package com.weibu.loveself.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibu.loveself.constant.QuestionTypeConst;
import com.weibu.loveself.dao.OrganizationDao;
import com.weibu.loveself.dao.UserAnswerDao;
import com.weibu.loveself.entity.Organization;
import com.weibu.loveself.entity.Question;
import com.weibu.loveself.entity.Sponsor;
import com.weibu.loveself.entity.UserAnswer;
import com.weibu.loveself.entity.statistics.SingleOptionResult;
import com.weibu.loveself.entity.wrapper.QuestionAnswerWrapper;
import com.weibu.loveself.entity.wrapper.QuestionOption;
import com.weibu.loveself.entity.wrapper.QuestionWrapper;
import com.weibu.loveself.mapper.statistics.AnswerStatMapper;
import com.weibu.loveself.service.IStatisticsService;
import com.weibu.loveself.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IStatisticsServiceImpl implements IStatisticsService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AnswerStatMapper answerStatMapper;
    @Autowired
    private UserAnswerDao userAnswerDao;

    @Override
    public List<SingleOptionResult> statisticsQuestionSingleResult(Question question) {
        List<SingleOptionResult> results = new ArrayList<>();
        try {
            // 1. 查询出该套题答案中关联的所有存在于组织结构里的代表，并更新手机号表记录
            List<Sponsor> sponsors = answerStatMapper.selectSponsorsAnswerSpecifiedQuestion(question.getId());
            List<Organization> organizations = organizationDao.findAll();

            //过滤出存在于组织结构中的代表
            List<Organization> filters = organizations.stream()
                    .filter(organization -> {
                        boolean exist = false;
                        for(Sponsor sponsor : sponsors) {
                            if(sponsor.getMobile().equals(organization.getMobile())) exist = true;
                        }
                        return exist;
                    })
                    .collect(Collectors.toList());
            if(filters.size() > 0) {
                // 更新指定套题下答题的相关联的代表记录表信息
                String tableName = String.format("sponsor_statistics_mobile_%d", question.getId());
                answerStatMapper.insertSponsorsAnswerSpecifiedQuestion(tableName, filters);
            }

            // 2. 该套题下所有题目信息汇总处理

            // 查询出该套题所有答案
            List<QuestionWrapper> wrappers = mapper.readValue(question.getData(), mapper.getTypeFactory().constructCollectionType(List.class, QuestionWrapper.class));
            if(wrappers != null) {
                // 获取并组装所有题目的答案
                Map<Integer, List<QuestionAnswerWrapper>> allAnswers = this.getQuestionOprions(question.getId(), wrappers);

                // 每个题目
                wrappers.forEach(wrapper -> {
                    SingleOptionResult result = new SingleOptionResult(question.getId(), wrapper.getIndex());
                    List<Map<String, Long>> opsCounts = null;
                    // 单选题的选项统计
                    if(wrapper.getType().equals(QuestionTypeConst.SINGLE_SELECT)) {
                        opsCounts = singleSelectQuestionStatistics(allAnswers.get(wrapper.getIndex()), wrapper.getOptions());
                    }
                    // 多选题的选项统计
                    if(wrapper.getType().equals(QuestionTypeConst.MULTI_SELECT)) {
                        opsCounts = multiSelectQuestionStatistics(allAnswers.get(wrapper.getIndex()), wrapper.getOptions());
                    }
                    if(opsCounts != null)
                        result.setCounts(opsCounts);
                    results.add(result);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    // 查询套题的每个题目的答题结果列表，键名为题目序号，键值为该题目所有答案列表
    private Map<Integer, List<QuestionAnswerWrapper>> getQuestionOprions(Long qid, List<QuestionWrapper> questionWrappers) {
        Map<Integer, List<QuestionAnswerWrapper>> optionsMap = new LinkedHashMap<>();

        // 根据题库中题目数量初始化答案列表
        questionWrappers.forEach(item -> {
            optionsMap.put(item.getIndex(), new ArrayList<>());
        });

        List<UserAnswer> userAnswers = userAnswerDao.selectUserAnswersByQuestionId(qid);
        if (userAnswers.size() > 0) {
            //其中每个元素里包含用户套题下所有题目的答案
            userAnswers.forEach(userAnswer -> {
                try {
                    List<QuestionAnswerWrapper> questionAnswers = mapper.readValue(userAnswer.getData(), mapper.getTypeFactory().constructCollectionType(List.class, QuestionAnswerWrapper.class));
                    if(questionAnswers != null) {
                        questionAnswers.forEach(questionAnswer -> {
                            Integer idx = questionAnswer.getIndex();
                            if(optionsMap.containsKey(idx)) {
                                optionsMap.get(idx).add(questionAnswer);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return optionsMap;
    }

    /**
     * 单选题的汇总
     * @param quesAllAnswers 一个题目所有的答案列表
     * @param options 题目的选项信息
     * @return 所有选项的次数统计结果
     */
    private List<Map<String, Long>>
    singleSelectQuestionStatistics(List<QuestionAnswerWrapper> quesAllAnswers, List<QuestionOption> options) {
        List<Map<String, Long>> optionsCount = new ArrayList<>();
        options.forEach(option -> {
            Long count = quesAllAnswers.stream()
                    .filter(answer -> answer.getAnswers().contains(option.getIndex()))
                    .count();
            Map<String, Long> optionCount = new HashMap<>();
            optionCount.put(option.getIndex(), count);
            optionsCount.add(optionCount);
        });
        return optionsCount;
    }

    /**
     * 多选题的汇总，先组合出题目选项可能的组合，再统计组合后的结果次数
     * @param quesAllAnswers 一个题目所有的答案列表
     * @param options 题目的选项信息
     * @return 所有组合后选项的次数统计结果
     */
    private List<Map<String, Long>>
    multiSelectQuestionStatistics(List<QuestionAnswerWrapper> quesAllAnswers, List<QuestionOption> options) {
        List<Map<String, Long>> optionsCount = new ArrayList<>();

        // 题目的原始选项
        List<String> ops = options.stream().map(QuestionOption::getIndex).collect(Collectors.toList());
        // 组合出所有可能的答案组合
        List<String> allOps = MathUtil.combination(ops);
        allOps.forEach(option -> {
            Long count = quesAllAnswers.stream()
                    .filter(answer -> {
                        // 1. 将答案处理成字符串
                        StringBuilder answerOpsStr = new StringBuilder();
                        answer.getAnswers().stream().sorted(String::compareTo).forEach(answerOpsStr::append);
                        // 2. 比较字符串
                        return option.equals(answerOpsStr.toString());
                    })
                    .count();

            Map<String, Long> optionCount = new HashMap<>();
            optionCount.put(option, count);
            optionsCount.add(optionCount);
        });
        return optionsCount;
    }
}

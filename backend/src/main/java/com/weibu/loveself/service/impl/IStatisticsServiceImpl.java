package com.weibu.loveself.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibu.loveself.dao.UserAnswerDao;
import com.weibu.loveself.entity.Question;
import com.weibu.loveself.entity.UserAnswer;
import com.weibu.loveself.entity.statistics.SingleOptionResult;
import com.weibu.loveself.entity.wrapper.QuestionAnswerWrapper;
import com.weibu.loveself.entity.wrapper.QuestionWrapper;
import com.weibu.loveself.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IStatisticsServiceImpl implements IStatisticsService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserAnswerDao userAnswerDao;

    @Override
    public List<SingleOptionResult> statisticsQuestionSingleResult(Question question) {
        List<SingleOptionResult> results = new ArrayList<>();
        try {
            // 套题下所有题目信息
            List<QuestionWrapper> wrappers = mapper.readValue(question.getData(), mapper.getTypeFactory().constructCollectionType(List.class, QuestionWrapper.class));
            if(wrappers != null) {
                // 获取并组装所有题目的答案
                Map<Integer, List<QuestionAnswerWrapper>> allAnswers = this.getQuestionAnswers(question.getId(), wrappers);

                // 每个题目
                wrappers.forEach(wrapper -> {
                    SingleOptionResult result = new SingleOptionResult(question.getId(), wrapper.getIndex());
                    // 题目的每个选项的次数
                    wrapper.getOptions().forEach(option -> {
                        Long count = allAnswers.get(wrapper.getIndex()).stream()
                                .filter(answer -> answer.getAnswers().contains(option.getIndex()))
                                .count();
                        Map<String, Long> optionCount = new HashMap<>();
                        optionCount.put(option.getIndex(), count);
                        result.getCounts().add(optionCount);
                    });
                    results.add(result);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    // 查询套题的每个题目的答题结果列表，键名为题目序号，键值为该题目所有答案列表
    private Map<Integer, List<QuestionAnswerWrapper>> getQuestionAnswers(Long qid, List<QuestionWrapper> questionWrappers) {
        Map<Integer, List<QuestionAnswerWrapper>> answerMap = new LinkedHashMap<>();

        // 根据题库中题目数量初始化答案列表
        questionWrappers.forEach(item -> {
            answerMap.put(item.getIndex(), new ArrayList<>());
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
                            if(answerMap.containsKey(idx)) {
                                answerMap.get(idx).add(questionAnswer);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return answerMap;
    }
}

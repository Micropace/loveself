package com.weibu.loveself;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibu.loveself.constant.QuestionTypeConst;
import com.weibu.loveself.dao.QuestionDao;
import com.weibu.loveself.entity.Question;
import com.weibu.loveself.entity.wrapper.QuestionWrapper;
import com.weibu.loveself.entity.wrapper.QuestionOption;
import com.weibu.loveself.entity.wrapper.QuestionTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class QuestionsTest {

    @Autowired
    private QuestionDao questionDao;

    private ObjectMapper mapper = new ObjectMapper();

    private List<String> idx = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N");

    @Test
    public void testInsert() {
//        List<QuestionWrapper> questions = new ArrayList<>();
//        for(int i=0; i<10; i++) {
//
//            String tc = "问题题干" + i;
//            String tp = i % 2 == 0 ? i + ".jpg" : null;
//            QuestionTitle title = new QuestionTitle(tc, tp);
//
//            List<QuestionOption> options = new ArrayList<>();
//            for(int j=0; j<4; j++) {
//                QuestionOption option = new QuestionOption(idx.get(j), "选项" + (j+1), null);
//                options.add(option);
//            }
//            List<String> answers = new ArrayList<>();
//            answers.add(options.get(0).getIndex());
//
//            QuestionWrapper qItem = new QuestionWrapper();
//            qItem.setIndex(i + 1);
//            if(i % 3 == 0) qItem.setType(QuestionTypeConst.SINGLE_SELECT);
//            else qItem.setType(QuestionTypeConst.MULTI_SELECT);
//            qItem.setTitle(title);
//            qItem.setOptions(options);
//            qItem.setAnswers(answers);
//
//            questions.add(qItem);
//        }
//
//        try {
//            String json = mapper.writeValueAsString(questions);
//            Question entity = new Question();
//            entity.setIdQrcode(1L);
//            entity.setIdSponsor(1L);
//            entity.setData(json);
//            questionDao.create(entity);
//
//            questionDao.deleteById(entity.getId());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testSelect() {
//        Question entity = questionDao.findByScene(10007L);
//        if(entity != null) {
//            try {
//                List<QuestionWrapper> questions = mapper.readValue(entity.getData(),
//                        mapper.getTypeFactory().constructCollectionType(List.class, QuestionWrapper.class));
//                System.out.println(questions);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Test
    // 超级科室会Topic1互动问题.pptx
    public void init10001() {
//        List<QuestionWrapper> questions = new ArrayList<>();
//
//        QuestionTitle title = new QuestionTitle("本次幻灯分享的两篇文献中，稳定性心绞痛患者，在β受体阻滞剂使用基础上联合万爽力，可以进一步降低心绞痛发作次数达？", null);
//
//        // 选项
//        List<QuestionOption> options = new ArrayList<>();
//        options.add(new QuestionOption("A", "40-50%", null));
//        options.add(new QuestionOption("B", "50-60%", null));
//        options.add(new QuestionOption("C", "60-70%", null));
//        options.add(new QuestionOption("D", "70%以上", null));
//
//        // 正确答案
//        List<String> answers = new ArrayList<>();
//
//        QuestionWrapper qItem = new QuestionWrapper();
//        qItem.setIndex(1);
//        qItem.setType(QuestionTypeConst.SINGLE_SELECT);
//        qItem.setTitle(title);
//        qItem.setOptions(options);
//        qItem.setAnswers(answers);
//        questions.add(qItem);
//
//        try {
//            String json = mapper.writeValueAsString(questions);
//            Question entity = new Question();
//            entity.setIdQrcode(1L);
//            entity.setData(json);
//            questionDao.create(entity);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    //超级科室会Topic2互动问题.pptx
    public void init10002() {
//        List<QuestionWrapper> questions = new ArrayList<>();
//
//        // 题干
//        QuestionTitle title = new QuestionTitle("以下药代动力学说法错误的是？", null);
//        List<QuestionOption> options = new ArrayList<>();
//        options.add(new QuestionOption("A", "目前治疗心绞痛的血流动力学药物仍存在一些局限性，如长效硝酸酯药物，由于耐药性问题，其每天需要有10-12小时的空窗期以防止耐药产生，因此对清晨危险时段疗效差", null));
//        options.add(new QuestionOption("B", "人体药代动力学实验表明，万爽力缓释片谷浓度较普通片提高了31%", null));
//        options.add(new QuestionOption("C", "万爽力缓释片生物利用度为50%", null));
//        options.add(new QuestionOption("D", "万爽力缓释片最佳药效持续时间T75（平台期，血浆浓度≥75%最大血浆浓度的时间）为11小时", null));
//
//        // 正确答案
//        List<String> answers = new ArrayList<>();
//        answers.add(options.get(1).getIndex());
//
//        QuestionWrapper qItem = new QuestionWrapper();
//        qItem.setIndex(1);
//        qItem.setType(QuestionTypeConst.SINGLE_SELECT);
//        qItem.setTitle(title);
//        qItem.setOptions(options);
//        qItem.setAnswers(answers);
//        questions.add(qItem);
//
//        try {
//            String json = mapper.writeValueAsString(questions);
//            Question entity = new Question();
//            entity.setIdQrcode(2L);
//            entity.setData(json);
//            questionDao.create(entity);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    //超级拜访互动问题.pptx
    public void init10003() {
//        List<QuestionWrapper> questions = new ArrayList<>();
//
//        // 1
//        QuestionTitle title1 = new QuestionTitle("您接诊的冠心病患者中，在接受了常规血流动力学药物治疗基础之上，大约多少比例仍存心绞痛症状？", null);
//        List<QuestionOption> options1 = new ArrayList<>();
//        options1.add(new QuestionOption("A", "<20%", null));
//        options1.add(new QuestionOption("B", "20-40%", null));
//        options1.add(new QuestionOption("C", ">40%", null));
//        List<String> answers1 = new ArrayList<>();
//
//        QuestionWrapper qItem1 = new QuestionWrapper();
//        qItem1.setIndex(1);
//        qItem1.setType(QuestionTypeConst.SINGLE_SELECT);
//        qItem1.setTitle(title1);
//        qItem1.setOptions(options1);
//        qItem1.setAnswers(answers1);
//        questions.add(qItem1);
//
//        // 2
//        QuestionTitle title2 = new QuestionTitle("您接诊的冠心病患者中，存在运动耐量下降的患者包括以下哪几类？（可多选）", null);
//        List<QuestionOption> options2 = new ArrayList<>();
//        options2.add(new QuestionOption("A", "冠心病合并糖尿病", null));
//        options2.add(new QuestionOption("B", "老龄患者", null));
//        options2.add(new QuestionOption("C", "中年女性", null));
//        options2.add(new QuestionOption("D", "长期高血压", null));
//        options2.add(new QuestionOption("E", "左心功能下降", null));
//        options2.add(new QuestionOption("F", "PCI术后", null));
//        List<String> answers2 = new ArrayList<>();
//
//        QuestionWrapper qItem2 = new QuestionWrapper();
//        qItem2.setIndex(2);
//        qItem2.setType(QuestionTypeConst.MULTI_SELECT);
//        qItem2.setTitle(title2);
//        qItem2.setOptions(options2);
//        qItem2.setAnswers(answers2);
//        questions.add(qItem2);
//
//        // 3
//        QuestionTitle title3 = new QuestionTitle("对于左心功能不全患者，以下哪些是您的治疗目标？(可多选)", null);
//        List<QuestionOption> options3 = new ArrayList<>();
//        options3.add(new QuestionOption("A", "改善心功能", null));
//        options3.add(new QuestionOption("B", "提高运动耐量", null));
//        options3.add(new QuestionOption("C", "减少症状发作", null));
//        List<String> answers3 = new ArrayList<>();
//
//        QuestionWrapper qItem3 = new QuestionWrapper();
//        qItem3.setIndex(3);
//        qItem3.setType(QuestionTypeConst.MULTI_SELECT);
//        qItem3.setTitle(title3);
//        qItem3.setOptions(options3);
//        qItem3.setAnswers(answers3);
//        questions.add(qItem3);
//
//        try {
//            String json = mapper.writeValueAsString(questions);
//            Question entity = new Question();
//            entity.setIdQrcode(3L);
//            entity.setData(json);
//            questionDao.create(entity);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    // 万爽力主RT
    @Test
    public void test10004() {
        List<QuestionWrapper> questions = new ArrayList<>();

        QuestionTitle title = new QuestionTitle("以下哪个描述是错误的？", null);

        // 选项
        List<QuestionOption> options = new ArrayList<>();
        options.add(new QuestionOption("A", "代谢异常很早出现，并贯穿疾病的始终", null));
        options.add(new QuestionOption("B", "血流动力学药物早期联合代谢治疗，才能真正全面控制心肌缺血", null));
        options.add(new QuestionOption("C", "最新欧洲个体化治疗共识推荐万爽力及早联合在：合并糖尿病、慢性阻塞性肺病、外周动脉疾病、冠脉痉挛、微血管心绞痛、心率≥70，心动过缓、高血压、低血压、左室功能紊乱、心力衰竭、心房颤动等12种情况", null));
        options.add(new QuestionOption("D", "CHOICE-II 亚组研究显示：新近诊断的心绞痛患者，联合万爽力可以进一步减少发作80%", null));

        // 正确答案
        List<String> answers = new ArrayList<>();
        answers.add("D");

        QuestionWrapper qItem = new QuestionWrapper();
        qItem.setIndex(1);
        qItem.setType(QuestionTypeConst.SINGLE_SELECT);
        qItem.setTitle(title);
        qItem.setOptions(options);
        qItem.setAnswers(answers);
        questions.add(qItem);

        try {
            String json = mapper.writeValueAsString(questions);
            Question entity = new Question();
            entity.setIdQrcode(15L);
            entity.setData(json);
            questionDao.create(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // CHCRT
    @Test
    public void test10005() {
        List<QuestionWrapper> questions = new ArrayList<>();

        QuestionTitle title = new QuestionTitle("以下描述错误的是哪个？", null);

        // 选项
        List<QuestionOption> options = new ArrayList<>();
        options.add(new QuestionOption("A", "CHOICE-II研究显示：及早联合万爽力可以增加运动耐量，增加最远步行距离达76%", null));
        options.add(new QuestionOption("B", "CHOICE-II亚组研究显示：新近发现的冠心病患者，及早联合万爽力缓释片，可进一步降低心绞痛发作达80%", null));
        options.add(new QuestionOption("C", "万爽力缓释片24小时强效抗心肌缺血，较仿制品疗效提高63%", null));
        options.add(new QuestionOption("D", "万爽力获得2013ESC冠心病指南和2018中国冠心病指南及2017欧洲心绞痛专家共识推荐", null));

        // 正确答案
        List<String> answers = new ArrayList<>();
        answers.add("B");

        QuestionWrapper qItem = new QuestionWrapper();
        qItem.setIndex(1);
        qItem.setType(QuestionTypeConst.SINGLE_SELECT);
        qItem.setTitle(title);
        qItem.setOptions(options);
        qItem.setAnswers(answers);
        questions.add(qItem);

        try {
            String json = mapper.writeValueAsString(questions);
            Question entity = new Question();
            entity.setIdQrcode(16L);
            entity.setData(json);
            questionDao.create(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // 缓释RT
    @Test
    public void test10006() {
        List<QuestionWrapper> questions = new ArrayList<>();

        QuestionTitle title = new QuestionTitle("以下说法错误的是？", null);

        // 选项
        List<QuestionOption> options = new ArrayList<>();
        options.add(new QuestionOption("A", "目前治疗心绞痛的血流动力学药物仍存在一些局限性，如长效硝酸酯药物，由于耐药性问题，其每天需要有10-12小时的空窗期以防止耐药产生，因此对清晨危险时段疗效差", null));
        options.add(new QuestionOption("B", "人体药代动力学实验表明，万爽力缓释片谷浓度较普通片提高了31%", null));
        options.add(new QuestionOption("C", "万爽力缓释片生物利用度为50%", null));
        options.add(new QuestionOption("D", "万爽力缓释片24小时强效抗心肌缺血，较曲美他嗪普通片仿制品疗效提高63%", null));

        // 正确答案
        List<String> answers = new ArrayList<>();
        answers.add("C");

        QuestionWrapper qItem = new QuestionWrapper();
        qItem.setIndex(1);
        qItem.setType(QuestionTypeConst.SINGLE_SELECT);
        qItem.setTitle(title);
        qItem.setOptions(options);
        qItem.setAnswers(answers);
        questions.add(qItem);

        try {
            String json = mapper.writeValueAsString(questions);
            Question entity = new Question();
            entity.setIdQrcode(17L);
            entity.setData(json);
            questionDao.create(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // 可兰特（3道题，都是单选）
    @Test
    public void test10007() {
        List<QuestionWrapper> questions = new ArrayList<>();

        // 1
        QuestionTitle title1 = new QuestionTitle("SHIFT研究超声亚组研究显示，联合可兰特治疗8个月，显著改善心衰患者左室收缩末期容积（LVESVI）和左室舒张末期容积（LVEDVI），所以联合可兰特可以显著逆转心衰患者______________, 改善心功能。", null);
        List<QuestionOption> options1 = new ArrayList<>();
        options1.add(new QuestionOption("A", "水肿", null));
        options1.add(new QuestionOption("B", "左室重构", null));
        options1.add(new QuestionOption("C", "心肺复苏", null));
        options1.add(new QuestionOption("D", "肺部湿罗音", null));
        List<String> answers1 = new ArrayList<>();
        answers1.add("B");

        QuestionWrapper qItem1 = new QuestionWrapper();
        qItem1.setIndex(1);
        qItem1.setType(QuestionTypeConst.SINGLE_SELECT);
        qItem1.setTitle(title1);
        qItem1.setOptions(options1);
        qItem1.setAnswers(answers1);
        questions.add(qItem1);

        // 2
        QuestionTitle title2 = new QuestionTitle("以下关于心率增快与心衰患者关系说法不正确的是？", null);
        List<QuestionOption> options2 = new ArrayList<>();
        options2.add(new QuestionOption("A", "急性心衰阶段，由于短期血流动力学状态恶化，心率增快是一种代偿机制", null));
        options2.add(new QuestionOption("B", "心衰患者进入易损期阶段后，心功能较差和心率偏快并存，心率持续增快会导致心功能恶化和心衰症状加重", null));
        options2.add(new QuestionOption("C", "心衰患者进入易损期期阶段后，心功能仍然较差，所以心率增快是正常代偿", null));
        options2.add(new QuestionOption("D", "心率增快是心衰患者易损期的可控因素", null));

        List<String> answers2 = new ArrayList<>();
        answers2.add("C");

        QuestionWrapper qItem2 = new QuestionWrapper();
        qItem2.setIndex(2);
        qItem2.setType(QuestionTypeConst.SINGLE_SELECT);
        qItem2.setTitle(title2);
        qItem2.setOptions(options2);
        qItem2.setAnswers(answers2);
        questions.add(qItem2);

        // 3
        QuestionTitle title3 = new QuestionTitle("“易损期及早联合”中的“易损期”所指的慢性心衰患者是_________", null);
        List<QuestionOption> options3 = new ArrayList<>();
        options3.add(new QuestionOption("A", "血流动力学不稳定的急性期患者", null));
        options3.add(new QuestionOption("B", "入院治疗后病情稳定，出院前患者", null));
        options3.add(new QuestionOption("C", "出院后前 3个月内的门诊患者", null));
        options3.add(new QuestionOption("D", "β受体阻滞剂已达最佳剂量的门诊患者", null));
        List<String> answers3 = new ArrayList<>();
        answers3.add("C");

        QuestionWrapper qItem3 = new QuestionWrapper();
        qItem3.setIndex(3);
        qItem3.setType(QuestionTypeConst.SINGLE_SELECT);
        qItem3.setTitle(title3);
        qItem3.setOptions(options3);
        qItem3.setAnswers(answers3);
        questions.add(qItem3);

        try {
            String json = mapper.writeValueAsString(questions);
            Question entity = new Question();
            entity.setIdQrcode(18L);
            entity.setData(json);
            questionDao.create(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

/*

qrcode 10004(ID:15) 万爽力主RT：http://micropace.top/wslrt/index.html
qrcode 10005(ID:16) 缓释RT：http://micropace.top/hsrt/index.html
qrcode 10006(ID:17) CHCRT：http://micropace.top/chcrt/index.html
qrcode 10007(ID:18) 可兰特：http://micropace.top/klt/index.html

*********************
万爽力主RT
*********************
问：以下哪个描述是错误的：D
A. 代谢异常很早出现，并贯穿疾病的始终
B. 血流动力学药物早期联合代谢治疗，才能真正全面控制心肌缺血
C. 最新欧洲个体化治疗共识推荐万爽力及早联合在：合并糖尿病、慢性阻塞性肺病、外周动脉疾病、冠脉痉挛、微血管心绞痛、心率≥70，心动过缓、高血压、低血压、左室功能紊乱、心力衰竭、心房颤动等12种情况。
D. CHOICE-II 亚组研究显示：新近诊断的心绞痛患者，联合万爽力可以进一步减少发作80%

*********************
CHCRT
*********************
以下描述错误的是哪个？B
A. CHOICE-II研究显示：及早联合万爽力可以增加运动耐量，增加最远步行距离达76%
B. CHOICE-II亚组研究显示：新近发现的冠心病患者，及早联合万爽力缓释片，可进一步降低心绞痛发作达80%
C. 万爽力缓释片24小时强效抗心肌缺血，较仿制品疗效提高63%
D. 万爽力获得2013ESC冠心病指南和2018中国冠心病指南及2017欧洲心绞痛专家共识推荐

*********************
缓释RT
*********************
以下说法错误的是？C
A. 目前治疗心绞痛的血流动力学药物仍存在一些局限性，如长效硝酸酯药物，由于耐药性问题，其每天需要有10-12小时的空窗期以防止耐药产生，因此对清晨危险时段疗效差。
B. 人体药代动力学实验表明，万爽力缓释片谷浓度较普通片提高了31%
C. 万爽力缓释片生物利用度为50%
D. 万爽力缓释片24小时强效抗心肌缺血，较曲美他嗪普通片仿制品疗效提高63%

*********************
可兰特（3道题，都是单选）
*********************
1.SHIFT研究超声亚组研究显示，联合可兰特治疗8个月，显著改善心衰患者左室收缩末期容积（LVESVI）和左室舒张末期容积（LVEDVI），
所以联合可兰特可以显著逆转心衰患者______________, 改善心功能。
A 水肿
B 左室重构    （正确答案）
C 心肺复苏
D 肺部湿罗音

2.以下关于心率增快与心衰患者关系说法不正确的是：
A 急性心衰阶段，由于短期血流动力学状态恶化，心率增快是一种代偿机制
B 心衰患者进入易损期阶段后，心功能较差和心率偏快并存，心率持续增快会导致心功能恶化和心衰症状加重
C 心衰患者进入易损期期阶段后，心功能仍然较差，所以心率增快是正常代偿   （正确答案）
D 心率增快是心衰患者易损期的可控因素

3.“易损期及早联合”中的“易损期”所指的慢性心衰患者是_________
A. 血流动力学不稳定的急性期患者
B. 入院治疗后病情稳定，出院前患者
C. 出院后前 3个月内的门诊患者     （争取答案）
D．β受体阻滞剂已达最佳剂量的门诊患者

 */
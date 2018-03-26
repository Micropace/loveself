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
}

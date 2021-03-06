package com.weibu.loveself.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibu.loveself.common.BaseController;
import com.weibu.loveself.common.ResponseMsg;
import com.weibu.loveself.dao.*;
import com.weibu.loveself.entity.*;
import com.weibu.loveself.entity.wrapper.QuestionAnswerWrapper;
import com.weibu.loveself.entity.wrapper.QuestionWrapper;
import com.weibu.loveself.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 答题相关后台接口
 */
@RestController
@RequestMapping("/api")
public class MiniAppController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(MiniAppController.class.getSimpleName());

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserDao userDao;
    @Autowired
    private SponsorDao sponsorDao;
    @Autowired
    private UserRelationDao userRelationDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionConfigDao questionConfigDao;
    @Autowired
    private UserAnswerDao userAnswerDao;

    /**
     * 首页内容获取接口
     * 当openid的用户不存在则创建
     * @param openid 用户openid
     * @param scene 场景值: 格式为mobile+题ID, 手机号11位，后面追加对应的套题ID
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ResponseMsg getHomePageConteng(@RequestParam(value="openid") String openid,
                                          @RequestParam(value="scene") String scene) {

        logger.info("openid: {}, scene: {}", openid, scene);
        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(scene == null || scene.length() <= 11 || value == null)
            return error("scene_must_be_numbers");

        // 查询用户，不存在时则创建
        int isFirstScan = 0;
        User user = userDao.findByOpenid(openid);
        if(user == null) {
            user = new User();
            user.setOpenid(openid);
            if(userDao.create(user) != 1)
                return error("create_user_failed");
            isFirstScan = 1;
        }

        // 从场景值中获取手机号和题ID
        String mobile = scene.substring(0, 11);
        Long idQuestion = Long.parseLong(scene.substring(11, scene.length()));

        if(!ValidatorUtil.isMobile(mobile)) {
            return error("illegal_mobile");
        }

        // 查询场景值对应题库的配置信息
        Map<String, Object> result = new HashMap<>();
        QuestionConfig questionConfig = questionConfigDao.findById(idQuestion);
        if(questionConfig == null)
            return error("question_config_not_found");

        result.put("mobile", mobile);
        result.put("isFirstScan", isFirstScan);
        // 页面相关颜色配置
        result.put("backgroundColor", questionConfig.getBackgroundColor());
        result.put("pageDiscriptionColor", questionConfig.getPageDiscriptionColor());
        result.put("questionActiveColor", questionConfig.getQuestionActiveColor());
        result.put("questionOptionColor", questionConfig.getQuestionOptionColor());

        result.put("barTitleText", questionConfig.getBarTitleText());
        if (questionConfig.getBannerText() != null)
            result.put("bannerText", questionConfig.getBannerText());
        if (questionConfig.getBannerPicName() != null)
            result.put("bannerPicName", questionConfig.getBannerPicName());
        if (questionConfig.getHomePagePicName() != null)
            result.put("homePagePicName", questionConfig.getHomePagePicName());
        if (questionConfig.getHomePageDiscription() != null)
            result.put("homePageDiscription", questionConfig.getHomePageDiscription());
        return success(result);
    }

    /**
     * 提交用户填写的手机号
     * @param params POST提交参数
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseMsg mobileRegister(@RequestBody Map<String, String> params) {

        String openid = params.get("openid");
        String mobile = params.get("mobile");

        logger.info("openid: {}, mobile: {}", openid, mobile);

        //查询用户是否存在
        User user = userDao.findByOpenid(openid);
        if(user == null) {
            return error("user_not_found");
        }

        //查询代表，不存在则创建
        Sponsor sponsor = sponsorDao.findByMobile(mobile);
        if(sponsor == null) {
            sponsor = new Sponsor();
            sponsor.setMobile(mobile);
            if(sponsorDao.create(sponsor) != 1)
                return error("create_sponsor_failed");
        }

        //查询用户和代表是否关联
        UserRelation relation = userRelationDao.findRelation(openid, mobile);
        if(relation == null) {
            relation = new UserRelation();
            relation.setIdUser(user.getId());
            relation.setIdSponsor(sponsor.getId());
            if(userRelationDao.create(relation) != 1)
                return error("create_relationship_failed");
        }
        return success();
    }

    /**
     * 题库内容获取
     * @param openid 用户openid
     * @param scene 场景值
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseMsg getQuestions(@RequestParam(value="openid") String openid,
                                    @RequestParam(value="scene") String scene) {

        logger.info("openid: {}, scene: {}", openid, scene);

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(scene == null || scene.length() <= 11 || value == null)
            return error("scene_must_be_numbers");

        // 查询用户是否存在
        User user = userDao.findByOpenid(openid);
        if(user == null)
            return error("user_not_found");

        // 从场景值中获取题ID
        Long idQuestion = Long.parseLong(scene.substring(11, scene.length()));

        // 查询题库是否存在
        Question question = questionDao.findById(idQuestion);
        if(question == null)
            return error("questions_not_found");

        // 查询题库配置信息是否存在
        QuestionConfig questionConfig = questionConfigDao.findByQuestionId(idQuestion);
        if(questionConfig == null)
            return error("question_config_not_found");


        List<QuestionWrapper> quesItems = new ArrayList<>();
        try {
            quesItems = mapper.readValue(question.getData(), mapper.getTypeFactory().constructCollectionType(List.class, QuestionWrapper.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //组装结果
        Map<String, Object> result = new HashMap<>();
        // 页面相关颜色配置
        result.put("backgroundColor", questionConfig.getBackgroundColor());
        result.put("pageDiscriptionColor", questionConfig.getPageDiscriptionColor());
        result.put("questionActiveColor", questionConfig.getQuestionActiveColor());
        result.put("questionOptionColor", questionConfig.getQuestionOptionColor());
        //bar 文字
        result.put("barTitleText", questionConfig.getBarTitleText());
        if (questionConfig.getBannerText() != null)
            result.put("bannerText", questionConfig.getBannerText());
        if (questionConfig.getBannerPicName() != null)
            result.put("bannerPicName", questionConfig.getBannerPicName());
        result.put("questions", quesItems);
        return success(result);
    }

    /**
     * 提交用户的答题结果, 已答过则更新
     * @param params POST提交参数
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    public ResponseMsg submitUserAnswers(@RequestBody Map<String, String> params) {
        String openid = params.get("openid");
        String scene = params.get("scene");
        String results = params.get("results");

        logger.info("openid: {}, scene: {}, results: {}", openid, scene, results);

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(scene == null || scene.length() <= 11 || value == null)
            return error("scene_must_be_numbers");

        // 查询用户是否存在
        User user = userDao.findByOpenid(openid);
        if(user == null)
            return error("user_not_found");

        // 从场景值中获取题ID
        Long idQuestion = Long.parseLong(scene.substring(11, scene.length()));

        // 查询题库是否存在
        Question question = questionDao.findById(idQuestion);
        if(question == null)
            return error("questions_not_found");

        // 结果格式验证, 必须是JSON格式字符串
        List<QuestionAnswerWrapper> answerWrappers = null;
        try {
            answerWrappers = mapper.readValue(results, mapper.getTypeFactory().constructCollectionType(List.class, QuestionAnswerWrapper.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(answerWrappers == null)
            return error("illegal_results_format");

        // 查询用户的答题结果，不存在则创建
        UserAnswer userAnswer = userAnswerDao.findUserAnswerByOpenidAndQuestionId(openid, idQuestion);
        if(userAnswer == null) {
            userAnswer = new UserAnswer();
            userAnswer.setIdQuestion(question.getId());
            userAnswer.setIdUser(user.getId());
            userAnswer.setData(results);
            if(userAnswerDao.create(userAnswer) != 1)
                return error("create_user_answers_failed");
        } else {
            userAnswer.setData(results);
            if(userAnswerDao.update(userAnswer) != 1)
                return error("update_user_answers_failed");
        }
        return success();
    }

    /**
     * 结束页内容获取接口
     * @param openid 用户openid
     * @param scene 场景值
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/endpage", method = RequestMethod.GET)
    public ResponseMsg getEndPageConteng(@RequestParam(value="openid") String openid,
                                         @RequestParam(value="scene") String scene) {

        logger.info("openid: {}, scene: {}", openid, scene);

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(scene == null || scene.length() <= 11 || value == null)
            return error("scene_must_be_numbers");

        // 查询用户是否存在
        User user = userDao.findByOpenid(openid);
        if(user == null)
            return error("user_not_found");

        // 从场景值中获取题ID
        Long idQuestion = Long.parseLong(scene.substring(11, scene.length()));

        // 查询场景值对应题库的配置信息
        Map<String, Object> result = new HashMap<>();
        QuestionConfig questionConfig = questionConfigDao.findById(idQuestion);
        if(questionConfig == null)
            return error("question_config_not_found");

        UserAnswer userAnswer = userAnswerDao.findUserAnswerByOpenidAndQuestionId(openid, idQuestion);
        if(userAnswer == null)
            return error("question_user_answer_not_found");

        // 页面相关颜色配置
        result.put("backgroundColor", questionConfig.getBackgroundColor());
        result.put("pageDiscriptionColor", questionConfig.getPageDiscriptionColor());
        result.put("questionActiveColor", questionConfig.getQuestionActiveColor());
        result.put("questionOptionColor", questionConfig.getQuestionOptionColor());
        // 答题时间
        result.put("answerTime", userAnswer.getUpdatedAt());
        // bar文字
        result.put("barTitleText", questionConfig.getBarTitleText());
        if (questionConfig.getBannerText() != null)
            result.put("bannerText", questionConfig.getBannerText());
        if (questionConfig.getBannerPicName() != null)
            result.put("bannerPicName", questionConfig.getBannerPicName());
        if (questionConfig.getEndPagePicName() != null)
            result.put("endPagePicName", questionConfig.getEndPagePicName());
        if (questionConfig.getEndPageDiscription() != null)
            result.put("endPageDiscription", questionConfig.getEndPageDiscription());
        return success(result);
    }

    // todo 统计相关流程
    // todo 1. 建立组织结构信息表，独立的代表组织分类信息，独立的统计结果表。
    // todo 2. 组织结构数据从excel导入。
    // todo 3. 定时任务处理所有代表的每天数量统计，结果存储中间结果表：一个代表、一套题、一天的答题量。
    // todo 4. 接口查询代表关于那几套题的统计结果，直接从中间结果选取结果，计算最终的结果。
}

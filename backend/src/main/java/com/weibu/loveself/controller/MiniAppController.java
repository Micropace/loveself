package com.weibu.loveself.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weibu.loveself.common.BaseController;
import com.weibu.loveself.common.ResponseMsg;
import com.weibu.loveself.dao.*;
import com.weibu.loveself.entity.*;
import com.weibu.loveself.entity.wrapper.QuestionAnswerWrapper;
import com.weibu.loveself.entity.wrapper.QuestionWrapper;
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
     * @param scene 场景值
     * @return ResponseMsg
     */
    @ResponseBody
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ResponseMsg getHomePageConteng(@RequestParam(value="openid") String openid,
                                          @RequestParam(value="scene") String scene) {

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(value == null)
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

        // 查询场景值对应题库的配置信息
        Map<String, Object> result = new HashMap<>();
        QuestionConfig questionConfig = questionConfigDao.findByScene(value);
        if(questionConfig == null)
            return error("question_config_not_found");

        result.put("isFirstScan", isFirstScan);
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

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(value == null)
            return error("scene_must_be_numbers");

        // 查询用户是否存在
        User user = userDao.findByOpenid(openid);
        if(user == null)
            return error("user_not_found");

        // 查询题库是否存在
        Question question = questionDao.findByScene(value);
        if(question == null)
            return error("questions_not_found");

        // 查询题库配置信息是否存在
        QuestionConfig questionConfig = questionConfigDao.findByScene(value);
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

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(value == null)
            return error("scene_must_be_numbers");

        // 查询用户，不存在时则创建
        User user = userDao.findByOpenid(openid);
        if(user == null)
            return error("user_not_found");

        // 查询题库是否存在
        Question question = questionDao.findByScene(value);
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
        UserAnswer userAnswer = userAnswerDao.findUserAnswerByScene(openid, value);
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

        // 参数校验
        Long value = null;
        try {
            value = Long.parseLong(scene);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if(value == null)
            return error("scene_must_be_numbers");

        // 查询用户是否存在
        User user = userDao.findByOpenid(openid);
        if(user == null)
            return error("user_not_found");

        // 查询场景值对应题库的配置信息
        Map<String, Object> result = new HashMap<>();
        QuestionConfig questionConfig = questionConfigDao.findByScene(value);
        if(questionConfig == null)
            return error("question_config_not_found");

        UserAnswer userAnswer = userAnswerDao.findUserAnswerByScene(openid, value);
        if(userAnswer == null)
            return error("question_user_answer_not_found");

        result.put("answerTime", userAnswer.getUpdatedAt());
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

}

package com.weibu.loveself.controller;

import com.weibu.loveself.common.BaseController;
import com.weibu.loveself.common.ResponseMsg;
import com.weibu.loveself.dao.QuestionDao;
import com.weibu.loveself.entity.Question;
import com.weibu.loveself.entity.statistics.SingleOptionResult;
import com.weibu.loveself.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统计相关的接口
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private IStatisticsService iStatisticsService;

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public ResponseMsg singleOptionStatistics(@RequestParam(value="qid") Long idQuestion) {

        Question question = questionDao.findById(idQuestion);
        if(question == null) {
            return error("question not found");
        }

        List<SingleOptionResult> results = iStatisticsService.statisticsQuestionSingleResult(question);
        return success(results);
    }

}

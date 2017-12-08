package com.weibu.loveself.service;

import com.weibu.loveself.entity.Question;
import com.weibu.loveself.entity.statistics.SingleOptionResult;

import java.util.List;

/**
 * 统计业务处理接口
 */
public interface IStatisticsService {

    /**
     * 统计一套题下每个题目的各项答案出现次数
     * @param question 一套题的对象
     * @return 所有题目的统计结果列表
     */
    List<SingleOptionResult> statisticsQuestionSingleResult(Question question);
}

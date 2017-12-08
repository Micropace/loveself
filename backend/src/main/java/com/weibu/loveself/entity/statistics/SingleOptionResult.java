package com.weibu.loveself.entity.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一套题中指定序号的题目下每个选项的次数统计结果
 */
public class SingleOptionResult {

    /** 题库ID */
    private Long idQuestion;
    /** 题目序号 */
    private Integer index;
    /** 题目下所有选项的次数统计结果，键名为选项序号，如A、B、C... */
    private List<Map<String, Long>> counts;

    public SingleOptionResult() {}

    public SingleOptionResult(Long idQuestion, Integer index) {
        this.idQuestion = idQuestion;
        this.index = index;
        this.counts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SingleOptionResult{" +
                "idQuestion=" + idQuestion +
                ", index=" + index +
                ", counts=" + counts +
                '}';
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Map<String, Long>> getCounts() {
        return counts;
    }

    public void setCounts(List<Map<String, Long>> counts) {
        this.counts = counts;
    }
}

package com.weibu.loveself.entity.wrapper;

import java.util.List;

/**
 * 用户一个题目的答题结果信息
 */
public class QuestionAnswerWrapper {

    /** 题目序号 */
    private Integer index;
    /** 用户选择的答案 */
    private List<String> answers;

    @Override
    public String toString() {
        return "UserAnswerItem{" +
                "index=" + index +
                ", answers=" + answers +
                '}';
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}

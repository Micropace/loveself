package com.weibu.loveself.entity.wrapper;

import java.util.List;

/**
 * 一个题目的信息
 */
public class QuestionWrapper {
    /** 题目序号 */
    private Integer index;
    /** 题目类型 */
    private String type;
    /** 题目题干信息 */
    private QuestionTitle title;
    /** 题目选项, 如[{"index": "A", "content": "xxxxx"}] */
    private List<QuestionOption> options;
    /** 题目的正确答案, 如["A","B"] */
    private List<String> answers;

    @Override
    public String toString() {
        return "QuestionWrapper{" +
                "index=" + index +
                ", type='" + type + '\'' +
                ", title=" + title +
                ", options=" + options +
                ", answers=" + answers +
                '}';
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public QuestionTitle getTitle() {
        return title;
    }

    public void setTitle(QuestionTitle title) {
        this.title = title;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}

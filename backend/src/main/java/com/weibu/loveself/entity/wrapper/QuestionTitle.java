package com.weibu.loveself.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 题干信息
 */
public class QuestionTitle {
    /** 题干内容文字 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    /** 题干图片路径 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String picName;

    public QuestionTitle() {}

    public QuestionTitle(String content, String picName) {
        this.content = content;
        this.picName = picName;
    }

    public String getContent() {
        return content;
    }

    public String getPicName() {
        return picName;
    }
}

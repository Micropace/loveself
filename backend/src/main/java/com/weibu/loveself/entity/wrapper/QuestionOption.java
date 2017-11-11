package com.weibu.loveself.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 具体条目的详细信息
 * 针对题目题干时，index是指该题目的序号，针对选项时，是指该题目下选项的序号
 * 注意：题干、选项中都可能出现图片
 */
public class QuestionOption {
    /** 选项序号 */
    private String index;
    /** 选项内容文字 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    /** 选项图片路径 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String picName;

    public QuestionOption() {}

    public QuestionOption(String index, String content, String picName) {
        this.index = index;
        this.content = content;
        this.picName = picName;
    }

    public String getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    public String getPicName() {
        return picName;
    }
}

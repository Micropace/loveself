package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

/**
 * 题库的配置信息表
 * 一套题目首页图片和文字、结束页图片和文字、banner等的配置信息
 *
 * Bar颜色：barColor
 * Bar文字颜色：barTextColor
 * 页面背景色：backgroundColor
 * 题干背景色：questionTitleColor
 * 题目选项背景色：questionSectionColor
 题目选项选中背景色：questionSectionSelectedColor
 结束页文字颜色：endPageTextColor
 */
@Getter
@Setter
@ToString
public class QuestionConfig extends MybatisBasePagerEntity {
    /** 题库ID */
    @Column(name = "id_question", nullable = false)
    private Long idQuestion;
    /** 页面背景色 */
    @Column(name = "background_color")
    private String backgroundColor;
    /** 页面描述文字颜色 */
    @Column(name = "page_discription_color")
    private String pageDiscriptionColor;
    /** 题干、题目选项选中背景色 */
    @Column(name = "question_active_color")
    private String questionActiveColor;
    /** 题目选项背景色 */
    @Column(name = "question_option_color")
    private String questionOptionColor;
    /** Bar标题文字 */
    @Column(name = "bar_title_text")
    private String barTitleText;
    /** banner文字 */
    @Column(name = "banner_text")
    private String bannerText;
    /** banner图片路径 */
    @Column(name = "banner_pic_name")
    private String bannerPicName;
    /** 首页描述文字 */
    @Column(name = "home_page_discription")
    private String homePageDiscription;
    /** 首页图片路径 */
    @Column(name = "home_page_pic_name")
    private String homePagePicName;
    /** 结束页描述文字 */
    @Column(name = "end_page_discription")
    private String endPageDiscription;
    /** 结束页图片路径 */
    @Column(name = "end_page_pic_name")
    private String endPagePicName;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
}

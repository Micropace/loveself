package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 题库信息表
 * 一个代表可以有多套题目，每套题目都有独立的二维码对应scnen值
 */
@Getter
@Setter
@ToString
public class Question extends MybatisBasePagerEntity {
    /** 题目信息Json字符串 */
    @Column(name = "data")
    private String data;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
}

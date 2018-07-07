package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 用户答题结果信息表
 * 每个用户一套题只有一条记录，允许重复答题，重复答题则更新结果
 */
@Getter
@Setter
@ToString
public class UserAnswer extends MybatisBasePagerEntity {
    /** 题库ID */
    @Column(name = "id_question")
    private Long idQuestion;
    /** 用户ID */
    @Column(name = "id_user")
    private Long idUser;
    /** 答题结果Json字符串 */
    @Column(name = "data")
    private String data;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
}

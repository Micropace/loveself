package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 用户答题结果信息表
 * 每个用户一套题只有一条记录，允许重复答题，重复答题则更新结果
 */
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

    @Override
    public String toString() {
        return "UserAnswer{" +
                "idQuestion=" + idQuestion +
                ", idUser=" + idUser +
                ", data='" + data + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

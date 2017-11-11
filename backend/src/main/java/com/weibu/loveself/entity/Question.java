package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 题库信息表
 * 一个代表可以有多套题目，每套题目都有独立的二维码对应scnen值
 */
public class Question extends MybatisBasePagerEntity {
    /** 代表ID */
    @Column(name = "id_sponsor")
    private Long idSponsor;
    /** 二维码ID */
    @Column(name = "id_qrcode")
    private Long idQrcode;
    /** 题目信息Json字符串 */
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
        return "Question{" +
                "idSponsor=" + idSponsor +
                ", idQrcode=" + idQrcode +
                ", data='" + data + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public Long getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(Long idSponsor) {
        this.idSponsor = idSponsor;
    }

    public Long getIdQrcode() {
        return idQrcode;
    }

    public void setIdQrcode(Long idQrcode) {
        this.idQrcode = idQrcode;
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

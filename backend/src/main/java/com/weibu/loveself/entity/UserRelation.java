package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 用户和代表关联信息表
 */
public class UserRelation extends MybatisBasePagerEntity {
    /** 代表ID */
    @Column(name = "id_sponsor", nullable = false)
    private Long idSponsor;
    /** 用户ID */
    @Column(name = "id_user", nullable = false)
    private Long idUser;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updatedAt;

    @Override
    public String toString() {
        return "UserRelation{" +
                "idSponsor=" + idSponsor +
                ", idUser=" + idUser +
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 用户和代表关联信息表
 */
@Getter
@Setter
@ToString
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
}

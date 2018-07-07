package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 代表信息表
 */
@Getter
@Setter
@ToString
public class Sponsor extends MybatisBasePagerEntity {
    @Column(name = "mobile", nullable = false)
    private String mobile;
    @Column(name = "openid")
    private String openid;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "country")
    private String country;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
}

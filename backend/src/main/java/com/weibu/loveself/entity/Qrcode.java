package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 二维码信息表，生成后创建记录
 */
@Getter
@Setter
@ToString
public class Qrcode extends MybatisBasePagerEntity {
    /** 二维码对应的场景值 */
    @Column(name = "scene", nullable = false)
    private Long scene;
    /** 类型，0: 永久, 1: 临时。临时则需要expire值 */
    @Column(name = "type", nullable = false)
    private Integer type;
    /** 图片在uploader下的相对路径名包括文件名 */
    @Column(name = "name")
    private String name;
    /** 本地路径 */
    @Column(name = "path")
    private String path;
    /** 临时二维码有效时间 */
    @Column(name = "expire")
    private Integer expire;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
}

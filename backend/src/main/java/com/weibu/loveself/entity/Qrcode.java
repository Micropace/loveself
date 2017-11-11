package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

/**
 * 二维码信息表，生成后创建记录
 */
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

    @Override
    public String toString() {
        return "Qrcode{" +
                "scene=" + scene +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", expire=" + expire +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public Long getScene() {
        return scene;
    }

    public void setScene(Long scene) {
        this.scene = scene;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
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

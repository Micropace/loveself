package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;

import javax.persistence.Column;

/**
 * 组织结构信息表
 */
public class Organization extends MybatisBasePagerEntity {
    /** 代表姓名 */
    @Column(name = "name")
    private String name;
    /** 代表手机号 */
    @Column(name = "mobile")
    private String mobile;
    /** 新分组 */
    @Column(name = "tag")
    private String tag;
    /** 大区 */
    @Column(name = "zone")
    private String zone;
    /** 小区 */
    @Column(name = "area")
    private String area;
    /** 大区所属的省份 */
    @Column(name = "province")
    private String province;
    /** 职位 */
    @Column(name = "position")
    private String position;
    /** 扩展，暂无 */
    @Column(name = "ext1")
    private String ext1;
    /** 扩展，暂无 */
    @Column(name = "ext2")
    private String ext2;
    /** 扩展，暂无 */
    @Column(name = "ext3")
    private String ext3;

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tag='" + tag + '\'' +
                ", zone='" + zone + '\'' +
                ", area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", position='" + position + '\'' +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}

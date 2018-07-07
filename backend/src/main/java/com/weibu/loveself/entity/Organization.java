package com.weibu.loveself.entity;

import com.weibu.loveself.common.MybatisBasePagerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

/**
 * 组织结构信息表
 */
@Getter
@Setter
@ToString
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
}

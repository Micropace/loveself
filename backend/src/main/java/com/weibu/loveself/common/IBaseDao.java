package com.weibu.loveself.common;

import java.util.List;

/**
 * 基本的增删改查接口
 * @param <T> T为具体的Entity
 *
 */
public interface IBaseDao<T> {

    /** 创建Entity记录 */
    int create(T object);

    /** 根据ID查询Entity */
    T findById(Long id);

    /** 更新Entity记录，只更新不为null的字段 */
    int update(T object);

    /** 根据ID删除Entity记录，如果有外键关联则会同时删除关联表的所有记录 */
    int deleteById(Long id);

    /** 查询表中所有记录 */
    List<T> findAll();
}

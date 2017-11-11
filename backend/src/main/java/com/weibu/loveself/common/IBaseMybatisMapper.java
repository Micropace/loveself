
package com.weibu.loveself.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Mybatis通用Mapper接口类，包含单表的基础CUID操作
 */
public interface IBaseMybatisMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

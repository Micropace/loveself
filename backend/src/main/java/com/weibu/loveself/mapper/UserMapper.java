package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends IBaseMybatisMapper<User> {

    @Select(value = "select * from user where openid=#{openid}")
    User selectByOpenid(@Param("openid") String openid);
}

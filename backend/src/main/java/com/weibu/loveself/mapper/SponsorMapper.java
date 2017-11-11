package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.Sponsor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SponsorMapper extends IBaseMybatisMapper<Sponsor> {

    @Select(value = "select * from sponsor where mobile=#{mobile}")
    Sponsor selectByMobile(@Param("mobile") String mobile);
}

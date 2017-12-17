package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.Organization;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrganizationMapper extends IBaseMybatisMapper<Organization> {

    @Select(value = "select * from organization where mobile=#{mobile}")
    Organization selectByMobile(@Param("mobile") String mobile);

}

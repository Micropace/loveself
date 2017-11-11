package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.Qrcode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QrcodeMapper extends IBaseMybatisMapper<Qrcode>{

    @Select(value = "select * from qrcode where scene=#{scene}")
    Qrcode selectByScene(@Param("scene") Long scene);
}

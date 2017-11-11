package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuestionMapper extends IBaseMybatisMapper<Question> {

    @Select(value = "select question.* \n" +
            "        from question \n" +
            "            right join qrcode \n" +
            "            on question.id_qrcode = qrcode.id\n" +
            "        where qrcode.scene=#{scene}")
    Question selectByScene(@Param("scene") Long scene);
}

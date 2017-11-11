package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.QuestionConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuestionConfigMapper extends IBaseMybatisMapper<QuestionConfig> {

    @Select(value = "select question_config.*\n" +
            "        from question_config\n" +
            "            right join question\n" +
            "            on question_config.id_question = question.id\n" +
            "            right join qrcode\n" +
            "            on question.id_qrcode = qrcode.id\n" +
            "        where qrcode.scene = #{scene}")
    QuestionConfig selectByScene(@Param("scene") Long scene);
}

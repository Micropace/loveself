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
            "        where question.id = #{idQuestion}")
    QuestionConfig selectByQuestionId(@Param("idQuestion") Long idQuestion);
}

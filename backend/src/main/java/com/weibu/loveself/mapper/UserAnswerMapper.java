package com.weibu.loveself.mapper;

import com.weibu.loveself.common.IBaseMybatisMapper;
import com.weibu.loveself.entity.UserAnswer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAnswerMapper extends IBaseMybatisMapper<UserAnswer> {

    @Select(value = "select user_answer.*\n" +
            "        from user_answer\n" +
            "            right join question\n" +
            "            on user_answer.id_question = question.id\n" +
            "            right join user\n" +
            "            on user_answer.id_user = user.id\n" +
            "            right join qrcode\n" +
            "            on question.id_qrcode = qrcode.id\n" +
            "        where user.openid = #{openid}\n" +
            "        and qrcode.scene = #{scene}")
    UserAnswer selectUserAnswerByScene(@Param("openid") String openid, @Param("scene") Long scene);

    @Select(value = "select * from user_answer where id_question=#{idQuestion}")
    List<UserAnswer> selectUserAnswersByQuestionId(@Param("idQuestion") Long idQuestion);
}

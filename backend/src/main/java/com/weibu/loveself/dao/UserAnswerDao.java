package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.UserAnswer;

import java.util.List;

public interface UserAnswerDao extends IBaseDao<UserAnswer> {

    UserAnswer findUserAnswerByOpenidAndQuestionId(String openid, Long idQuestion);

    List<UserAnswer> selectUserAnswersByQuestionId(Long idQuestion);
}

package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.UserAnswer;

public interface UserAnswerDao extends IBaseDao<UserAnswer> {

    UserAnswer findUserAnswerByScene(String openid, Long scene);
}

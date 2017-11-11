package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.User;

public interface UserDao extends IBaseDao<User> {

    User findByOpenid(String openid);
}

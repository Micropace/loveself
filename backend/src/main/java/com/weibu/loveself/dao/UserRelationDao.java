package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.UserRelation;

public interface UserRelationDao extends IBaseDao<UserRelation> {

    UserRelation findRelation(String openid, String mobile);
}

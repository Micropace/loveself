package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.UserRelationDao;
import com.weibu.loveself.entity.UserRelation;
import com.weibu.loveself.mapper.UserRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UserRelationDaoImpl implements UserRelationDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserRelationMapper userRelationMapper;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int create(UserRelation object) {
        String now = sf.format(new Date());
        object.setCreatedAt(now);
        object.setUpdatedAt(now);
        return userRelationMapper.insert(object);
    }

    @Override
    public UserRelation findById(Long id) {
        return userRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(UserRelation object) {
        String now = sf.format(new Date());
        object.setUpdatedAt(now);
        //更新不为null的字段
        return  userRelationMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return userRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserRelation> findAll() {
        return userRelationMapper.selectAll();
    }

    @Override
    public UserRelation findRelation(String openid, String mobile) {
        return userRelationMapper.selectUserRelation(openid, mobile);
    }
}

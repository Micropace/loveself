package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.UserAnswerDao;
import com.weibu.loveself.entity.UserAnswer;
import com.weibu.loveself.mapper.UserAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UserAnswerDaoImpl implements UserAnswerDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserAnswerMapper userAnswerMapper;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int create(UserAnswer object) {
        String now = sf.format(new Date());
        object.setCreatedAt(now);
        object.setUpdatedAt(now);
        return userAnswerMapper.insert(object);
    }

    @Override
    public UserAnswer findById(Long id) {
        return userAnswerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(UserAnswer object) {
        String now = sf.format(new Date());
        object.setUpdatedAt(now);
        //更新不为null的字段
        return  userAnswerMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return userAnswerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserAnswer> findAll() {
        return userAnswerMapper.selectAll();
    }

    @Override
    public UserAnswer findUserAnswerByScene(String openid, Long scene) {
        return userAnswerMapper.selectUserAnswerByScene(openid, scene);
    }

    @Override
    public List<UserAnswer> selectUserAnswersByQuestionId(Long idQuestion) {
        return userAnswerMapper.selectUserAnswersByQuestionId(idQuestion);
    }
}

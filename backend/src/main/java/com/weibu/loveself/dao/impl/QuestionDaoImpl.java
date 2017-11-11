package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.QuestionDao;
import com.weibu.loveself.entity.Question;
import com.weibu.loveself.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private QuestionMapper questionMapper;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int create(Question object) {
        String now = sf.format(new Date());
        object.setCreatedAt(now);
        object.setUpdatedAt(now);
        return questionMapper.insert(object);
    }

    @Override
    public Question findById(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Question object) {
        String now = sf.format(new Date());
        object.setUpdatedAt(now);
        //更新不为null的字段
        return  questionMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return questionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Question> findAll() {
        return questionMapper.selectAll();
    }

    @Override
    public Question findByScene(Long scene) {
        return questionMapper.selectByScene(scene);
    }
}

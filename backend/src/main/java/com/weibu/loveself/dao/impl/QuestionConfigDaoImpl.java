package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.QuestionConfigDao;
import com.weibu.loveself.entity.QuestionConfig;
import com.weibu.loveself.mapper.QuestionConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class QuestionConfigDaoImpl implements QuestionConfigDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private QuestionConfigMapper questionConfigMapper;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int create(QuestionConfig object) {
        String now = sf.format(new Date());
        object.setCreatedAt(now);
        object.setUpdatedAt(now);
        return questionConfigMapper.insert(object);
    }

    @Override
    public QuestionConfig findById(Long id) {
        return questionConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(QuestionConfig object) {
        String now = sf.format(new Date());
        object.setUpdatedAt(now);
        //更新不为null的字段
        return  questionConfigMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return questionConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<QuestionConfig> findAll() {
        return questionConfigMapper.selectAll();
    }

    @Override
    public QuestionConfig findByQuestionId(Long idQUestion) {
        return questionConfigMapper.selectByQuestionId(idQUestion);
    }
}

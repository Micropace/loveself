package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.QuestionConfig;

public interface QuestionConfigDao extends IBaseDao<QuestionConfig> {

    QuestionConfig findByQuestionId(Long idQUestion);
}

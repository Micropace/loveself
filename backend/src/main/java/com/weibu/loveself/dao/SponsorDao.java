package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.Sponsor;

public interface SponsorDao extends IBaseDao<Sponsor> {

    Sponsor findByMobile(String mobile);
}

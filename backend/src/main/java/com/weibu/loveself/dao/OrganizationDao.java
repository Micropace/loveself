package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.Organization;

public interface OrganizationDao extends IBaseDao<Organization> {

    Organization findByMobile(String mobile);
}

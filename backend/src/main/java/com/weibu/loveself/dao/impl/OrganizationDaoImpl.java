package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.OrganizationDao;
import com.weibu.loveself.entity.Organization;
import com.weibu.loveself.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OrganizationMapper organizationMapper;

    @Override
    public Organization findByMobile(String mobile) {
        return organizationMapper.selectByMobile(mobile);
    }

    @Override
    public int create(Organization object) {
        return organizationMapper.insert(object);
    }

    @Override
    public Organization findById(Long id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Organization object) {
        return organizationMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return organizationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Organization> findAll() {
        return organizationMapper.selectAll();
    }
}

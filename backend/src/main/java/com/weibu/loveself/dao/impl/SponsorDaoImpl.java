package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.SponsorDao;
import com.weibu.loveself.entity.Sponsor;
import com.weibu.loveself.mapper.SponsorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SponsorDaoImpl implements SponsorDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SponsorMapper sponsorMapper;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int create(Sponsor object) {
        String now = sf.format(new Date());
        object.setCreatedAt(now);
        object.setUpdatedAt(now);
        return sponsorMapper.insert(object);
    }

    @Override
    public Sponsor findById(Long id) {
        return sponsorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Sponsor object) {
        String now = sf.format(new Date());
        object.setUpdatedAt(now);
        //更新不为null的字段
        return  sponsorMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return sponsorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Sponsor> findAll() {
        return sponsorMapper.selectAll();
    }

    @Override
    public Sponsor findByMobile(String mobile) {
        return sponsorMapper.selectByMobile(mobile);
    }
}

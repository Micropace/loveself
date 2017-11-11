package com.weibu.loveself.dao.impl;

import com.weibu.loveself.dao.QrcodeDao;
import com.weibu.loveself.entity.Qrcode;
import com.weibu.loveself.mapper.QrcodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class QrcodeDaoImpl implements QrcodeDao {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private QrcodeMapper qrcodeMapper;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int create(Qrcode object) {
        String now = sf.format(new Date());
        object.setCreatedAt(now);
        object.setUpdatedAt(now);
        return qrcodeMapper.insert(object);
    }

    @Override
    public Qrcode findById(Long id) {
        return qrcodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Qrcode object) {
        String now = sf.format(new Date());
        object.setUpdatedAt(now);
        //更新不为null的字段
        return  qrcodeMapper.updateByPrimaryKeySelective(object);
    }

    @Override
    public int deleteById(Long id) {
        return qrcodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Qrcode> findAll() {
        return qrcodeMapper.selectAll();
    }

    @Override
    public Qrcode findByScene(Long scene) {
        return qrcodeMapper.selectByScene(scene);
    }
}

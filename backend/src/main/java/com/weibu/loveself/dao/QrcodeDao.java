package com.weibu.loveself.dao;

import com.weibu.loveself.common.IBaseDao;
import com.weibu.loveself.entity.Qrcode;

public interface QrcodeDao extends IBaseDao<Qrcode> {

    Qrcode findByScene(Long scene);
}

package com.weibu.loveself.service;

import java.util.Map;

/**
 * 小程序码获取接口
 */
public interface IQrCodeService {

    /**
     * 获取小程序二维码图片
     * @param scene 场景值，数字
     * @return 结果信息
     */
    Map<String, String> getWxaQrCode(Long scene);
}

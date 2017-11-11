package com.weibu.loveself.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxMaProperties {
    /** 设置微信小程序的appid */
    @Value("${wechat.miniapp.appid}")
    private String appid;
    /** 设置微信小程序的Secret */
    @Value("${wechat.miniapp.secret}")
    private String secret;
    /** 设置微信小程序的token */
    @Value("${wechat.miniapp.token}")
    private String token;
    /** 设置微信小程序的EncodingAESKey */
    @Value("${wechat.miniapp.aesKey}")
    private String aesKey;
    /** 消息格式，XML或者JSON */
    @Value("${wechat.miniapp.msgDataFormat}")
    private String msgDataFormat;

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    public String getToken() {
        return token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public String getMsgDataFormat() {
        return msgDataFormat;
    }
}

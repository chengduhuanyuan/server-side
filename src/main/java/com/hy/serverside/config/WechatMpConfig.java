package com.hy.serverside.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WechatMpConfig
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/14 11:18
 * @Version: 1.0
 */
@Component
public class WechatMpConfig {
    @Autowired
    private WechatAccountConfig accountConfig;
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxConfigProvider());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxConfigProvider(){
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(accountConfig.getAppid());
        wxConfigProvider.setSecret(accountConfig.getSecret());
        return wxConfigProvider;
    }
}

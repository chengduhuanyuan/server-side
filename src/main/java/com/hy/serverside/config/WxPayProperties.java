package com.hy.serverside.config;

import com.hy.serverside.util.Constant;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: WxPayProperties
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/27 9:55
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {
    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId ;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    private String notifyUrl;

    private String tradeType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}

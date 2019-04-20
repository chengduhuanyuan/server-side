package com.hy.serverside.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.hy.serverside.util.Constant;

import java.io.*;

/**
 * @ClassName: WeChatPayConfig
 * @Description: TODO 微信支付配置文件
 * @Author: Kaiser
 * @Date: 2019/4/19 13:04
 * @Version: 1.0
 */
public class WeChatPayConfig implements WXPayConfig {

    private byte[] certData;

    public WeChatPayConfig(){
        String certPath = "D:\\apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = null;
        try {
            certStream = new FileInputStream(file);
            this.certData = new byte[(int) file.length()];
            certStream.read(this.certData);
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                certStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getAppID() {
        return Constant.APPID;
    }

    @Override
    public String getMchID() {
        return Constant.MCHID;
    }

    @Override
    public String getKey() {
        return Constant.PAY_KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certs = new ByteArrayInputStream(this.certData);
        return certs;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}

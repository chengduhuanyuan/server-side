package com.hy.serverside.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.hy.serverside.util.Constant;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @ClassName: WeChatPayConfig
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/25 17:08
 * @Version: 1.0
 */
@Component
public class WeChatPayConfig implements WXPayConfig {
    private byte[] certData;

    public WeChatPayConfig() {
        try {
            File file = new File("D:" + File.separator + "usr" + File.separator + "cert" + File.separator, "apiclient_cert.p12");
            InputStream certStream = new FileInputStream(file);
            this.certData = new byte[(int) file.length()];
            certStream.read(this.certData);
            certStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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

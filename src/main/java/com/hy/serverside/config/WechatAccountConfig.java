package com.hy.serverside.config;

import com.hy.serverside.util.Constant;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WechatAccountConfi
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/14 11:16
 * @Version: 1.0
 */
@Component
@Data
public class WechatAccountConfig {
    private String appid = Constant.WECHAT_APPID_VALUE;
    private String secret = Constant.WECHAT_SECRET_VALUE;
}

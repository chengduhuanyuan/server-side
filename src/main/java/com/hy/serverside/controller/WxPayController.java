package com.hy.serverside.controller;

//import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
//import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
//import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
//import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
//import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
//import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
//import com.github.binarywang.wxpay.exception.WxPayException;
//import com.github.binarywang.wxpay.service.WxPayService;
import com.github.wxpay.sdk.WXPay;
import com.hy.serverside.config.WeChatPayConfig;
import com.hy.serverside.config.WxPayService;
import com.hy.serverside.entity.PayBean;
import com.hy.serverside.util.Constant;
import com.hy.serverside.util.JsonData;
import com.hy.serverside.util.NetworkInterfaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WxPayController
 * @Description: TODO
 * @Author: Kaiser
 * @Date: 2019/5/25 14:34
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/pay")
public class WxPayController {
    private WeChatPayConfig config = new WeChatPayConfig();
    private WXPay wxpay = new WXPay(config);
    @ResponseBody
    @PostMapping("/createOrder")
    public JsonData createOrder(@RequestBody PayBean pay) throws Exception {
        Map<String, String> data = new HashMap<>(8);
        data.put("body", pay.getBody());
        data.put("out_trade_no", pay.getOutTradeNo());
        data.put("fee_type", Constant.FEE_TYPE);
        data.put("total_fee", pay.getTotalFee());
        data.put("spbill_create_ip", "171.214.136.238");
        data.put("notify_url", Constant.PAY_NOTIFY_URL);
        data.put("trade_type", Constant.TRADE_TYPE);
        data.put("openid",pay.getOpenid());
        Map<String, String> resp = wxpay.unifiedOrder(data);
        return new JsonData(resp,"ss",true);
    }
}

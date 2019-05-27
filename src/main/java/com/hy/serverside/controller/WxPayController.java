package com.hy.serverside.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.hy.serverside.config.WeChatPayConfig;
import com.hy.serverside.entity.PayBean;
import com.hy.serverside.util.Constant;
import com.hy.serverside.util.JsonData;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    //    private WxPayService wxService;

//    @Autowired
//    public WxPayController(WxPayService wxService) {
//        this.wxService = wxService;
//    }

    private WeChatPayConfig config = new WeChatPayConfig();
    private WXPay wxpay = new WXPay(config);


    /**
     *  统一下单
     * @param pay 支付请求函数
     * @return
     * @throws Exception
     */
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


//    /**
//     *  统一下单
//     * @param pay 支付请求函数
//     * @return
//     * @throws Exception
//     */
//    @ResponseBody
//    @PostMapping("/createOrder")
//    public JsonData createOrder(@RequestBody PayBean pay){
//        try {
//            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//            orderRequest.setBody(pay.getBody());
//            orderRequest.setOutTradeNo(pay.getOutTradeNo());
//            orderRequest.setNotifyUrl(Constant.PAY_NOTIFY_URL);
//            orderRequest.setTradeType(Constant.TRADE_TYPE);
//            //元转成分
//            orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(pay.getTotalFee()));
//            orderRequest.setOpenid(pay.getOpenid());
//            orderRequest.setSpbillCreateIp("171.214.136.238");
//            orderRequest.setTimeStart("yyyyMMddHHmmss");
//            orderRequest.setTimeExpire("yyyyMMddHHmmss");
//            WxPayUnifiedOrderResult result = wxService.unifiedOrder(orderRequest);
//            return new JsonData(result,"success",true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new JsonData(null,"fail",false);
//        }
//    }

    @ResponseBody
    @PostMapping("/notify/order")
    public String notifyUrl(HttpServletRequest request){
        try {
            InputStream inStream = request.getInputStream();
            int bufferrSize = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[bufferrSize];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, bufferrSize)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                //将流转换成字符串
                String result = new String(outStream.toByteArray(), "UTF-8");
                Map<String, String> notifyMap = WXPayUtil.xmlToMap(result);
                //验证签名
                if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                    // 签名正确
                    // 进行处理。
                    // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                    //通知微信支付系统接收到信息
                    return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                }
                else {
                    // 签名错误，如果数据里没有sign字段，也认为是签名错误
                    return "fail";
                }
            }
            return "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

//    @PostMapping("/notify/order")
//    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
//        final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
//        // TODO 根据自己业务场景需要构造返回对象
//        return WxPayNotifyResponse.success("成功");
//    }
}

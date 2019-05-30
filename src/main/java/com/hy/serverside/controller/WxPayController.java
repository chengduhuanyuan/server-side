package com.hy.serverside.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.hy.serverside.config.WeChatPayConfig;
import com.hy.serverside.entity.PayBean;
import com.hy.serverside.util.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
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

//    private WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5, true);

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
        data.put("total_fee", new DecimalFormat("#.00").format(Double.parseDouble(pay.getTotalFee())));
        data.put("spbill_create_ip", NetworkInterfaceUtil.getMyIp());
        data.put("notify_url", Constant.PAY_NOTIFY_URL);
        data.put("trade_type", Constant.TRADE_TYPE);
        data.put("openid",pay.getOpenid());
        Map<String, String> resp = wxpay.unifiedOrder(data);
        if(Constant.TRADE.equals(resp.get(Constant.RETURN_CODE)) && Constant.TRADE.equals(resp.get(Constant.RESULT_CODE))){
            //生成支付签名
            String prepayId = resp.get("prepay_id");
            String nonceStr = WXPayUtil.generateNonceStr();
            long timeStamp = TimeUtil.getSignTimeStmap();
            String signType = "MD5";
            Map<String, String> map = new HashMap<>(5);
            map.put("appId",Constant.APPID);
            map.put("timeStamp",String.valueOf(timeStamp));
            map.put("nonceStr",nonceStr);
            map.put("package","prepay_id=".concat(prepayId));
            map.put("signType",signType);
            String signature = WXPayUtil.generateSignature(map, Constant.PAY_KEY);
            map.put("paySign",signature);
            return new JsonData(map,"success",true);
        }
        return new JsonData(resp,"fail",false);
    }

    /**
     *  支付回调
     * @param request
     * @return
     */
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
                if (Constant.TRADE.equals(notifyMap.get(Constant.RETURN_CODE))){
                    //验证签名
                    if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                        // 签名正确
                        // 进行处理
                        // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                        //通知微信支付系统接收到信息
                        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                    }
                }
            }
            return "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     *  关闭订单
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/closeOrder")
    public JsonData closeOrder(@RequestBody String outTradeNo) throws Exception {
        Map<String, String> data = new HashMap<>(1);
        data.put("out_trade_no", outTradeNo);
        Map<String, String> map = wxpay.closeOrder(data);
        return GetResult.payResult(map);
    }

    /**
     *  订单查询
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/queryOrder")
    public JsonData queryOrder(@RequestBody String outTradeNo) throws Exception {
        Map<String, String> data = new HashMap<>(1);
        data.put("out_trade_no", outTradeNo);
        Map<String, String> orderQuery = wxpay.orderQuery(data);
        if (Constant.TRADE.equals(orderQuery.get(Constant.RETURN_CODE)) && Constant.TRADE.equals(orderQuery.get(Constant.RESULT_CODE)) && Constant.TRADE.equals(orderQuery.get(Constant.TRADE_STATE))){
            return new JsonData(orderQuery,"success",true);
        }
        return new JsonData(orderQuery,"fail",false);
    }

    /**
     *  撤销订单
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/reverseOrder")
    public JsonData reverseOrder(@RequestBody String outTradeNo) throws Exception {
        Map<String, String> da = new HashMap<>(1);
        da.put("out_trade_no", outTradeNo);
        Map<String, String> or = wxpay.orderQuery(da);
        return GetResult.payResult(or);
    }

}

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
        data.put("total_fee", pay.getTotalFee());
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
    public String notifyUrl(HttpServletRequest request) {
        String resSuccessXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        String resFailXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";

        InputStream inStream;
        try {
            inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            System.out.println("wxnotify:微信支付----start----");
            // 获取微信调用我们notify_url的返回信息
            String result = new String(outSteam.toByteArray(), "utf-8");
            System.out.println("wxnotify:微信支付----result----=" + result);
            // 关闭流
            outSteam.close();
            inStream.close();
            // xml转换为map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            System.out.println("获取到的数据是："+resultMap);
            if (WXPayConstants.SUCCESS.equalsIgnoreCase(resultMap.get(Constant.RETURN_CODE))) {

                System.out.println("wxnotify:微信支付----返回成功");

                if (WXPayUtil.isSignatureValid(resultMap, Constant.PAY_KEY)) {

                    // 订单处理 操作 orderconroller 的回写操作?
                    System.out.println("wxnotify:微信支付----验证签名成功");

                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    return resSuccessXml;

                } else {
                    System.out.println("wxnotify:微信支付----判断签名错误");
                    return resFailXml;
                }

            } else {
                System.out.println("wxnotify:支付失败,错误信息：" + resultMap.get(Constant.RETURN_MSG));
                return resFailXml;
            }

            // 付款记录修改 & 记录付款日志

//            // 回调方法，处理业务 - 修改订单状态
//            WXPayUtil.getLogger().info("wxnotify:微信支付回调：修改的订单===>" + resultMap.get("out_trade_no"));
//            int updateResult = ...;
//            if (updateResult > 0) {
//                WXPayUtil.getLogger().info("wxnotify:微信支付回调：修改订单支付状态成功");
//            } else {
//                WXPayUtil.getLogger().error("wxnotify:微信支付回调：修改订单支付状态失败");
//            }

        } catch (Exception e) {
            System.out.println("wxnotify:支付回调发布异常：" + e);
            return resFailXml;
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

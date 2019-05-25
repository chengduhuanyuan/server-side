package com.hy.serverside.config;

import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WxPayService
 * @Description: TODO 微信支付工具类
 * @Author: Kaiser
 * @Date: 2019/4/20 9:08
 * @Version: 1.0
 */
@Component
public class WxPayService {
    @Autowired
    private WeChatPayConfig weChatPayConfig;
    private WXPay wxPay = new WXPay(weChatPayConfig);
    /**
     *  统一下单
     * @param body 商品描述
     * @param outTradeNo 商户订单号
     * @param totalFee 标价金额 单位分
     * @param spbillCreateIp 终端IP
     * @param notifyUrl 通知地址
     * @param tradeType 交易类型
     * @return 支付结果集
     */
    public Map<String,String> unifiedOrder(String body,String outTradeNo, String totalFee,String spbillCreateIp,
                                           String notifyUrl,String tradeType,String openid) throws Exception {
        Map<String,String> data = new HashMap<>(8);
        data.put("body", body);
        data.put("out_trade_no", outTradeNo);
        data.put("fee_type", "CNY");
        data.put("total_fee", totalFee);
        data.put("spbill_create_ip", spbillCreateIp);
        data.put("notify_url", notifyUrl);
        data.put("trade_type", tradeType);
        data.put("openid", openid);
        System.out.println("将数据放入MAP");
        Map<String, String> stringMap = wxPay.unifiedOrder(data);
        System.out.println("ssss"+stringMap);
        return wxPay.unifiedOrder(data);
    }

    /**
     *  订单查询
     * @param outTradeNo 订单号
     * @return 查询结果
     * @throws Exception
     */
    public Map<String,String> orderQuery(String outTradeNo) throws Exception {
        Map<String,String> map = new HashMap<>(1);
        map.put("out_trade_no",outTradeNo);
        return wxPay.orderQuery(map);
    }

    /**
     *  退款查询
     * @param outTradeNo 订单号
     * @return
     * @throws Exception
     */
    public Map<String,String> refundQuery(String outTradeNo) throws Exception {
        Map<String,String> map = new HashMap<>(1);
        map.put("out_trade_no",outTradeNo);
        return wxPay.refundQuery(map);
    }

    /**
     *  关闭订单
     * @param outTradeNo 订单号
     * @return
     * @throws Exception
     */
    public Map<String,String> closeOrder(String outTradeNo) throws Exception {
        Map<String,String> map = new HashMap<>(1);
        map.put("out_trade_no",outTradeNo);
        return wxPay.closeOrder(map);
    }

    /**
     *  撤销订单
     * @param outTradeNo 订单号
     * @return
     * @throws Exception
     */
    public Map<String,String> reverseOrder(String outTradeNo) throws Exception {
        Map<String,String> map = new HashMap<>(1);
        map.put("out_trade_no",outTradeNo);
        return wxPay.reverse(map);
    }
    /**
     *  下载对账单
     * @param billDate 账单日期
     * @param billType 账单类型
     * @return
     * @throws Exception
     */
    public Map<String,String> downloadBill(String billDate,String billType) throws Exception {
        Map<String,String> data = new HashMap<>(2);
        data.put("bill_date",billDate);
        data.put("bill_type",billType);
        return wxPay.downloadBill(data);
    }


}

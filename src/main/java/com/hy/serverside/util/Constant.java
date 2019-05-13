package com.hy.serverside.util;

/**
 * @ClassName: Constant
 * @Description: TODO 常量类
 * @Author: Kaiser
 * @Date: 2019/4/17 10:39
 * @Version: 1.0
 */
public final class Constant {

    public static final String CHECK_CODE = "0";

    public static final String CODE2SESSION_GRANT_TYPE = "authorization_code";
    public static final String APPID = "wxd9bf4fc56d78d0f8";
    public static final String APPSECRET = "4955c8d535378e8d1dd61995b7db45ae";
    public static final String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final int STATUS_CODE_SUCCESS = 200;
    public static final String HTTP_CHARACTER = "UTF-8";
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String ACCESS_TOKEN_GRANT_TYPE = "client_credential";
    public static final String CODE_URL="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    public static final String LANG_VALUE = "zh_CN";
    /**
     *  key
     */
    public static final String ACCESS_TOKEN_KEY = "accessTokenKey";
    public static final String GRANT_TYPE_KEY = "grant_type";
    public static final String APPID_KEY = "appid";
    public static final String APPSECRET_KEY = "secret";
    public static final String JS_CODE_KEY = "js_code";
    public static final String NAME_KEY = "name";
    public static final String LANG = "lang";
    /**
     *  支付密匙
     */
    public static final String PAY_KEY = "";
    /**
     *  商户号
     */
    public static final String MCHID = "asdasd";
    /**
     * 标价币种
     */
    public static final String FEE_TYPE = "CNY";
    /**
     *  交易类型，小程序指定JSAPI
     */
    public static final String TRADE_TYPE = "JSAPI";

    public static final String WECHAT_GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    public static final String WECHAT_CODE2TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String WECHAT_CHECKTOKEN_URL = "https://api.weixin.qq.com/sns/auth";
    public static final String WECHAT_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    public static final String WECHAT_GETUSERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    /**
     *  公众号常量值
     */
    public static final String WECHAT_APPID_VALUE = "wx4090b3ba169287ef";
    public static final String WECHAT_REDIRECT_URI_VALUE = "http://www.kaidi.work/#/info";
    public static final String WECHAT_RESPONSE_TYPE_VALUE = "code";
    public static final String WECHAT_SCOPE_VALUE = "snsapi_userinfo";
    public static final String WECHAT_STATE_VALUE = "#wechat_redirect";
    public static final String WECHAT_SECRET_VALUE = "ded712bd96af9befcb4105c6a2f37261";
    public static final String WECHAT_GRANT_TYPE_VALUE = "authorization_code";
    public static final String WECHAT_GRANT_TYPE_REFRESH_VALUE = "refresh_token";

    /**
     *  公众号常量key
     */
    public static final String WECHAT_REDIRECT_URI_KEY = "redirect_uri";
    public static final String WECHAT_RESPONSE_TYPE_KEY = "response_type";
    public static final String WECHAT_SCOPE_KEY = "scope";
    public static final String WECHAT_STATE_KEY = "state";
    public static final String WECHAT_CODE_KEY = "code";
    public static final String WECHAT_ACCESS_TOKEN_KEY = "access_token";
    public static final String WECHAT_OPENID_KEY = "openid";
    public static final String WECHAT_ERRCODE = "errcode";
    public static final String WECHAT_CHECK_SCOPE = "snsapi_userinfo";
    public static final String WECHAT_REFRESH_TOKEN_KEY = "refresh_token";
}

package com.wxphoto.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;

import com.wxphoto.servlet.BaseServlet;

public class WXJsapiticket {

	   private static org.apache.log4j.Logger logger = LogManager.getLogger(WXJsapiticket.class);
	    /**
	     * 微信jsapi验签
	     * @param url
	     * @return
	     */
	    public static Map<String, String> jsApiSign(String url) {
	        Map<String, String> ret = new HashMap<String, String>();
	        String nonce_str = CheckUtil.create_nonce_str();
	        String timestamp = CheckUtil.create_timestamp();
	        String jsapi_ticket = getJsApiTicket();
	        String string1 = CheckUtil.getString1(nonce_str,timestamp,jsapi_ticket,url);
	        String signature = CheckUtil.getSha1(string1);
	        ret.put("appid", BaseServlet.getAPPID());//取你自己的公众号appid
	        ret.put("url", url);
	        ret.put("jsapi_ticket", jsapi_ticket);
	        ret.put("nonceStr", nonce_str);
	        ret.put("timestamp", timestamp);
	        ret.put("signature", signature);
	        logger.info("jsApiSign------url=" + url + "-----jsapi_ticket=" + jsapi_ticket + "--------nonceStr=" + nonce_str + "--------timestamp=" + timestamp + "-------signature=" + signature);
	        return ret;
	    }
	    public static String getJsApiTicket(){
	        Map<String,Object> map = JsApiTicketCache.getInstance().getJsApiTicketAndExpiresIn();
	        return (String) map.get("jsapi_ticket");
	    }
}

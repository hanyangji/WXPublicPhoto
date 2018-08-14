package com.wxphoto.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;

import com.wxphoto.entity.JsapiTicket;

public class JsApiTicketCache {
	private static org.apache.log4j.Logger logger = LogManager.getLogger(JsApiTicketCache.class);

	//缓存jsapi_ticket的Map,map中包含jsapiTicket,expiresIn和缓存的时间戳time
    private Map<String, String> map = new HashMap<String,String>();
    private static JsApiTicketCache jsApiTicketCache = null;
    private JsApiTicketCache() { }
    // 静态工厂方法
    public static JsApiTicketCache getInstance() {
        if (jsApiTicketCache == null) {
            jsApiTicketCache = new JsApiTicketCache();
        }
        return jsApiTicketCache;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    /**
     * 获取 jsapi_ticket expires_in
     * @return
     */
    public Map<String,Object> getJsApiTicketAndExpiresIn() {
        Map<String,Object> result = new HashMap<String,Object>();
        JsApiTicketCache jsApiTicketCache = JsApiTicketCache.getInstance();
        Map<String, String> map = jsApiTicketCache.getMap();
        String time = map.get("time");
        String jsapiTicket = map.get("jsapi_ticket");
        String expiresIn = map.get("expires_in");
        Long nowDate = new Date().getTime();
        if (jsapiTicket != null && time != null && expiresIn!=null) {
            //这里设置过期时间为微信规定的过期时间减去5分钟
            int outTime = (Integer.parseInt(expiresIn)-300) * 1000;
            if (nowDate - Long.parseLong(time) < outTime) {
                logger.info("-----从缓存读取jsapi_ticket：" + jsapiTicket);
                //从缓存中拿数据为返回结果赋值
                result.put("jsapi_ticket", jsapiTicket);
                result.put("expires_in", expiresIn);
            }else {
            		WeiXinUtil wxu=new WeiXinUtil();
                JsapiTicket info = wxu.getJsapiTicket();//实际中这里要改为你自己调用微信接口去获取jsapi_ticket和expires_in
                logger.info("----Ticket超时，调用微信接口获取jsapi_ticket：" + info.getJsapiTicket());
                //将信息放置缓存中
                map.put("time", nowDate + "");
                map.put("jsapi_ticket", info.getJsapiTicket());
                map.put("expires_in", info.getExpiresIn()+"");
                //为返回结果赋值
                result.put("jsapi_ticket", info.getJsapiTicket());
                result.put("expires_in", info.getExpiresIn());
            }
        } else {
        		WeiXinUtil wxu=new WeiXinUtil();
            JsapiTicket info = wxu.getJsapiTicket();//实际中这里要改为你自己调用微信接口去获取jsapi_ticket和expires_in
            logger.info("-----通过调用微信接口获取jsapi_ticket：" + info.getJsapiTicket());
            //将信息放置缓存中
            map.put("time", nowDate + "");
            map.put("jsapi_ticket", info.getJsapiTicket());
            map.put("expires_in", info.getExpiresIn()+"");
            //为返回结果赋值
            result.put("jsapi_ticket", info.getJsapiTicket());
            result.put("expires_in", info.getExpiresIn());
        }
        return result;
    }

}

package com.wxphoto.util;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.wxphoto.entity.JsapiTicket;
import com.wxphoto.servlet.BaseServlet;

public class WeiXinUtil extends BaseServlet{
	 
	public JsapiTicket getJsapiTicket(){
			String access_token = getAccessToken();
			String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
			String params = "access_token=" + access_token + "&type=jsapi";
			String res = HttpsREQUEST.sendGet(requestUrl,params);
			String jsapi_ticket = JSONObject.parseObject(res).getString("ticket");
			int activeTime=Integer.parseInt(JSONObject.parseObject(res).getString("expires_in"));
			JsapiTicket js=new JsapiTicket();
			js.setExpiresIn(activeTime);
			js.setJsapiTicket(jsapi_ticket);
//			Jssdk jssdk = new Jssdk();
//			jssdk.setActiveTime(activeTime-600);
//			jssdk.setJsapiTicket(jsapi_ticket);
//			jssdkDao.update(jssdk);
			return js;
		}
//		private Jssdk getSign(String url,String jsapi_ticket) {
//			String noncestr =  getNonceStr();
//			String timestamp =  getTimeStamp();
//			String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "×tamp=" + timestamp + "&url=" + url;
//			sign = Sha1Util.getSha1(sign);
//			Jssdk jssdk = new Jssdk();
//	        jssdk.setAppid(appid);
//	        jssdk.setNoncestr(noncestr);
//	        jssdk.setTimestamp(timestamp);
//	        jssdk.setSign(sign);
//			return jssdk;
//		}
//	private String getNonceStr() {
//	 Random random = new Random();
//	        return MD5Util.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
//	 }
//	 
//	 private String getTimeStamp() {
//	 return String.valueOf(System.currentTimeMillis() / 1000);
//	 }
		public static String getAccessToken() {
			String request = "https://api.weixin.qq.com/cgi-bin/token";
			String param = "grant_type=client_credential&appid=" + getAPPID()+ "&secret=" + getAPPSECET() + "";
			String result = HttpsREQUEST.sendGet(request,param);
			String access_token = JSONObject.parseObject(result).getString("access_token");
			return access_token;
		}
	    /**
	     * 获取临时素材
	     */
	    public static InputStream getMedia(String mediaId) {
	        String url = "https://api.weixin.qq.com/cgi-bin/media/get";
	        String access_token = WeiXinUtil.getAccessToken();
	        String params = "access_token=" + access_token + "&media_id=" + mediaId;
	           InputStream is = null;
	           try {
	            String urlNameString = url + "?" + params;
	               URL urlGet = new URL(urlNameString);
	               HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	               http.setRequestMethod("GET"); // 必须是get方式请求
	               http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	               http.setDoOutput(true);
	               http.setDoInput(true);
	               http.connect();
	               // 获取文件转化为byte流
	               is = http.getInputStream();
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	        return is;
	    }

}

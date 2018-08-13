package com.wxphoto.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class WeixinSignUtil {

	 @SuppressWarnings("rawtypes")
	    public static String createSignBySha1(SortedMap<Object, Object> params) {
	        StringBuffer sb = new StringBuffer();
	        Set es = params.entrySet();
	        Iterator it = es.iterator();
	        while (it.hasNext()) {
	            Map.Entry entry = (Map.Entry) it.next();
	            String k = (String) entry.getKey();
	            String v = (String) entry.getValue();
	            if (v != null && !v.equals("")) {
	                sb.append(k + "=" + v + "&");
	            }
	        } 
	        String result = sb.toString().substring(0, sb.toString().length()-1);
	        return getSha1(result);
	    }

	    /**
	     * 获取时间戳(秒)
	     */
	    public static String getTimestamp() {
	        return String.valueOf(System.currentTimeMillis() / 1000);
	    }

	    /** 
	     * 取出一个指定长度大小的随机正整数. 
	     * @param length 
	     *            int 设定所取出随机数的长度。length小于11 
	     * @return int 返回生成的随机数。 
	     */  
	    public static int buildRandom(int length) {  
	        int num = 1;  
	        double random = Math.random();  
	        if (random < 0.1) {  
	            random = random + 0.1;  
	        }  
	        for (int i = 0; i < length; i++) {  
	            num = num * 10;  
	        }  
	        return (int) ((random * num));  
	    }  

	    /** 
	     * 获取当前时间 yyyyMMddHHmmss 
	     */  
	    public static String getCurrTime() {  
	        Date now = new Date();  
	        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
	        String s = outFormat.format(now);  
	        return s;  
	    }  

	    /**
	     * 生成随机字符串
	     */ 
	    public static String getNonceStr() {
	        String currTime = getCurrTime();  
	        String strTime = currTime.substring(8, currTime.length());  
	        String strRandom = buildRandom(4) + "";  
	        return strTime + strRandom;
	    }
	    /**
	     * Sha1加密方法
	     * @param str
	     * @return
	     */
	    public static String getSha1(String str) {
	        if (str == null || str.length() == 0) {
	            return null;
	        }
	        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                'a', 'b', 'c', 'd', 'e', 'f' };

	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	            mdTemp.update(str.getBytes("UTF-8"));

	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char buf[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                buf[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(buf);
	        } catch (Exception e) {
	            return null;
	        }
	    }
}

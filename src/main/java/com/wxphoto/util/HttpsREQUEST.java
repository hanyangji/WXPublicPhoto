package com.wxphoto.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class HttpsREQUEST {

	/**
     * 发送https请求
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outStr
     *            提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static String HttpsRequest(String requestUrl, String requestMethod, String outStr) throws Exception{
//        创建 SSLContext
        SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
        TrustManager[] trustManagers = {new MyX509TrustManager()};
//        初始化
        sslContext.init(null,trustManagers,new SecureRandom());
//        获取 sslSocketFactory 对象
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//        设置当前实例使用

        StringBuffer buffer = null;
//      传递的URL
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
//      请求方式
        connection.setRequestMethod(requestMethod);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setSSLSocketFactory(sslSocketFactory);
        connection.connect();

        if (outStr != null){
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(outStr.getBytes("utf-8"));
            outputStream.close();
        }

//        读取服务端内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        buffer = new StringBuffer();
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            buffer.append(line);
        }
//        System.out.println(buffer.toString());
        return buffer.toString();
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}

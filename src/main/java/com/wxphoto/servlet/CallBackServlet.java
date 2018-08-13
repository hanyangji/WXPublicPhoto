package com.wxphoto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxphoto.entity.Info;
import com.wxphoto.entity.Token;
import com.wxphoto.util.HttpsREQUEST;

@WebServlet("/callBack")
public class CallBackServlet extends BaseServlet {

	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String code = req.getParameter("code");
	        System.out.println("code:" + code);

//	        页面授权第二步，通过code换取 access_token openid
	        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + getAPPID()
	                + "&secret=" + getAPPSECET()
	                + "&code=" + code
	                + "&grant_type=authorization_code";
	        String s = null;
	        try {
//	            发送HTTPS请求, GET请求这个文档有写，然后注意是GET或者POST都是大写 没有参数所以最后一个为null，
	            s = HttpsREQUEST.HttpsRequest(url, "GET", null);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//	        Gson的使用 ：Json字符串转化为到java实体的
	        Token token = gson.fromJson(s, Token.class);
//	        实体类的封装获取
	        String access_token = token.getAccess_token();
	        System.out.println("access_token:"+access_token);
	        String openid = token.getOpenid();
	        System.out.println("openid:"+openid);
//	        页面授权第四步，通过 access_token openid 换取 用户信息  下面就和上面的一样了
	        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token
	                + "&openid=" + openid
	                + "&lang=zh_CN ";
	        String info = null;
	        try {
	            info = HttpsREQUEST.HttpsRequest(infoUrl, "GET", null);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Info infos = gson.fromJson(info, Info.class);

	        //1、使用用户信息直接登录，无需注册和绑定
	        req.setAttribute("info", infos);
	        req.getRequestDispatcher("/photoindex.jsp").forward(req, resp);
	    }

}

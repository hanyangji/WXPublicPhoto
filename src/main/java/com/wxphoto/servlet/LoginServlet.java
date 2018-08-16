package com.wxphoto.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wxLogin")
public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//                String backUrl = "http://hanyangji.free.ngrok.cc/WXPublicPhoto/callBack";
                String backUrl = "http://wx.pay.zj.cn/WXPublicPhoto/callBack";
                String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+getAPPID()
                        + "&redirect_uri="+URLEncoder.encode(backUrl)
                        + "&response_type=code"
                        + "&scope=snsapi_userinfo"
                        + "&state=STATE#wechat_redirect";
                resp.sendRedirect(url);
    }
}

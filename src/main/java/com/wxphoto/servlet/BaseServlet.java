package com.wxphoto.servlet;

import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet {

//    private static String APPID = "wx8ebf6aeb679126cb";
//    private static String APPSECET = "7bb841ef32055da1182fb69070e0a21d";
    private static String APPID = "wxd3f75dc99fd78732";
    private static String APPSECET = "e5f3e9fd3bdb918e42f86fc9662deb75";

    Gson gson = new Gson();

    public static String getAPPID() {
        return APPID;
    }

    public static String getAPPSECET() {
        return APPSECET;
    }
}

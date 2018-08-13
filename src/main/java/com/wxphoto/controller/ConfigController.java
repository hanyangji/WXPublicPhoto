package com.wxphoto.controller;

import java.io.BufferedOutputStream;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wxphoto.entity.MchtImage;
import com.wxphoto.service.MchtUploadImageService;
import com.wxphoto.util.WXJsapiticket;

import net.sf.json.JSONObject;

@Controller
public class ConfigController {
	private static org.apache.log4j.Logger logger = LogManager.getLogger(ConfigController.class);
	
	@Autowired
	private MchtUploadImageService muis;

	/**
	 * 微信验签
	 * 
	 * @param url
	 * @return
	 * @return
	 */
	@RequestMapping("jsapisign")
	public @ResponseBody Map<String, String> jsApiSign(String url) {
		// 添加微信js签名信息
		Map<String, String> data = WXJsapiticket.jsApiSign(url);
		logger.info("data:"+JSONObject.fromObject(data));
		return data;
	}
	/**
	 * 上传并保存到服务器
	 * 2018年8月6日上午8:42:29
	 */
	@RequestMapping("saveImageToDisk")
	@ResponseBody
	public String saveImageToDisk(String mediaIdStr,MchtImage mchtImage,String servermap) {
		Integer count=muis.saveImageToDisk(mchtImage, servermap);
		return count+"";
	}
	
	
	@RequestMapping("queryMchtInfo")
	@ResponseBody
	public String find() {
		
		return null;
	}
	 

}

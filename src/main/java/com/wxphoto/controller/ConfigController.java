package com.wxphoto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView find(MchtImage mchtImage,HttpServletRequest req) {
		
		System.out.println("openid:"+mchtImage.getOpenId());
		System.out.println("st:"+mchtImage.getStart());
//		List<MchtImage> list= muis.query(mchtImage);
		List<MchtImage> list=new  ArrayList<MchtImage>();
		list.add(mchtImage);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("mchtlist");
		modelAndView.addObject("dataList", list);
		req.setAttribute("openid", list.get(0).getOpenId());
		return modelAndView;
	}
	 

}

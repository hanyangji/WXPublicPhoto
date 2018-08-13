package com.wxphoto.serviceimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxphoto.dao.MchtImageMapper;
import com.wxphoto.data.DataSourceConst;
import com.wxphoto.data.DataSourceContextHolder;
import com.wxphoto.entity.MchtImage;
import com.wxphoto.service.MchtUploadImageService;
import com.wxphoto.util.DateFormat;
import com.wxphoto.util.PathContent;
import com.wxphoto.util.WeiXinUtil;
import com.wxphoto.util.WeixinSignUtil;

import net.sf.json.JSONObject;

@Service
public class MchtUploadImageServiceImpl implements MchtUploadImageService {

	@Autowired
	private MchtImageMapper mim;

	/**
	 * 保存到数据库,并下载到指定目录
	 */
	public Integer saveImageToDisk(MchtImage mchtImage, String mediaIdStr) {
		System.out.println(mchtImage.getOpenId());
		mchtImage.setMchtid(mchtImage.getMchtid().toUpperCase());
		Map<String,String>  mediaId = toStringList(mediaIdStr);
		String image = "";
		for(Entry<String, String> map:mediaId.entrySet()) {
			//下载到指定目录
			String filename=save(map,mchtImage.getMchtid());
			image+=filename+",";
		}
		mchtImage.setImage(image);
		mchtImage.setUpdatetime(DateFormat.getCurrentDate());
		DataSourceContextHolder.setDataSourceType(DataSourceConst.ORACLE);
		//存入数据库
		Integer count=mim.insertSelective(mchtImage);
		return count;
	}
	
	public Map<String,String> toStringList(String mediaIdStr) {
		JSONObject jsonObject = JSONObject.fromObject(mediaIdStr);
		Map<String,String> map=new HashMap<String, String>();
		map.put("wxlogo", jsonObject.getString("wxlogo"));
		map.put("activity", jsonObject.getString("activity"));
		map.put("alilogo", jsonObject.getString("alilogo"));
		map.put("door", jsonObject.getString("door"));
		map.put("platform", jsonObject.getString("platform"));
		map.put("wxbusiness", jsonObject.getString("wxbusiness"));
		map.put("wxdoor", jsonObject.getString("wxdoor"));
		map.put("wxinside", jsonObject.getString("wxinside"));
		return map;
	}
	
	public String save(Entry<String,String> map,String mchtId) {
		String filename = null;
		InputStream inputStream = WeiXinUtil.getMedia(map.getValue());
		InputStream inputstream=null;
//		byte[] data = new byte[1024];
//		int len = 0;
		Boolean flag=false;
		try {
			String path="";
			if(map.getKey().equals("wxlogo")||map.getKey().equals("door")||map.getKey().equals("activity")) {
				// 服务器存图路径
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.WX;
				flag=writeFile(path,inputStream,map.getKey());
			}else if(map.getKey().equals("alilogo")){
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.ZFB;
				flag=writeFile(path,inputStream,map.getKey());
			}else {
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.WX;
				flag=writeFile(path,inputStream,map.getKey());
				inputstream=WeiXinUtil.getMedia(map.getValue());
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.ZFB;
				flag=writeFile(path,inputstream,map.getKey());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputstream != null) {
				try {
					inputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(flag) {
			filename=map.getKey()+".jpg";
		}
		return filename;
	}
	
	public Boolean writeFile(String path,InputStream inputStream,String key) throws IOException  {
		FileOutputStream fileOutputStream = null;
		byte[] data = new byte[1024];
		int len = 0;
		String filename = "";
		Boolean flag=false;
		File sf = new File(path);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		if(key.equals("wxlogo")) {
			filename = "微信LOGO的收银台.jpg";
		}else if(key.equals("door")) {
			filename = "地推人员和门头合照.jpg";
		}else if(key.equals("activity")) {
			filename = "摇摇乐活动照.jpg";
		}else if(key.equals("alilogo")) {
			filename = "支付宝LOGO收银台.jpg";
		}else if(key.equals("platform")) {
			filename = "入驻主流餐饮平台展示照.jpg";
		}else if(key.equals("wxbusiness")) {
			filename = "营业执照.jpg";
		}else if(key.equals("wxdoor")) {
			filename = "门头照片.jpg";
		}else if(key.equals("wxinside")) {
			filename = "内部经营照片.jpg";
		}
		try {
			fileOutputStream = new FileOutputStream(sf.getPath() + "/" + filename);
			while ((len = inputStream.read(data)) != -1) {
			fileOutputStream.write(data, 0, len);
			fileOutputStream.flush();
			flag=true;
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

}

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
		mchtImage.setUpdatetime(DateFormat.getCurrentDateHours());
		DataSourceContextHolder.setDataSourceType(DataSourceConst.ORACLE);
		//存入数据库
		Integer count=mim.insertSelective(mchtImage);
		return count;
	}
	
	public List<MchtImage> query(MchtImage mchtImage){
		String st=mchtImage.getStop();
		if(st!=null) {
			String[] str=mchtImage.getStop().split("-");
			str[2]=String.valueOf(Integer.valueOf(str[2])+1);
			String stop="";
			stop+=str[0]+"-";
			stop+=str[1]+"-";
			stop+=str[2];
			mchtImage.setStop(stop);
		}
		List<MchtImage> list=mim.selectByTime(mchtImage);
		mchtImage.setStop(st);
		return list;
	}
	
	/**
	 * 实体类转map
	 * 2018年8月13日上午10:25:05
	 */
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
	
	/**
	 * 下载方法
	 * 2018年8月13日上午10:24:29
	 */
	public String save(Entry<String,String> map,String mchtId) {
		String filename = null;
		InputStream inputStream = WeiXinUtil.getMedia(map.getValue());
		InputStream inputstream=null;
//		byte[] data = new byte[1024];
//		int len = 0;
		try {
			String path="";
			if(map.getKey().equals("wxlogo")||map.getKey().equals("door")||map.getKey().equals("activity")) {
				// 服务器存图路径
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.WX;
				filename=writeFile(path,inputStream,map.getKey(),"wx");
			}else if(map.getKey().equals("alilogo")){
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.ZFB;
				filename=writeFile(path,inputStream,map.getKey(),"zfb");
			}else {
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.WX;
				filename=writeFile(path,inputStream,map.getKey(),"wx");
				inputstream=WeiXinUtil.getMedia(map.getValue());
				path = PathContent.DownLoadImg +"/"+DateFormat.getCurrentDate()+ "/" + mchtId+"/"+PathContent.ZFB;
				filename=filename+","+writeFile(path,inputstream,map.getKey(),"zfb");
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
		return filename;
	}
	
	/**
	 * 数据流写数据
	 * 2018年8月13日上午10:24:51
	 */
	public String writeFile(String path,InputStream inputStream,String key,String type) throws IOException  {
		FileOutputStream fileOutputStream = null;
		byte[] data = new byte[1024];
		int len = 0;
		String filename = "";
		File sf = new File(path);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		if(type.equals("wx")) {
			if(key.equals("wxlogo")) {
				filename = "收银台.jpg";
			}else if(key.equals("door")) {
				filename = "BD和商户门头照.jpg";
			}else if(key.equals("activity")) {
				filename = "摇摇乐照.jpg";
			}else if(key.equals("platform")) {
				filename = "入驻照.jpg";
			}else if(key.equals("wxbusiness")) {
				filename = "营业执照.jpg";
			}else if(key.equals("wxdoor")) {
				filename = "门头照.jpg";
			}else if(key.equals("wxinside")) {
				filename = "店内环境.jpg";
			}
		}else {
			if(key.equals("alilogo")) {
				filename = "2.jpg";
			}else if(key.equals("platform")) {
				filename = "5.jpg";
			}else if(key.equals("wxbusiness")) {
				filename = "3.jpg";
			}else if(key.equals("wxdoor")) {
				filename = "1.jpg";
			}else if(key.equals("wxinside")) {
				filename = "4.jpg";
			}
		}
		try {
			fileOutputStream = new FileOutputStream(sf.getPath() + "/" + filename);
			while ((len = inputStream.read(data)) != -1) {
			fileOutputStream.write(data, 0, len);
			fileOutputStream.flush();
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
		return filename;
	}

}

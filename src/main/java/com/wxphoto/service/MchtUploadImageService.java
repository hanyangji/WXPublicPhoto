package com.wxphoto.service;

import java.util.List;

import com.wxphoto.entity.MchtImage;

public interface MchtUploadImageService {
	
	public Integer saveImageToDisk(MchtImage mchtImage,String mediaIdStr);
	public List<MchtImage> query(MchtImage mchtImage);

}

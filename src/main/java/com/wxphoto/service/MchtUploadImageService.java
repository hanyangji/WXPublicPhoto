package com.wxphoto.service;

import com.wxphoto.entity.MchtImage;

public interface MchtUploadImageService {
	
	public Integer saveImageToDisk(MchtImage mchtImage,String mediaIdStr);

}

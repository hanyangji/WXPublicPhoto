package com.wxphoto.dao;

import java.util.List;

import com.wxphoto.entity.MchtImage;

public interface MchtImageMapper {
    int deleteByPrimaryKey(String mchtid);

    int insert(MchtImage record);

    int insertSelective(MchtImage record);

    List<MchtImage> selectByTime(String mchtid);

    int updateByPrimaryKeySelective(MchtImage record);

    int updateByPrimaryKey(MchtImage record);
}
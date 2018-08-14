package com.wxphoto.entity;

public class MchtImage {
    private String mchtid;

    private String mchtname;

    private String image;

    private String type;

    private String flag;

    private String isdelete;

    private String updatetime;
    
    private String openId;
    
    private String start;
    private String stop;
    
    public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	@Override
	public String toString() {
		return "MchtImage [mchtid=" + mchtid + ", mchtname=" + mchtname + ", image=" + image + ", type=" + type
				+ ", flag=" + flag + ", isdelete=" + isdelete + ", updatetime=" + updatetime + ", openId=" + openId
				+ ", wxName=" + wxName + "]";
	}

	private String wxName;

    public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	public String getMchtid() {
        return mchtid;
    }

    public void setMchtid(String mchtid) {
        this.mchtid = mchtid == null ? null : mchtid.trim();
    }

    public String getMchtname() {
        return mchtname;
    }

    public void setMchtname(String mchtname) {
        this.mchtname = mchtname == null ? null : mchtname.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }
}
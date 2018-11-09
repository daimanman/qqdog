package com.man.qqdog.client.po;

import java.util.Date;
import java.util.Map;

public class QsessionInfoPo implements java.io.Serializable {
	
	public static final long serialVersionUID = -3495034486216602323L;

	public String uid;

    public Date createDate;
    
    public String params;
    
    public String cookie;
    
    public int flag;
    
    public Date updateDate;
    
    
    public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Map<String,String> cookieMap;
    
    public Map<String,Object> paramsMap;
    
    public Map<String, String> getCookieMap() {
		return cookieMap;
	}

	public void setCookieMap(Map<String, String> cookieMap) {
		this.cookieMap = cookieMap;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
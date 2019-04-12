package com.man.qqdog.client.dto;

public class MtMeishiLogDto {

	//记录最后一次访问的url
	public String lasturl;
	
	//记录最后一次访问的时间戳  毫秒记录
	public long lasttime;
	
	public int pn;
	
	public int cityId;
	
	public int areaId;

	public String getLasturl() {
		return lasturl;
	}

	public void setLasturl(String lasturl) {
		this.lasturl = lasturl;
	}

	public long getLasttime() {
		return lasttime;
	}

	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}

	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	
	
	
}

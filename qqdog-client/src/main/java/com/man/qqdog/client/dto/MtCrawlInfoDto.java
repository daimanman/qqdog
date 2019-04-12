package com.man.qqdog.client.dto;

public class MtCrawlInfoDto {

	//code 0 ok -1 not ok
	public int code;
	
	public String msg;
	
	public String nextUrl;
	
	//0 城市
	public int type;
	
	public int ci;
	
	public int totalPn;
	
	public int curPn;
	
	public String cityName;
	
	public int areaId;
	
	public String areaName;
	
	public long poiId;
	
	
	
	

	

	public long getPoiId() {
		return poiId;
	}

	public void setPoiId(long poiId) {
		this.poiId = poiId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getTotalPn() {
		return totalPn;
	}

	public void setTotalPn(int totalPn) {
		this.totalPn = totalPn;
	}

	public int getCurPn() {
		return curPn;
	}

	public void setCurPn(int curPn) {
		this.curPn = curPn;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
}

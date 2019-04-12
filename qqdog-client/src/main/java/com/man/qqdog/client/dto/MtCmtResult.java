package com.man.qqdog.client.dto;

public class MtCmtResult {

	public long poiId;
	
	public String poiUrl;
	
	public int offset;
	
	public int cityId;
	
	public String poiname;
	
	public int cmtNum;
	

	public int getCmtNum() {
		return cmtNum;
	}

	public void setCmtNum(int cmtNum) {
		this.cmtNum = cmtNum;
	}

	public String getPoiname() {
		return poiname;
	}

	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public long getPoiId() {
		return poiId;
	}

	public void setPoiId(long poiId) {
		this.poiId = poiId;
	}

	public String getPoiUrl() {
		return poiUrl;
	}

	public void setPoiUrl(String poiUrl) {
		this.poiUrl = poiUrl;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}

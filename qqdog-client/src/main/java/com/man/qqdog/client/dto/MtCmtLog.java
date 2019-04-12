package com.man.qqdog.client.dto;

public class MtCmtLog {

	public int cmtNum;
	
	public int cmtGet;
	
	public long poiId;
	
	

	public MtCmtLog(int cmtNum, int cmtGet, long poiId) {
		super();
		this.cmtNum = cmtNum;
		this.cmtGet = cmtGet;
		this.poiId = poiId;
	}
	
	
	public MtCmtLog() {}

	public int getCmtNum() {
		return cmtNum;
	}

	public void setCmtNum(int cmtNum) {
		this.cmtNum = cmtNum;
	}

	public int getCmtGet() {
		return cmtGet;
	}

	public void setCmtGet(int cmtGet) {
		this.cmtGet = cmtGet;
	}

	public long getPoiId() {
		return poiId;
	}

	public void setPoiId(long poiId) {
		this.poiId = poiId;
	}
	
	
}

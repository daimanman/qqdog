package com.man.qqdog.client.dto;

public class MtNextDetailDto {

	public long poiId;
	
	public long start;
	
	public long end = 0L;
	
	
	
	

	public MtNextDetailDto(long poiId, long start, long end) {
		super();
		this.poiId = poiId;
		this.start = start;
		this.end = end;
	}
	public MtNextDetailDto() {}
	public long getPoiId() {
		return poiId;
	}

	public void setPoiId(long poiId) {
		this.poiId = poiId;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
	
	
	
}

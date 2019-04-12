package com.man.qqdog.client.po;


public class MtMeishiInfoPo implements java.io.Serializable {
	
    
	private static final long serialVersionUID = -7747739762438920754L;

	public Long id;

    public String address;

    public String title;

    public Integer cmtNum;

    public Double avgPrice;

    public Double avgScore;

    public String frontImg;

    public Integer areaId;

    public String areaName;

    public Integer areaPid;

    public Double lat;

    public Double lng;

    public Integer cityId;

    public String cityName;

    public Integer showStatus;

    public String phone;

    public String openTime;

    public Integer brandId;

    public String brandName;

    public Integer hasFoodSafeInfo;

    public Integer isMeiShi;

    public String extraInfo;

    public Integer dealNum;
    
    public Integer cmtGet;
    
    public Integer down;
    

    public Integer getDown() {
		return down;
	}

	public void setDown(Integer down) {
		this.down = down;
	}

	public Integer getCmtGet() {
		return cmtGet;
	}

	public void setCmtGet(Integer cmtGet) {
		this.cmtGet = cmtGet;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCmtNum() {
        return cmtNum;
    }

    public void setCmtNum(Integer cmtNum) {
        this.cmtNum = cmtNum;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg == null ? null : frontImg.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getAreaPid() {
        return areaPid;
    }

    public void setAreaPid(Integer areaPid) {
        this.areaPid = areaPid;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Integer getHasFoodSafeInfo() {
        return hasFoodSafeInfo;
    }

    public void setHasFoodSafeInfo(Integer hasFoodSafeInfo) {
        this.hasFoodSafeInfo = hasFoodSafeInfo;
    }

    public Integer getIsMeiShi() {
        return isMeiShi;
    }

    public void setIsMeiShi(Integer isMeiShi) {
        this.isMeiShi = isMeiShi;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo == null ? null : extraInfo.trim();
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }
}
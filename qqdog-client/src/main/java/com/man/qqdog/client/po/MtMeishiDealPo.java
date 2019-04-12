package com.man.qqdog.client.po;

public class MtMeishiDealPo {
    public Long id;

    public String frontImgUrl;

    public Double price;

    public Integer soldNum;

    public String title;

    public Double valuePrice;

    public Long poiId;

  

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrontImgUrl() {
        return frontImgUrl;
    }

    public void setFrontImgUrl(String frontImgUrl) {
        this.frontImgUrl = frontImgUrl == null ? null : frontImgUrl.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(Integer soldNum) {
        this.soldNum = soldNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(Double valuePrice) {
        this.valuePrice = valuePrice;
    }

    public Long getPoiId() {
        return poiId;
    }

    public void setPoiId(Long poiId) {
        this.poiId = poiId;
    }
}
package com.man.qqdog.client.po;

public class MtMeishiCmtPo {
    public Long id;

    public String userName;

    public String userUrl;

    public Double avgPrice;

    public Long commentTime;

    public Integer replyCnt;

    public Integer zanCnt;

    public Integer readCnt;

    public String hilignt;

    public Integer userLevel;

    public Long userId;

    public Integer utype;

    public Integer star;

    public Boolean quality;

    public Boolean alreadyZzz;

    public Long reviewId;

    public String menu;

    public Long did;

    public Long dealEndtime;

    public Boolean anonymous;

    public Long poiId;

    public Integer cityId;
    
    public String comment;
    
    public String merchantComment;
    
    public String picUrls;
    

    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMerchantComment() {
		return merchantComment;
	}

	public void setMerchantComment(String merchantComment) {
		this.merchantComment = merchantComment;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl == null ? null : userUrl.trim();
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getReplyCnt() {
        return replyCnt;
    }

    public void setReplyCnt(Integer replyCnt) {
        this.replyCnt = replyCnt;
    }

    public Integer getZanCnt() {
        return zanCnt;
    }

    public void setZanCnt(Integer zanCnt) {
        this.zanCnt = zanCnt;
    }

    public Integer getReadCnt() {
        return readCnt;
    }

    public void setReadCnt(Integer readCnt) {
        this.readCnt = readCnt;
    }

    public String getHilignt() {
        return hilignt;
    }

    public void setHilignt(String hilignt) {
        this.hilignt = hilignt == null ? null : hilignt.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Boolean getQuality() {
        return quality;
    }

    public void setQuality(Boolean quality) {
        this.quality = quality;
    }

    public Boolean getAlreadyZzz() {
        return alreadyZzz;
    }

    public void setAlreadyZzz(Boolean alreadyZzz) {
        this.alreadyZzz = alreadyZzz;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getDealEndtime() {
        return dealEndtime;
    }

    public void setDealEndtime(Long dealEndtime) {
        this.dealEndtime = dealEndtime;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Long getPoiId() {
        return poiId;
    }

    public void setPoiId(Long poiId) {
        this.poiId = poiId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
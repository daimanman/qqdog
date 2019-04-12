package com.man.qqdog.client.po;

public class MtCityInfoAreaPo {
	
    public Integer id;

    public Integer cityId;

    public String cityName;

    public String name;

    public String url;

    public Integer pid;

    public Integer meishiNum;

    public Integer hasChild;

    public Integer crawlFlag;

    public Integer getNum;

    public Integer totalPn;

    public Integer getPn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getMeishiNum() {
        return meishiNum;
    }

    public void setMeishiNum(Integer meishiNum) {
        this.meishiNum = meishiNum;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public Integer getCrawlFlag() {
        return crawlFlag;
    }

    public void setCrawlFlag(Integer crawlFlag) {
        this.crawlFlag = crawlFlag;
    }

    public Integer getGetNum() {
        return getNum;
    }

    public void setGetNum(Integer getNum) {
        this.getNum = getNum;
    }

    public Integer getTotalPn() {
        return totalPn;
    }

    public void setTotalPn(Integer totalPn) {
        this.totalPn = totalPn;
    }

    public Integer getGetPn() {
        return getPn;
    }

    public void setGetPn(Integer getPn) {
        this.getPn = getPn;
    }
}
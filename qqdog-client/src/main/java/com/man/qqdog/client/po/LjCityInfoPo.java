package com.man.qqdog.client.po;

public class LjCityInfoPo {
    public Long id;

    public String cityName;

    public String provinceName;

    public String url;

    public String cap;

    public Integer erNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap == null ? null : cap.trim();
    }

    public Integer getErNum() {
        return erNum;
    }

    public void setErNum(Integer erNum) {
        this.erNum = erNum;
    }
}
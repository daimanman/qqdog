package com.man.qqdog.client.po;

public class AuthorInfoPo {
    public Long id;

    public String chaoDai;

    public String name;

    public String urlMd5;

    public String url;

    public Integer topicType;

    public String remark;

    public String tag;

    public String qs;

    public String js;

    public Integer num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChaoDai() {
        return chaoDai;
    }

    public void setChaoDai(String chaoDai) {
        this.chaoDai = chaoDai == null ? null : chaoDai.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5 == null ? null : urlMd5.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs == null ? null : qs.trim();
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js == null ? null : js.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
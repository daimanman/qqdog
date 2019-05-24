package com.man.qqdog.client.po;

public class ShiInfoPo {
    public Long id;

    public String title;

    public String authorName;

    public String urlMd5;

    public String url;

    public String js;

    public String ns;

    public Long authorId;

    public String content;

    public String authorMd5;

    public Integer topicType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
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

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js == null ? null : js.trim();
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns == null ? null : ns.trim();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAuthorMd5() {
        return authorMd5;
    }

    public void setAuthorMd5(String authorMd5) {
        this.authorMd5 = authorMd5 == null ? null : authorMd5.trim();
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }
}
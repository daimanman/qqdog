package com.man.qqdog.client.po;

public class QimgVideoPo {
    public Long id;

    public Long uid;

    public String pickey;

    public Long imgId;

    public Double coverHeight;

    public Double coverWidth;

    public Integer duration;

    public String format;

    public Long size;

    public Integer status;

    public String vid;

    public String videoShareH5;

    public Integer videoType;

    public String videoUrl;
    
    public String topicid;
    
    public long photoId;
    
    
    public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPickey() {
        return pickey;
    }

    public void setPickey(String pickey) {
        this.pickey = pickey == null ? null : pickey.trim();
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Double getCoverHeight() {
        return coverHeight;
    }

    public void setCoverHeight(Double coverHeight) {
        this.coverHeight = coverHeight;
    }

    public Double getCoverWidth() {
        return coverWidth;
    }

    public void setCoverWidth(Double coverWidth) {
        this.coverWidth = coverWidth;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid == null ? null : vid.trim();
    }

    public String getVideoShareH5() {
        return videoShareH5;
    }

    public void setVideoShareH5(String videoShareH5) {
        this.videoShareH5 = videoShareH5 == null ? null : videoShareH5.trim();
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }
}
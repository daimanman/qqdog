package com.man.qqdog.client.po;

import java.util.Date;

public class QemotCommentPo implements java.io.Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1856022238749170854L;

	public Long id;

    public Long emotId;

    public String tid;

    public String uid;

    public String muid;

    public String mname;

    public String createtime2;

    public String createTime;

    public String replyNum;

    public String createTime1;

    public Date createGmt;

    public String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmotId() {
        return emotId;
    }

    public void setEmotId(Long emotId) {
        this.emotId = emotId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getMuid() {
        return muid;
    }

    public void setMuid(String muid) {
        this.muid = muid == null ? null : muid.trim();
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

   

    public String getCreatetime2() {
		return createtime2;
	}

	public void setCreatetime2(String createtime2) {
		this.createtime2 = createtime2;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum == null ? null : replyNum.trim();
    }

    public String getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1 == null ? null : createTime1.trim();
    }

    public Date getCreateGmt() {
        return createGmt;
    }

    public void setCreateGmt(Date createGmt) {
        this.createGmt = createGmt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
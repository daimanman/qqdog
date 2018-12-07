package com.man.qqdog.biz.utils;

import java.util.Map;

import com.man.qqdog.client.po.QemotCommentPo;
import com.man.qqdog.client.po.QemotCommentReplyPo;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QemotPicPo;
import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;
import com.man.qqdog.client.po.QphotoImgPo;
import com.man.qqdog.client.po.QphotoInfoPo;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.utils.ObjectUtil;


@SuppressWarnings("unchecked")
public class QqModelTransform {
	
	
	public static QuserInfoPo converQuserInfo(Map<String,Object> contentMap) {
		QuserInfoPo po = new QuserInfoPo();
		Map<String,Object> data  = ObjectUtil.castMapObj(contentMap.get("data"));
		po.age = ObjectUtil.getStr(data,"age");
		po.birthday = ObjectUtil.getStr(data, "birthday");
		po.birthyear = ObjectUtil.getStr(data, "birthyear");
		po.bloodtype = ObjectUtil.getStr(data, "bloodtype");
		po.career = ObjectUtil.getStr(data,"career");
		po.cb = ObjectUtil.getStr(data, "cb");
		po.cc = ObjectUtil.getStr(data, "cc");
		po.cco = ObjectUtil.getStr(data, "cco");
		po.city = ObjectUtil.getStr(data, "city");
		po.company = ObjectUtil.getStr(data, "compamy");
		po.country = ObjectUtil.getStr(data, "country");
		po.cp = ObjectUtil.getStr(data, "cp");
		po.describe = ObjectUtil.getStr(data, "desc");
		po.hc = ObjectUtil.getStr(data, "hc");
		po.hco = ObjectUtil.getStr(data, "hco");
		po.hp = ObjectUtil.getStr(data, "hp");
		po.marriage = ObjectUtil.getStr(data, "marriage");
		po.nickname = ObjectUtil.getStr(data, "nickname");
		po.province = ObjectUtil.getStr(data, "province");
		po.sex = ObjectUtil.getStr(data, "sex");
		po.sexType =ObjectUtil.getStr(data, "sex_type");
		po.spacename = ObjectUtil.getStr(data, "spacename");
		po.signature = ObjectUtil.getStr(data, "signature");
		return po;
	}
	public static QemotInfoPo converQemotInfo(Map qqmap){
		QemotInfoPo info = new QemotInfoPo();
		
		info.id = ObjectUtil.parseLong(qqmap.get("qemotInfoId"));
		info.cmtnum = ObjectUtil.parseInteger(qqmap.get("cmtnum"));
		info.createtime = ObjectUtil.toString(qqmap.get("createTime"));
		info.createdTime = ObjectUtil.toString(qqmap.get("created_time"));
		
		info.editmask = ObjectUtil.toString(qqmap.get("editMask"));
		
		info.fwdnum = ObjectUtil.parseInteger(qqmap.get("fwdnum"));
		info.name = ObjectUtil.toString(qqmap.get("name"));
		info.pictotal = ObjectUtil.parseInteger(qqmap.get("pictotal"));
		info.right = ObjectUtil.toString(qqmap.get("right"));
		info.rtSum = ObjectUtil.toString(qqmap.get("rt_sum"));
		
		info.secret = ObjectUtil.toString(qqmap.get("secret"));
		info.tid = ObjectUtil.toString(qqmap.get("tid"));
		info.ugcRight = ObjectUtil.toString(qqmap.get("ugc_right"));
		
		info.uid = ObjectUtil.toString(qqmap.get("uin"));
		info.wbid = ObjectUtil.toString(qqmap.get("wbid"));
		
		Map lbsMap = ObjectUtil.castMapObj(qqmap.get("lbs"));
		info.lbsId = ObjectUtil.toString(lbsMap.get("id"));
		info.lbsIdname = ObjectUtil.toString(lbsMap.get("idname"));
		info.lbsName = ObjectUtil.toString(lbsMap.get("name"));
		info.lbsPosX = ObjectUtil.toString(lbsMap.get("pos_x"));
		info.lbsPosY = ObjectUtil.toString(lbsMap.get("pos_y"));
		info.sourceName = ObjectUtil.toString(qqmap.get("source_name"));
		info.content = ObjectUtil.toString(qqmap.get("content"));
		
		return info;
	}
	
	public static QemotPicPo converQemotPic(Map picMap){
		QemotPicPo pic = new QemotPicPo();
		pic.tid = ObjectUtil.toString(picMap.get("tid"));
		pic.emotId = ObjectUtil.parseLong(picMap.get("qemotInfoId"));
		pic.uid = ObjectUtil.toString(picMap.get("uin"));
		pic.height = ObjectUtil.toString(picMap.get("height"));
		pic.width = ObjectUtil.toString(picMap.get("width"));
		pic.url1 = ObjectUtil.toString(picMap.get("url1"));
		pic.url2 = ObjectUtil.toString(picMap.get("url2"));
		pic.url3 = ObjectUtil.toString(picMap.get("url3"));
		
		return pic;
	}
	
	public static QemotCommentPo converQemotComment(Map commentMap){
		QemotCommentPo comment =  new QemotCommentPo();
		comment.id = ObjectUtil.parseLong(commentMap.get("emotCommentId"));
		comment.emotId = ObjectUtil.parseLong(commentMap.get("qemotInfoId"));
		comment.tid = ObjectUtil.toString(commentMap.get("tid"));
		comment.uid = ObjectUtil.toString(commentMap.get("qemotInfoUin"));
		comment.muid = ObjectUtil.toString(commentMap.get("uin"));
		comment.mname = ObjectUtil.toString(commentMap.get("name"));
		comment.content = ObjectUtil.toString(commentMap.get("content"));
		comment.createtime2 = ObjectUtil.toString(commentMap.get("createtime2"));
		comment.createTime = ObjectUtil.toString(commentMap.get("createTime"));
		comment.createTime1 = ObjectUtil.toString(commentMap.get("create_time"));
		comment.replyNum = ObjectUtil.toString(commentMap.get("reply_num"));
		
		return comment;
	}
	
	public static QemotCommentReplyPo converQemotCommentReply(Map replyMap){

		QemotCommentReplyPo reply = new QemotCommentReplyPo();
		reply.commentId = ObjectUtil.parseLong(replyMap.get("qemotCommentId"));
		reply.uid = ObjectUtil.toString(replyMap.get("uin"));
		reply.content = ObjectUtil.toString(replyMap.get("content"));
		reply.createTime0 = ObjectUtil.toString(replyMap.get("create_time"));
		reply.createTime2 = ObjectUtil.toString(replyMap.get("createTime2"));
		reply.createTime3 = ObjectUtil.toString(replyMap.get("createTime"));
		reply.name = ObjectUtil.toString(replyMap.get("name"));
		reply.sourceName = ObjectUtil.toString(replyMap.get("source_name"));
		reply.tid = ObjectUtil.toString(replyMap.get("tid"));
		return reply;
		
	}
	
	public static QmsgInfoPo converQmsgInfo(Map<String,Object> data) {
		QmsgInfoPo po = new QmsgInfoPo();
		po.bmp = ObjectUtil.getStr(data, "bmp");
		po.capacity = ObjectUtil.getStr(data, "capacity");
		po.effect = ObjectUtil.getStr(data, "effect");
		po.htmlContent = ObjectUtil.getStr(data, "htmlContent");
		po.modifytime = ObjectUtil.getStr(data, "modifytime");
		po.msgid = ObjectUtil.getStr(data,"id");
		po.nickname = ObjectUtil.getStr(data,"nickname");
	    po.pasterid = ObjectUtil.getStr(data,"pasterid");
	    po.pubtime = ObjectUtil.getStr(data,"pubtime");
	    po.secret = ObjectUtil.getStr(data,"secret");
	    po.type = ObjectUtil.getStr(data,"type");
	    po.uin = ObjectUtil.getStr(data,"uin");
	    return po;
	}
	
	public static QmsgInfoReplyPo converMsgReplyInfo(Map<String,Object> data) {
		QmsgInfoReplyPo po = new QmsgInfoReplyPo();
		po.content = ObjectUtil.getStr(data, "content");
		po.nick = ObjectUtil.getStr(data, "nick");
		po.time = ObjectUtil.getStr(data, "time");
		po.uin = ObjectUtil.getStr(data,"uin");
		return po;
	}
	
	public static QphotoInfoPo converPhotoInfo(Map<String,Object> data) {
		QphotoInfoPo po = new QphotoInfoPo();
		po.allowAccess = ObjectUtil.getStr(data,"allowAccess");
		po.anonymity = ObjectUtil.getStr(data,"anonymity");
		po.bitmap = ObjectUtil.getStr(data,"bitmap");
		po.classid = ObjectUtil.getStr(data,"classid");
		po.comment = ObjectUtil.getStr(data,"comment");
		po.createtime = ObjectUtil.getStr(data,"createtime");
		po.desc = ObjectUtil.getStr(data,"desc");
		po.handset = ObjectUtil.getStr(data,"handset");
		po.lastuploadtime = ObjectUtil.getStr(data,"lastuploadtime");
		po.modifytime = ObjectUtil.getStr(data,"modifytime");
		po.name = ObjectUtil.getStr(data,"name");
		po.order = ObjectUtil.getStr(data,"order");
		po.pre = ObjectUtil.getStr(data,"pre");
		po.priv = ObjectUtil.getStr(data,"priv");
		po.topicid = ObjectUtil.getStr(data,"id");
		po.viewtype = ObjectUtil.getStr(data,"viewtype");
		po.totalnum = ObjectUtil.getInt(data,"total");
		po.getnum = 0;
		return po;
	}
	
	public static QphotoImgPo converPhotoImgPo(Map<String,Object> data) {
		QphotoImgPo po = new QphotoImgPo();
		po.batchid = ObjectUtil.getStr(data,"batchId");
		po.browser = ObjectUtil.getStr(data,"browser");
		po.cameratype = ObjectUtil.getStr(data,"cameratype");
		po.cpFlag = ObjectUtil.getStr(data,"cp_flag");
		po.cpX = ObjectUtil.getStr(data,"cp_x");
		po.cpY = ObjectUtil.getStr(data,"cp_y");
		po.desc = ObjectUtil.getStr(data,"desc");
		po.forum = ObjectUtil.getStr(data,"forum");
		po.frameno = ObjectUtil.getStr(data,"frameno");
		po.height = ObjectUtil.getStr(data,"height");
		po.isVideo = ObjectUtil.getStr(data,"is_video");
		po.modifytime = ObjectUtil.getStr(data,"modifytime");
		po.name = ObjectUtil.getStr(data,"name");
		po.originUrl = ObjectUtil.getStr(data,"origin_url");
		po.owner = ObjectUtil.getStr(data,"owner");
		po.ownername = ObjectUtil.getStr(data,"ownername");
		po.photocubage = ObjectUtil.getStr(data,"photocubage");
		po.phototype = ObjectUtil.getStr(data,"phototype");
		po.picrefer = ObjectUtil.getStr(data,"picrefer");
		po.platformid = ObjectUtil.getStr(data,"platformId");
		po.platformsubid = ObjectUtil.getStr(data,"platformSubId");
		po.poiname = ObjectUtil.getStr(data,"poiName");
		po.pre = ObjectUtil.getStr(data,"pre");
		po.rawshoottime = ObjectUtil.getStr(data,"rawshoottime");
		po.uploadtime = ObjectUtil.getStr(data,"uploadtime");
		po.url = ObjectUtil.getStr(data,"url");
		po.width = ObjectUtil.getStr(data, "width");
		po.sloc = ObjectUtil.getStr(data,"sloc");
		po.lloc = ObjectUtil.getStr(data,"lloc");
		po.raw = ObjectUtil.getStr(data,"raw");
		po.rawUpload = ObjectUtil.getInt(data, "raw_upload");
		return po;
	}
}

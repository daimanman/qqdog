package com.man.qqdog.biz.utils;

import java.util.Map;

import com.man.qqdog.client.po.QemotCommentPo;
import com.man.qqdog.client.po.QemotCommentReplyPo;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QemotPicPo;
import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;
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
//		 #{qemotInfo.qemotInfoId},
//	      #{qemotInfo.cmtnum}, 
//	      #{qemotInfo.createTime}, 
//	      #{qemotInfo.created_time}, 
//	      #{qemotInfo.editMask}, 
//	      #{qemotInfo.fwdnum}, 
//	      #{qemotInfo.name},
//	      #{qemotInfo.pictotal},
//	      #{qemotInfo.right}, 
//	      #{qemotInfo.rt_sum},
//	      #{qemotInfo.secret},
//	      #{qemotInfo.tid},
//	      #{qemotInfo.ugc_right},
//	      #{qemotInfo.uin},
//	      #{qemotInfo.wbid}, 
//	      #{qemotInfo.lbs.id},
//	      #{qemotInfo.lbs.idname},
//	      #{qemotInfo.lbs.name}, 
//	      #{qemotInfo.lbs.pos_x},
//	      #{qemotInfo.lbs.pos_y},
//	      #{qemotInfo.source_name}, 
//	      #{qemotInfo.content},
		
		
//		 id,
//	      cmtnum, 
//	      createTime, 
//	      created_time, 
//	      editMask, 
//	      fwdnum, 
//	      name, 
//	      pictotal, 
//	      `right`, 
//	      rt_sum, 
//	      secret, 
//	      tid, 
//	      ugc_right, 
//	      uid, 
//	      wbid, 
//	      lbs_id, 
//	      lbs_idname,
//	      lbs_name, 
//	      lbs_pos_x, 
//	      lbs_pos_y, 
//	      source_name, 
//	      content,
	      
	      
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
//		 insert into qemot_pic (
//			    	tid, 
//			    	emot_id, 
//			      	uid, 
//			      	height, 
//			      	width, 
//			      	url1, 
//			      	url2, 
//			      	url3,
//			      	create_gmt
//			      	)
//			    values 
//			    <foreach collection="picList" item="item" index="index" separator="," >
//			    ( 
//			      #{item.tid}, 
//			      #{item.qemotInfoId}, 
//			      #{item.uin}, 
//			      #{item.height},
//			      #{item.width}, 
//			      #{item.url1}, 
//			      #{item.url2},
//			      #{item.url3},
//			      now()
//			     )
		
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
//		 insert into qemot_comment (
//			      id,
//			      emot_id, 
//			      tid, 
//			      uid,
//			      muid,
//			      mname, 
//			      content, 
//			      create_time2, 
//			      create_time,
//			      create_time1, 
//			      reply_num,
//			      create_gmt
//			      )
//			    values 
//			    <foreach collection="commentList" item="comment" separator=",">
//			    		 ( 
//			    		  #{comment.emotCommentId},
//					      #{comment.qemotInfoId}, 
//					      #{comment.tid}, 
//					      #{comment.qemotInfoUin}, 
//					      #{comment.uin}, 
//					      #{comment.name}, 
//					      #{comment.content}, 
//					      #{comment.createtime2},
//					      #{comment.createTime}, 
//					      #{comment.create_time},
//					      #{comment.reply_num},
//					      now()
//			     		 )
		
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
//		 insert into qemot_comment_reply (
//			    	comment_id,
//			    	uid, 
//			      	content, 
//			      	create_time0, 
//			      	create_time2, 
//			      	create_time3, 
//			      	name, 
//			      	source_name, 
//			      	tid,
//			      	create_gmt
//			      	)
//			    values 
//			    <foreach collection="replyList" item="reply" index="index" separator=",">
//			      (
//			      #{reply.qemotCommentId},
//			      #{reply.uin}, 
//			      #{reply.content},
//			      #{reply.create_time},
//			      #{reply.createTime2}, 
//			      #{reply.createTime},
//			      #{reply.name},
//			      #{reply.source_name}, 
//			      #{reply.tid},
//			      now()
//			      )
//			      </foreach>
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
}

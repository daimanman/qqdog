select * from qq a left join qsession_info b on a.uid = b.uid where b.uid is null


select count(1) quser_info from quser_info;
select count(1) qemot_comment from qemot_comment;
select count(1) qemot_comment_reply from qemot_comment_reply;
select count(1) qemot_info from qemot_info;
select count(1) qemot_pic from qemot_pic;
select count(1) qmsg_info from qmsg_info;
select count(1) qmsg_info_reply from qmsg_info_reply;
select count(1) qphoto_info from qphoto_info;
select count(1) qphoto_img from qphoto_img;


select max(uin) maxuid from (
		select max(uid) uin from (
		select * from quser_info order by id desc
		limit 10
		) a
		union
		select max(uid) uin from (
		select * from quser_info_n
		order by uid desc limit 10
		) b
		) c
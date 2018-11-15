import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.manager.QqManager;
import com.man.qqdog.biz.manager.StartCrawlThread;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;


public class TestMan {
	public static String QQ_EMOTINFO_URL = "https://h5.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6";
	public static String QQ_BASEINFO_URL = "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/cgi_userinfo_get_all";
	public static String QQ_MSGINFO_URL = "https://h5.qzone.qq.com/proxy/domain/m.qzone.qq.com/cgi-bin/new/get_msgb";
	public static String QQ_PHOTOINFO_URL = "https://h5.qzone.qq.com/proxy/domain/alist.photo.qq.com/fcgi-bin/fcg_list_album_v3";
	public static String QQ_IMGINFO_URL = "https://h5.qzone.qq.com/proxy/domain/plist.photo.qzone.qq.com/fcgi-bin/cgi_list_photo";
	public static String QQ_VISITINFO_URL = "https://h5.qzone.qq.com/proxy/domain/g.qzone.qq.com/cgi-bin/friendshow/cgi_get_visitor_simple";
	public static String QQ_SENT_EMOT = "https://user.qzone.qq.com/proxy/domain/taotao.qzone.qq.com/cgi-bin/emotion_cgi_publish_v6";
	public Map<String,String> getHeader()  {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("q.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Object> sets = prop.keySet();
		Map<String,String> map = new HashMap<>();
		for(Object k:sets) {
			map.put(k.toString(),prop.getProperty(k.toString()));
		}
		return map;
	}
	
	@Test
	public void testUserInfo() {
//		uin: 1843594995
//		vuin: 1843594995
//		fupdate: 1
//		rd: 0.30054798221476764
//		g_tk: 2014866823
//		qzonetoken: 913039de8625eda75b36646073d2588502d521fd289a4718df8fa3b1c99664c81722e33245c1e0edd5a8
		Map<String,Object> params = new HashMap<>();
		params.put("uin","1843594995");
		params.put("vuin","1843594995");
		params.put("fupdate","1");
		params.put("rd","0.30054798221476764");
		params.put("g_tk", "2014866823");
		params.put("qzonetoken","913039de8625eda75b36646073d2588502d521fd289a4718df8fa3b1c99664c81722e33245c1e0edd5a8");
		params.put("format","json");
		String content = YhHttpUtil.sendHttpGetWithRetry(QQ_BASEINFO_URL,params,getHeader());
		
		System.out.println(content);
	}
	
	@Test
	public void testSentEmot01() {
		Map<String, Object> newParamMap = new HashMap<String, Object>();
	newParamMap.put("g_tk","1225134021");
	newParamMap.put("qzonetoken", "550221b9c7a2b78fa8ad40861bf00f36074878d701f45778a2017c2c8f41ce64aac63f18eb67607c6cd4");
		
		
		newParamMap.put("syn_tweet_verson",1);
		newParamMap.put("paramstr", 1);
		newParamMap.put("pic_template", "");
		newParamMap.put("richval", "");
		newParamMap.put("special_url","");
		
		newParamMap.put("subrichtype","");
		newParamMap.put("con","qm9087");
		newParamMap.put("feedversion", "1");
		newParamMap.put("ver", "1");
		newParamMap.put("ugc_right","1");
		
		newParamMap.put("to_sign","1");
		newParamMap.put("hostuin","1843594995");
		newParamMap.put("feedversion", "1");
		newParamMap.put("code_version", "1");
		newParamMap.put("format","fs");
		newParamMap.put("qzreferrer","https://user.qzone.qq.com/1843594995?ADUIN=1843594995&ADSESSION=1541985158&ADTAG=CLIENT.QQ.5575_MyInfo_PersonalInfo.0&ADPUBNO=26809&source=namecardstar");
		String resp = YhHttpUtil.sendHttpGetWithRetry(QQ_SENT_EMOT, newParamMap, getHeader());
		System.out.println(resp);
	}
	
	@Test
	public void testLink() {
		LinkedList<String> datas = new LinkedList<>(Arrays.asList("12","3","9","90"));
		for(int i = 0;i<6;i++) {
		System.out.println(datas);
		String data = datas.pop();
		System.out.println(data);
		//datas.add(data);
		System.out.println(datas);
		System.out.println("-------------");
		}
	}
	
	@Test
	public  void testSentEmot() {
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	@Test
	public void testThread10() {
		QqManager qq = new QqManager();
		qq.initStartUid(0);
		for(int i = 0;i<30;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"----"+qq.getNextUid());
				}
			}).start();
		}
		
	}
	
	@Test
	public void testEmotJson() throws FileNotFoundException, IOException {
		String json = IOUtils.toString(new FileInputStream(new File("D:\\1.json")),"utf-8");
		Map<String,Object> m = JSON.parseObject(json,Map.class);
		System.out.println(ObjectUtil.getSize(m.get("msglist")));
	}
	
	@Test
	public void testNextUidThread() {
		QqManager qqManager = new QqManager();
		qqManager.startUid = 10187965;
		for(int i = 0;i<10;i++) {
			Thread t = new Thread(new StartCrawlThread(qqManager));
			t.start();
		}
		
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

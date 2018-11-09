import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import com.man.utils.YhHttpUtil;


public class TestMan {
	public static String QQ_EMOTINFO_URL = "https://h5.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6";
	public static String QQ_BASEINFO_URL = "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/cgi_userinfo_get_all";
	public static String QQ_MSGINFO_URL = "https://h5.qzone.qq.com/proxy/domain/m.qzone.qq.com/cgi-bin/new/get_msgb";
	public static String QQ_PHOTOINFO_URL = "https://h5.qzone.qq.com/proxy/domain/alist.photo.qq.com/fcgi-bin/fcg_list_album_v3";
	public static String QQ_IMGINFO_URL = "https://h5.qzone.qq.com/proxy/domain/plist.photo.qzone.qq.com/fcgi-bin/cgi_list_photo";
	public static String QQ_VISITINFO_URL = "https://h5.qzone.qq.com/proxy/domain/g.qzone.qq.com/cgi-bin/friendshow/cgi_get_visitor_simple";
	
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
}

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.qq.QqConfig;
import com.man.qqdog.biz.manager.QqManager;
import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.utils.YhHttpUtil;

public class TestQ {

	@Test
	public void testTestBaseInfo() {
		System.out.println(System.getenv("dbname"));
	}
	
	
	public List<QsessionInfoPo> testQzon() throws Exception {
		Map<String,Object> params = new HashMap<>();
		params.put("sql","select * from qsession_info where flag = 0  ");
		String content = YhHttpUtil.sendHttpPost("http://192.168.1.193:54321/getDataBySql", params, null);
		return JSON.parseArray(content, QsessionInfoPo.class);
	}
	
	@Test
	public void testQqMan() throws Exception{
		QqManager qqManager = new QqManager();
		List<QsessionInfoPo> datas = testQzon();
		
		qqManager.initSessionByDatas(datas);
		String uid = "1003769227";
		String topicId = "V13nQSkZ0GqAiT";
		String data = qqManager.crawlQzoneImgInfo_test(uid,0, topicId);
		System.out.println(data);
	}
	
	
	@Test
	public void testQqImg() throws Exception {
		String url1 = "https://h5.qzone.qq.com/proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_list_photo?g_tk=1054386212&callback=shine0_Callback&t=637219642&mode=0&idcNum=4&hostUin=1003769227&topicId=V13nQSkZ0GqAiT&noTopic=0&uin=3246615477&pageStart=0&pageNum=30&skipCmtCount=0&singleurl=1&batchId=&notice=0&appid=4&inCharset=utf-8&outCharset=utf-8&source=qzone&plat=qzone&outstyle=json&format=jsonp&json_esc=1&question=&answer=&callbackFun=shine0&_=1547433170944";
		String url2 = "https://h5.qzone.qq.com/proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_list_photo?g_tk=1054386212&callback=shine0_Callback&t=637219642&mode=0&idcNum=4&hostUin=1003769227&topicId=V13nQSkZ0GqAiT&noTopic=0&uin=3246615477&pageStart=0&pageNum=30&skipCmtCount=0&singleurl=1&batchId=&notice=0&appid=4&inCharset=utf-8&outCharset=utf-8&source=qzone&plat=qzone&outstyle=json&format=jsonp&json_esc=1&question=&answer=&callbackFun=shine0&_=1547433170944";
		String url = "https://h5.qzone.qq.com/proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_list_photo?g_tk=1054386212&callback=shine0_Callback&t=637219642&mode=0&idcNum=4&hostUin=1003769227&topicId=V13nQSkZ0GqAiT&noTopic=0&uin=3246615477&pageStart=0&pageNum=30&skipCmtCount=0&singleurl=1&batchId=&notice=0&appid=4&inCharset=utf-8&outCharset=utf-8&source=qzone&plat=qzone&outstyle=json&format=json&json_esc=1&question=&answer=";
		Map<String,String> header = new HashMap<>();
		header.put("Cookie","pgv_pvid=4558450288; RK=WbiFaU9QQw; ptcz=d423ebe94d6a17de39bb5f22c4cb78d3b41959d3b007ee44f794d6baae3459cf; pgv_pvi=2434416640; QZ_FE_WEBP_SUPPORT=1; tvfe_boss_uuid=35ad5a42718df061; AMCV_248F210755B762187F000101%40AdobeOrg=-1891778711%7CMCIDTS%7C17717%7CMCMID%7C63191310701835526532311349328044808507%7CMCAAMLH-1531281679%7C11%7CMCAAMB-1531281679%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1530684079s%7CNONE%7CMCAID%7CNONE%7CMCSYNCSOP%7C411-17724%7CvVersion%7C2.4.0; __Q_w_s_hat_seed=1; _ga=amp-JC1Sf57ydpFesQlxbjbt9Q; 3g_guest_id=-8690779826006523904; __Q_w_s__QZN_TodoMsgCnt=1; randomSeed=885641; o_cookie=193271517; pac_uid=1_193271517; cpu_performance_v8=2; uin=o3246615477; skey=@IWfWb5qF9; p_uin=o3246615477; pt4_token=iiqurL*B4*02nUZuiAHZ3JS5ZO439LXBt96Mce8gszA_; p_skey=Rg6xBQKyzsJ10b4BenyqPhdjw8ZS2h8-L8wsyhSkrRc_; rv2=8036A3A7369CB8EA1D9B4A6DD671472D0E7348D70346CF7A37; property20=9DCC34C1D7B9AD0E22D8EEF763F6A349DE134A4E8E041A47B5E0D4A25E5E4B94CE6F6B6EC38D1157; pgv_si=s934201344; pgv_info=ssid=s4242932282; qq_photo_key=69ee9800bb1ebcf8005648eb7e20ed33");
		header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		String content = YhHttpUtil.sendHttpGet(url, null, header);
		System.out.println(content);
	}
	
	@Test
	public void testQqImg1() throws Exception {
		String url = "https://h5.qzone.qq.com/proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_list_photo?g_tk=1881486131&callback=shine0_Callback&t=637219642&mode=0&idcNum=4&hostUin=1003769227&topicId=V13nQSkZ0GqAiT&noTopic=0&uin=1018202804&pageStart=0&pageNum=30&skipCmtCount=0&singleurl=1&batchId=&notice=0&appid=4&inCharset=utf-8&outCharset=utf-8&source=qzone&plat=qzone&outstyle=json&format=json&json_esc=1&question=&answer=";
		Map<String,String> header = new HashMap<>();
		header.put("Cookie","pgv_pvi=4604101632; pgv_si=s8112870400; _qpsvr_localtk=0.6797812430239143; pgv_pvid=5870996684; pgv_info=ssid=s7388903132; ptisp=ctc; RK=wxY4qxSdfY; ptcz=1425393773c79d652a07b2257a792cbdc057d6862263adc1749e64eb54a647f9; Loading=Yes; qz_screen=1536x864; osstat=14; 848443394_todaycount=0; 848443394_totalcount=0; 2181296065_todaycount=0; 2181296065_totalcount=0; 938545531_todaycount=0; 938545531_totalcount=0; 3246615477_todaycount=0; 3246615477_totalcount=0; ptui_loginuin=1018202804; uin=o1018202804; skey=@8cLgzkBte; p_uin=o1018202804; pt4_token=SZUMvTyqMXVqTJXk0xgr-wZ0Wjtfi3fZ-VvyMYPfexE_; p_skey=hLMYQbOQ8hWWjEyIVCcCvoCb1iuOo0UVJB7kbATdWyY_; x-stgw-ssl-info=2f073707a22bdf72c99cd5178a739214|0.099|1547427150.895|36|r|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|43500|h2|0");
		header.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		String content = YhHttpUtil.sendHttpGet(url, null, header);
		System.out.println(content);
	}
	
	
	@Test
	public void testEm() {
		String cm = "[em]e327775[/em][em]e327775[/em]收点废铁造航母[em]e327881[/em]".replaceAll("\\[/em\\]","[em]");
		String[] names = cm.split("\\[em\\]");
		System.out.println(JSON.toJSONString(names));
	}
	
	
}

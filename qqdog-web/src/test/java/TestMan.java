import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.basequery.QueryBuilderParser;
import com.man.es.manager.ElasticSearchManager;
import com.man.pageinfo.PageResult;
import com.man.pageinfo.QueryParams;
import com.man.qqdog.biz.es.MsgInfoQueryDsl;
import com.man.qqdog.biz.manager.QqManager;
import com.man.qqdog.biz.manager.StartCrawlThread;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;
import com.man.utils.YhHttpUtil;


public class TestMan {
	public static String QQ_EMOTINFO_URL = "https://h5.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6";
	public static String QQ_BASEINFO_URL = "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/cgi_userinfo_get_all";
	public static String QQ_MSGINFO_URL = "https://h5.qzone.qq.com/proxy/domain/m.qzone.qq.com/cgi-bin/new/get_msgb";
	public static String QQ_PHOTOINFO_URL = "https://h5.qzone.qq.com/proxy/domain/alist.photo.qq.com/fcgi-bin/fcg_list_album_v3";
	public static String QQ_IMGINFO_URL = "https://h5.qzone.qq.com/proxy/domain/plist.photo.qzone.qq.com/fcgi-bin/cgi_list_photo";
	public static String QQ_VISITINFO_URL = "https://h5.qzone.qq.com/proxy/domain/g.qzone.qq.com/cgi-bin/friendshow/cgi_get_visitor_simple";
	public static String QQ_SENT_EMOT = "https://user.qzone.qq.com/proxy/domain/taotao.qzone.qq.com/cgi-bin/emotion_cgi_publish_v6";
	public static String QQ_IMG_VEDIO_URL = "https://h5.qzone.qq.com/proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_floatview_photo_list_v2";
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
	@Test
	public void testGroups() throws IOException {
		String url = "https://qun.qq.com/cgi-bin/qun_mgr/search_group_members";
		Map<String,String> headers = getHeader();
		Map<String,Object> map = new HashMap<>();
		//map.put("gc","648995561");
		map.put("gc","2635913");
		map.put("bkn", "1785406182");
		map.put("st","21");
		map.put("end","41");
		map.put("sort","0");
		String content = YhHttpUtil.sendHttpPost(url, map, headers);
		System.out.println(content);
	}
	
	@Test
	public void testJoin() {
		String k = String.join("&",Arrays.asList("name=dxm","age=13"));
		System.out.println(k);
	}
	
	@Test
	public void testGetMappping() {
		ElasticSearchManager es = new ElasticSearchManager();
		es.setClusterName("elasticsearch");
		es.setHosts("127.0.0.1:9300");
		TransportClient client = es.initClient();
		ImmutableOpenMap<String, MappingMetaData> mappings = client.admin().cluster().prepareState().execute()
                .actionGet().getState().getMetaData().getIndices().get("qemot_info_idx").getMappings();
        String maps  = mappings.get("qemot_info").source().toString();
        System.out.println(maps);
	}
	
	@Test
	public void testsignature() throws FileNotFoundException, IOException {
		String url = "https://www.cwbaoguowang.com/PACKAGEK/api_user_store/SEARCHGETCOMMODITY?shopId=066a07f4-ee97-4def-87c6-98d8de1548ea&keyword=%E5%8F%AF%E4%B9%90";
		Map<String,String> headers = new HashMap<>();
		headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		Map<String,Object> params = new HashMap<>();
		String ss = IOUtils.toString(new FileInputStream(new File("D:\\4.txt")),"utf-8");
		params.put("signature", ss);
		try {
			String resp = YhHttpUtil.sendHttpPost(url, params,headers);
			System.out.println(resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAggs() {
		ElasticSearchManager es = new ElasticSearchManager();
		es.setClusterName("elasticsearch");
		es.setHosts("192.168.1.53:9300");
		TransportClient client = es.initClient();
		MaxAggregationBuilder  aggsBuilder = AggregationBuilders.max("maxid").field("id");
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch("quser_info_idx").setTypes("quser_info")   
                .addAggregation(aggsBuilder)    
                .setSize(0); 
		 SearchResponse sr = searchRequestBuilder.execute().actionGet();
		 
		 Max max = sr.getAggregations().get("maxid");
		 
		 double  maxVal = max.getValue();
		 System.out.println((long)maxVal);
	}
	
	@Test
	public void testEmotMax() {
		ElasticSearchManager es = new ElasticSearchManager();
		es.setClusterName("elasticsearch");
		es.setHosts("192.168.1.53:9300");
		TransportClient client = es.initClient();
		TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_uid").field("uid");
		termsAggregationBuilder.size(15);
		
		TermsAggregationBuilder muidAggsBuilder = AggregationBuilders.terms("count_muid").field("muid");
		termsAggregationBuilder.subAggregation(muidAggsBuilder);
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch("qemot_comment_idx").setTypes("qemot_comment")   
                .addAggregation(termsAggregationBuilder)
                .setSize(10)
                .setFrom(0);
		 SearchResponse sr = searchRequestBuilder.execute().actionGet();
		 
		 Terms aggregation = sr.getAggregations().get("group_uid");
		 System.out.println("group_uid aggs size "+aggregation.getBuckets().size());
		 for (Terms.Bucket bucket : aggregation.getBuckets()) {
	            System.out.println("uid="+bucket.getKey()+"--count="+bucket.getDocCount());
//	            Terms muidAggs = bucket.getAggregations().get("count_muid");
//	            for(Terms.Bucket muidBucket:muidAggs.getBuckets()) {
//	            	System.out.println("\t muid="+muidBucket.getKey()+"----count="+muidBucket.getDocCount());
//	            }
	     }
	}
	
	@Test
	public void testDsl() {
		ReqParam reqParams = new ReqParam();
		reqParams.put("uids",Arrays.asList("1003772882","1003672309"));
		QueryParams queryParams = MsgInfoQueryDsl.parseListDsl(reqParams);
		System.out.println(queryParams.getQueryItems().size());
	}
	
	@Test
	public void testPage() {
		ElasticSearchManager es = new ElasticSearchManager();
		es.setClusterName("elasticsearch");
		es.setHosts("192.168.1.53:9300");
		TransportClient client = es.initClient();
		String index = "qmsg_info_idx";
		String type= "qmsg_info";
		ReqParam reqParams = new ReqParam();
		reqParams.put("uids",Arrays.asList("1003772882","1003672309"));
		QueryParams queryParams = MsgInfoQueryDsl.parseListDsl(reqParams);
		queryParams.setPage(1);
		queryParams.setPageSize(10);
		PageResult<Map<String,Object>> pageInfo = es.filterPage(index, type, queryParams);
		System.out.println(pageInfo.getDatas().size());
	}
	@Test
	public void testMsgMax() {
		ElasticSearchManager es = new ElasticSearchManager();
		es.setClusterName("elasticsearch");
		es.setHosts("192.168.1.53:9300");
		TransportClient client = es.initClient();
		String index = "qmsg_info_idx";
		String type= "qmsg_info";
		
		ReqParam reqParams = new ReqParam();
		reqParams.put("uids",Arrays.asList("1003772882","1003672309","1003674614"));
		QueryParams queryParams = MsgInfoQueryDsl.parseListDsl(reqParams);
		
		SearchRequestBuilder searchRequest = client.prepareSearch(index).setTypes(type)
				.setQuery(new QueryBuilderParser().parseQueryItems(queryParams.getQueryItems()));
				//.setPostFilter(new QueryBuilderParser().parseQueryItems(queryParams.getQueryItems()));
		//searchRequest.setFrom(0).setSize(10);
		
		TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_uid").field("uid");
		termsAggregationBuilder.size(15);
		
		TermsAggregationBuilder muidAggsBuilder = AggregationBuilders.terms("count_muid").field("uin").size(100);
		termsAggregationBuilder.subAggregation(muidAggsBuilder);
		
		searchRequest.addAggregation(termsAggregationBuilder);
		
		
		SearchResponse sr = searchRequest.execute().actionGet();
		 
		 Terms aggregation = sr.getAggregations().get("group_uid");
		 System.out.println("group_uid aggs size "+aggregation.getBuckets().size());
		 for (Terms.Bucket bucket : aggregation.getBuckets()) {
	            System.out.println("uid="+bucket.getKey()+"--count="+bucket.getDocCount());
	            Terms muidAggs = bucket.getAggregations().get("count_muid");
	            for(Terms.Bucket muidBucket:muidAggs.getBuckets()) {
	            	System.out.println("\t muid="+muidBucket.getKey()+"----count="+muidBucket.getDocCount());
	            }
	     }
		
	}
	
	private Map<String,String> getH(String filePath){
		String json="{}";
		try {
			json = IOUtils.toString(new FileInputStream(new File(filePath)),"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.parseObject(json,Map.class);
	}
	private Map<String,Object> getP(String filePath){
		String json="{}";
		try {
			json = IOUtils.toString(new FileInputStream(new File(filePath)),"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.parseObject(json,Map.class);
	}
	@Test
	public void testImgVedio() throws IOException {
		Map<String,String> header = getH("D:\\1.json");
		Map<String,Object> ps = getP("D:\\2.json");
		Map<String,Object> params = new HashMap<>();
		params.put("g_tk",ps.get("g_tk"));
		params.put("callback","viewer_Callback");
		//params.put("t","972306368");
		params.put("topicId","V13YkSk201Wgg1");
		//params.put("picKey","NDR0fhXYO81Xi1vp*JYQIQEAAAAAAAA!");
		params.put("shootTime","");
		params.put("cmtOrder","1");
		params.put("fupdate","1");
		params.put("plat","qzone");
		params.put("source","qzone");
		params.put("cmtNum","10");
		params.put("likeNum","5");
		params.put("inCharset","utf-8");
		params.put("outCharset","utf-8");
		params.put("callbackFun","viewer");
		params.put("offset","0");
		params.put("number","100");
		params.put("uin","1143886181");
		params.put("appid","4");
		params.put("isFirst","1");
		params.put("hostUin","1004017022");
		params.put("sortOrder","1");
		params.put("showMode","1");
		params.put("need_private_comment","1");
		params.put("prevNum","0");
		params.put("postNum","0");
		params.put("format","json");
		
		String res = YhHttpUtil.sendHttpGet(QQ_IMG_VEDIO_URL, params, header);
		IOUtils.write(res,new FileOutputStream(new File("D:\\q.json")));

		
	}
	
}

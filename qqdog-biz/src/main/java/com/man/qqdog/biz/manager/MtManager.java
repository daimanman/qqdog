package com.man.qqdog.biz.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.man.constants.MtConstant;
import com.man.qqdog.biz.mapper.MtCityCookiePoMapper;
import com.man.qqdog.biz.mapper.MtMeishiInfoPoMapper;
import com.man.qqdog.biz.utils.FileCharsetDetector;
import com.man.qqdog.client.dto.MtCmtLog;
import com.man.qqdog.client.dto.MtMeishiLogDto;
import com.man.qqdog.client.dto.MtNextDetailDto;
import com.man.qqdog.client.po.MtCityCookiePo;
import com.man.qqdog.client.po.MtImgPo;
import com.man.qqdog.client.po.MtMeishiDealPo;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.qqdog.client.po.MtMeishiTagPo;
import com.man.qqdog.client.service.MtMeishiInfoService;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;

@Service
public class MtManager {

	@Autowired
	private MtMeishiInfoPoMapper  mtMeishiInfoPoMapper;
	
	
	public LinkedList<MtMeishiInfoPo> nextMeshiInfos = new LinkedList<>();
	
	public LinkedList<MtMeishiInfoPo> detailMeshiInfos = new LinkedList<>();
	
	public MtMeishiInfoPo detailUrlLock = new MtMeishiInfoPo();
	
	public Set<Long> hasGetIds = new HashSet<>();
	
	Logger logger = LoggerFactory.getLogger(MtManager.class);
	
	public MtMeishiLogDto urlLog = new MtMeishiLogDto();
	
	public MtNextDetailDto nextListLock = new MtNextDetailDto();
	
	public List<MtNextDetailDto> nextList = new ArrayList<>(1000);
	static Map<String,String> h = new HashMap<>();
	static {
		h.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
	}
	
	public Map<Long,MtCityCookiePo> cookieMap = new HashMap<>();
	
	//存储缓存id集合
	public LinkedList<Long> cookieIds = new LinkedList<>();
	
	@Autowired
	private MtCityCookiePoMapper cookieMapper;
	
	public void initCookie() {
		List<MtCityCookiePo> cookies = cookieMapper.getAllCookie();
		if(null != cookies && cookies.size() > 0) {
			cookieIds = new LinkedList<>();
			for(MtCityCookiePo cookie:cookies) {
				cookieIds.add(cookie.id);
				cookieMap.put(cookie.id,cookie);
			}
		}
	}
	
	public MtCityCookiePo getNextCookie() {
		if(cookieMap.keySet().size() == 0) {
			initCookie();
		}
		if(cookieIds.size() == 0 ) {
			return null;
		}
		synchronized (cookieMap) {
			long id = cookieIds.removeFirst();
			cookieIds.addLast(id);
			return cookieMap.get(id);
		}
	}
	
	public void removeNextList(long poiId) {
		synchronized (nextListLock) {
			for(MtNextDetailDto data : nextList) {
				if(data.poiId == poiId) {
					nextList.remove(data);
					break;
				}
			}
		}
		
	}

	
	
	public ConcurrentHashMap<Long,MtCmtLog> cmtLogMap = new ConcurrentHashMap<Long, MtCmtLog>();
	
	
	public void updateCmtLog(long poiId,int cmtNum,int cmtGet) {
	     MtCmtLog log = cmtLogMap.get(poiId);
	     if(null == log) {
	    	 log = new MtCmtLog(cmtNum,cmtGet,poiId);
	    	 cmtLogMap.put(poiId,log);
	     }else {
	    	 log.cmtGet += cmtGet;
	     }
	}
	
	public void initCmtLog(List<MtMeishiInfoPo> datas) {
		if(null == datas || datas.size() == 0) {
			return;
		}
		for(MtMeishiInfoPo data : datas) 
		{
			cmtLogMap.put(data.id,new MtCmtLog(data.cmtNum,0,data.id));
		}
	}
	
	public MtCmtLog checkGetCmtOver(List<Long> ids) {
		MtCmtLog result = new MtCmtLog();
		result.poiId = -1;
		if(null != ids && ids.size() > 0) {
		  int totalCmtNum = 0;
		  int totalCmtGet = 0;
		  for(long id : ids) {
			  MtCmtLog log = cmtLogMap.get(id);
			  if(null != log) {
				  result.poiId = ids.size();
				  totalCmtNum += log.cmtNum;
				  totalCmtGet += log.cmtGet;
			  }
		  }
		  result.cmtGet = totalCmtGet;
		  result.cmtNum = totalCmtNum;
		}
		return result;
	}
	
	private void initNext() {
		if(hasGetIds.size() == 0) {
			hasGetIds.add(-1L);
		}
		logger.info("get next top 100 meishiinfo hasGetIds.size={} ",hasGetIds.size()-1);
		List<MtMeishiInfoPo> datas = mtMeishiInfoPoMapper.getNextCmtMeishiTop100(hasGetIds);
		logger.info("datas size is {}",ObjectUtil.getSize(datas));
		if(datas != null && datas.size() > 0) {
			for(MtMeishiInfoPo data : datas) {
				if(!hasGetIds.contains(data.id)) {
					nextMeshiInfos.add(data);				
				}
		        hasGetIds.add(data.id);
			}
		}
	}
	public synchronized MtMeishiInfoPo getNextMeishiCmt() {
		if(nextMeshiInfos.size() == 0) {
			initNext();
		}
		if(nextMeshiInfos.size() > 0) {
			return nextMeshiInfos.removeFirst();
		}
		return null;
	}
	
	public Set<Long> cmtGetIds = new HashSet<>();
	public  synchronized MtMeishiInfoPo  getNextCmt(int cityId) {
		if(cmtGetIds.size() == 0) {
			cmtGetIds.add(-1L);
		}
		MtMeishiInfoPo po = mtMeishiInfoPoMapper.getNextCmt(cmtGetIds,cityId);
		cmtGetIds.add(po.id);
		return po;
	}
	
	public void reopenUrlInChrome(String url) throws IOException {
		String chrome = "chrome "+url;
		Process process = Runtime.getRuntime().exec("cmd /k " + chrome);
	}
	
	public void reopenMeishiDetail(long poiId) throws IOException {
		String url = "http://www.meituan.com/meishi/"+poiId+"/";
		reopenUrlInChrome(url);
	}
	public MtMeishiInfoPo updatePo = new MtMeishiInfoPo();
	public  boolean downFlag = true;
	Pattern pattern = Pattern.compile("<script>window._appState =(.*?);</script>");
	
	private MtMeishiInfoPo parseDetail(Map<String,Object> data,int dealNum) {
		MtMeishiInfoPo info = new MtMeishiInfoPo();
		info.id = ObjectUtil.getLong(data,"poiId");
		info.avgPrice = ObjectUtil.getDouble(data,"avgPrice");
		info.avgScore = ObjectUtil.getDouble(data,"avgScore");
		info.brandId = ObjectUtil.getInt(data,"brandId");
		info.brandName = ObjectUtil.getStr(data,"brandName");
		info.dealNum = dealNum;
		info.title = ObjectUtil.getStr(data,"name");
		info.address = ObjectUtil.getStr(data,"address");
		List<Map<String,Object>> extraInfos = ObjectUtil.castListObj(data.get("extraInfos"));
		if(ObjectUtil.getSize(extraInfos) > 0) {
			List<String> extras = new ArrayList<>();
			for(Map<String,Object> extraInfo:extraInfos) {
				extras.add(ObjectUtil.getStr(extraInfo,"text"));
			}
			info.extraInfo = JSON.toJSONString(extras);
		}
		info.hasFoodSafeInfo = ObjectUtil.getBool(data,"hasFoodSafeInfo") ? 1 : 0;
		info.isMeiShi = ObjectUtil.getBool(data,"isMeiShi") ? 1 : 0;
		info.lat = ObjectUtil.getDouble(data,"latitude");
		info.lng = ObjectUtil.getDouble(data,"longitude");
		info.openTime = ObjectUtil.getStr(data,"openTime",150);
		info.phone = ObjectUtil.getStr(data,"phone");
		info.showStatus = ObjectUtil.getInt(data,"showStatus");
		return info;
	}
	public List<MtMeishiDealPo> parseDeals(List<Map<String,Object>> datas,long poiId){
		List<MtMeishiDealPo> dealList = new ArrayList<>();
		for(Map<String,Object> data : datas) {
			MtMeishiDealPo po = new MtMeishiDealPo();
			po.frontImgUrl = ObjectUtil.getStr(data,"frontImgUrl");
			po.id = ObjectUtil.getLong(data,"id");
			po.poiId = poiId;
			po.price = ObjectUtil.getDouble(data,"price");
			po.soldNum = ObjectUtil.getInt(data,"soldNum");
			po.title = ObjectUtil.getStr(data,"title");
			po.valuePrice = ObjectUtil.getDouble(data,"value");
			dealList.add(po);
		}
		return dealList;
	}
	
	private List<MtImgPo> parseMtImg(List imgs,int cityId,int type,long itemId){
		List<MtImgPo> imgPos = new ArrayList<>();
		if(ObjectUtil.getSize(imgs) > 0) {
			for(Object img : imgs) {
				MtImgPo imgPo = new MtImgPo();
				imgPo.cityId = cityId;
				imgPo.itemId = itemId;
				imgPo.type = type;
				imgPo.url = ObjectUtil.toString(img);
				imgPos.add(imgPo);
			}
		}
		return imgPos;
	}
	
	
	public void updateDetail(Map<String,Object> param) {
		Map<String,Object> detailInfo = ObjectUtil.castMapObj(param.get("detailInfo"));
		
		Map<String,Object> dealListMap =  ObjectUtil.castMapObj(param.get("dealList"));
		
		List<Map<String,Object>> deals = ObjectUtil.castListObj(dealListMap.get("deals"));
		
		MtMeishiInfoPo detail = parseDetail(detailInfo,ObjectUtil.getSize(deals));
		long poiId = detail.id;
		logger.info("update-detail poiid={} ",poiId);
		MtMeishiInfoPo po = mtMeishiInfoPoMapper.getMeishiInfoById(poiId);
		if(null != po && po.showStatus == null ) {
			mtMeishiInfoPoMapper.updateMeishiInfoSelective(detail);
			
			List<MtMeishiDealPo> dealPos = parseDeals(deals, detail.id);
			if(ObjectUtil.getSize(dealPos) > 0) {
				try {
					mtMeishiInfoPoMapper.insertMtMeishiDealBatch(dealPos);
				}catch(Exception e) {
					logger.error("insertMtMeishiDealBatch error {} ",e);
				}
			}
			Map<String,Object> photos = ObjectUtil.castMapObj(param.get("photos"));
			List albumImgUrls = ObjectUtil.castListObj(photos.get("albumImgUrls"));
			List<MtImgPo> mtImgs = parseMtImg(albumImgUrls,po.cityId,MtConstant.MT_IMG_TYPE_MEISHI,poiId);
			if(ObjectUtil.getSize(mtImgs) > 0) {
				try {
					mtMeishiInfoPoMapper.insertMtImgBatch(mtImgs);
				}catch(Exception e) {
					logger.error("insertMtImgBatch {} ",e);
				}
			}
		}
	}
	
	public MtMeishiInfoPo detailLock = new MtMeishiInfoPo();
	public Set<Long> detailIds = new HashSet<>();
	public MtMeishiInfoPo getNextDetail(int cityId) {
		synchronized (detailLock) {
			if(detailIds.size() == 0) {
				detailIds.add(-1L);
			}
			MtMeishiInfoPo po = mtMeishiInfoPoMapper.getNextDetail(detailIds,cityId);
			if(null != po) {
				detailIds.add(po.id);
			}
			return po;
		}
	}
	public void multiDownMeiDetail(MtMeishiInfoPo po) {
		int num = 0;
		if(!downFlag) {
			logger.info("downFlag is stop ");
			return;
		}
			if(null != po) {
				MtCityCookiePo cookiePo = getNextCookie();
				if(null == cookiePo ) {
					logger.error("has no cookie to be use ");
					return;
				}
				String cookieStr = cookiePo.cookie;
				logger.info(" down poiId={} ",po.id);
				String url = "http://www.meituan.com/meishi/"+po.id+"/";
				try {
					h.put("Cookie",cookieStr);
					String content = YhHttpUtil.sendHttpGet(url, null, h);
					int length = content.length();
					
					
					if((length >= 27372-1000 && length <= 27372+1000) || content.indexOf("验证中心") >= 0 ) {
						logger.info("error verfiy length={} poiid={} removeCookieId={} ",length,po.id,cookiePo.id);
						cookieIds.remove(cookiePo.id);
						cookiePo.flag = -1;
						cookiePo.updateDate = new Date();
						cookieMapper.updateSelective(cookiePo);
						detailIds.remove(po.id);
						return;
					}
					if(content.indexOf("window._appState") < 0) {
						logger.info("down html is not detail html poiid={} length={} cookieSize={} ",po.id,length,cookieIds.size());
						updatePo.id = po.id;
						updatePo.down = -1;
						mtMeishiInfoPoMapper.updateMeishiInfoSelective(updatePo);
						detailIds.remove(po.id);
						return;
					}
					Matcher matcher = pattern.matcher(content);
					updatePo.down = 11;
					if(matcher.find()) {
						updatePo.down = 1;
						String json = matcher.group(1);
						updateDetail(JSON.parseObject(json,Map.class));
					}
					num++;
					logger.info("has get num={} poiid={} length={} cookieSize={} ",num,po.id,length,cookieIds.size());
					updatePo.id = po.id;
					mtMeishiInfoPoMapper.updateMeishiInfoSelective(updatePo);
					detailIds.remove(po.id);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
	}
	public void downMeiDetail(int cityId)  {
		int num = 0;
		if(!downFlag) {
			logger.info("downFlag is stop ");
		}
		while(downFlag) {
			MtMeishiInfoPo po = mtMeishiInfoPoMapper.getNextMeishiDetail(cityId);
			if(po == null) {
				break;
			}
			if(null != po) {
				MtCityCookiePo cookiePo = getNextCookie();
				if(null == cookiePo ) {
					logger.error("has no cookie to be use ");
					break;
				}
				String cookieStr = cookiePo.cookie;
				logger.info(" down poiId={} ",po.id);
				String url = "http://www.meituan.com/meishi/"+po.id+"/";
				try {
					h.put("Cookie",cookieStr);
					String content = YhHttpUtil.sendHttpGet(url, null, h);
					int length = content.length();
					
					
					if((length >= 27372-1000 && length <= 27372+1000) || content.indexOf("验证中心") >= 0 ) {
						logger.info("error verfiy length={} poiid={} removeCookieId={} ",length,po.id,cookiePo.id);
						cookieIds.remove(cookiePo.id);
						cookiePo.flag = -1;
						cookiePo.updateDate = new Date();
						cookieMapper.updateSelective(cookiePo);
						//logger.error("sleep 10min and retry detail ");
						//Thread.sleep(1000*60*10);
						continue;
					}
					if(content.indexOf("window._appState") < 0) {
						logger.info("down html is not detail html poiid={} length={} cookieSize={} ",po.id,length,cookieIds.size());
						updatePo.id = po.id;
						updatePo.down = -1;
						mtMeishiInfoPoMapper.updateMeishiInfoSelective(updatePo);
						continue;
					}
					Matcher matcher = pattern.matcher(content);
					updatePo.down = 11;
					if(matcher.find()) {
						updatePo.down = 1;
//						FileOutputStream fos = new FileOutputStream(new File(filePath+po.id+".json"));
//						IOUtils.write(matcher.group(1),fos,"UTF-8");
//						IOUtils.closeQuietly(fos);
						String json = matcher.group(1);
						updateDetail(JSON.parseObject(json,Map.class));
					}
					num++;
					logger.info("has get num={} poiid={} length={} cookieSize={} ",num,po.id,length,cookieIds.size());
					updatePo.id = po.id;
					mtMeishiInfoPoMapper.updateMeishiInfoSelective(updatePo);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public void dealMeitFile(String[] names,int offset,int size)  {
		String filePath = "D:\\dxmtools\\myxm\\mytools\\meit01\\";
		int length =  names.length;
		for(int i = 0;i<size;i++) {
			if(i+offset < length) {
				String filename = names[i+offset];
				try {
					File file = new File(filePath+filename);
					String code = new FileCharsetDetector().guessFileEncoding(file);
					String encoding = "UTF-8";
					if(!"UTF-8".equals(code)) {
						encoding = "GBK";
					}
					
					String content = IOUtils.toString(new FileInputStream(file),encoding);
					Matcher matcher = pattern.matcher(content);
					if(matcher.find()) {
						String json = matcher.group(1);
						updateDetail(JSON.parseObject(json,Map.class));
						logger.error("has deal filename {} ",filename);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public void dealMeitJson(String[] names,int offset,int size)  {
		String filePath = "D:\\dxmtools\\myxm\\mytools\\meit02\\";
		int length =  names.length;
		for(int i = 0;i<size;i++) {
			if(i+offset < length) {
				String filename = names[i+offset];
				String json = "";
				try {
					File file = new File(filePath+filename);
					String code = new FileCharsetDetector().guessFileEncoding(file);
					String encoding = "UTF-8";
					if(!"UTF-8".equals(code)) {
						encoding = "GBK";
					}
					
					json = IOUtils.toString(new FileInputStream(file),"utf-8");
					updateDetail(JSON.parseObject(json,Map.class));
					logger.error("has deal filename {} ",filename);
				} catch (Exception e) {
					logger.error(json);
					e.printStackTrace();
				} 
			}
		}
	}
	class DealMeitFile implements Runnable{
		public MtManager mtManager;
		
		public String[] names;
		
		public int offset;
		
		public int size;
		
		
		public DealMeitFile(MtManager mtManager, String[] names, int offset, int size) {
			super();
			this.mtManager = mtManager;
			this.names = names;
			this.offset = offset;
			this.size = size;
		}


		@Override
		public void run() {
			mtManager.dealMeitJson(names, offset, size);
		}
		
	}
	public void dealMeit01() {
		String filePath = "D:\\dxmtools\\myxm\\mytools\\meit01\\";
		File fileDir = new File(filePath);
		String[] names = fileDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".html");
			}
		});
		
		int length = names.length;
		
		int totalThreads = 10;
		
		int pageSize= length/totalThreads;
		
		int lastSize = length - (totalThreads-1)*pageSize;
		
		for(int i=1;i<=totalThreads;i++) {
			int size = pageSize;
			int offset = (i-1)*pageSize;
			if(i == totalThreads) {
				size = lastSize;
			}
			Runnable run = new DealMeitFile(this, names, offset, size);
			new Thread(run).start();
		}
		
		
		
		
		
		
	}
	
	public void dealMeit02() {
		String filePath = "D:\\dxmtools\\myxm\\mytools\\meit02\\";
		File fileDir = new File(filePath);
		String[] names = fileDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		});
		
		int length = names.length;
		
		int totalThreads = 10;
		
		int pageSize= length/totalThreads;
		
		int lastSize = length - (totalThreads-1)*pageSize;
		
		for(int i=1;i<=totalThreads;i++) {
			int size = pageSize;
			int offset = (i-1)*pageSize;
			if(i == totalThreads) {
				size = lastSize;
			}
			Runnable run = new DealMeitFile(this, names, offset, size);
			new Thread(run).start();
		}
	}
	
	public Map<String,Object> getCmt(long poiId,int offset,int retry) {
		if(retry >= 4) {
			return null;
		}
		Map<String,String> h = new HashMap<>();
		h.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		//String url = "http://www.meituan.com/meishi/api/poi/getMerchantComment?uuid=cb04b2ed-2d5c-4379-8432-7233199767d7&platform=1&partner=126&originUrl=http%3A%2F%2Fwww.meituan.com%2Fmeishi%2F169036537%2F&riskLevel=1&optimusCode=1&id=169036537&userId=&offset=0&pageSize=10&sortType=1";
		//2437494
		//4817511
		
		Map<String,Object> param = new HashMap<>();
		param.put("platform","1");
		param.put("partner","126");
		param.put("originUrl","http%3A%2F%2Fwww.meituan.com%2Fmeishi%2F"+poiId+"%2F");
		param.put("id",poiId);
		param.put("sortType","1");
		param.put("riskLevel", "1");
		param.put("optimusCode","1");
		param.put("offset",offset+"");
		param.put("pageSize","400");
//		MtCityCookiePo cookiePo = getNextCookie();
//		if(null == cookiePo ) {
//			logger.error("**********has no cookie to be use*********** ");
//			return null;
//		}
		//String cookieStr = cookiePo.cookie;
		//h.put("Cookie",cookieStr);
		
		String url = "http://www.meituan.com/meishi/api/poi/getMerchantComment";
		String content = YhHttpUtil.sendHttpGetWithRetry(url, param,h);
		try {
			if(content.indexOf("验证中心") >= 0) {
				int num = retry+1;
				logger.error("######## cmt need verfiy sleep 10 min and retry retry={}  ########",num);
				Thread.sleep(1000*60*10);
				return getCmt(poiId,offset,num);
			}
			Map<String,Object> dataMap = JSON.parseObject(content);
			int status = ObjectUtil.getInt(dataMap,"status");
			String message = ObjectUtil.getStr(dataMap,"message");
			logger.info("get poiid={} cmt result status={} message={}",poiId,status,message);
			if(status != 0 && message.indexOf("timeout") >= 0 ) {
				return getCmt(poiId,offset,retry++);
			}
			if(status == 0) {
				return dataMap;
			}
		}catch(Exception e) {
			logger.error("err content={} ",content);
			logger.error("error {} ",e);
		}
		return null;
	}
	 private List<MtMeishiTagPo> parseTag(List<Map<String,Object>> tags,long poiId){
	    	ArrayList<MtMeishiTagPo> datas = new ArrayList<MtMeishiTagPo>();
	    	if(null != tags && tags.size() > 0) {
	    		for(Map<String,Object> tag :  tags) {
	    			MtMeishiTagPo po = new MtMeishiTagPo();
	    			po.countNum = ObjectUtil.getInt(tag,"count");
	    			po.poiId = poiId;
	    			po.tag = ObjectUtil.getStr(tag,"tag");
	    			datas.add(po);
	    		}
	    	}
	    	return datas;
	    }
	@Autowired
	public MtMeishiInfoService mtMeishiInfoService;
	public void saveCmt(MtMeishiInfoPo po,int offset,Map<String,Object> data) {
		int cityId = po.cityId;
		long poiId = po.id;
		List<Map<String,Object>> comments = ObjectUtil.castListObj(data.get("comments"));
		if(offset == 0) {
			List<Map<String,Object>> tags = ObjectUtil.castListObj(data.get("tags"));
			List<MtMeishiTagPo> tagList = parseTag(tags, poiId);
			if(ObjectUtil.getSize(tagList) > 0) {
				mtMeishiInfoPoMapper.insertMtMeishiTagBatch(tagList);
			}
		}
		int cmtGetSize = mtMeishiInfoService.addMeishiCmts(comments, cityId, poiId);
		
		logger.info("save poiId={} offset={} cmtGetSize={} ",po.id,offset,cmtGetSize);
		
		
		
	}
	public int downCmt(MtMeishiInfoPo po) {
		if(null == po) {
			logger.error("null po  ");
			return 0;
		}
		long poiId = po.id;
		int cmtGet = ObjectUtil.parseInt(po.cmtGet);
		int cmtNum = ObjectUtil.parseInt(po.cmtNum);
		int pageSize = 400;
		
		Map<String,Object> dataMap = getCmt(poiId, cmtGet,0);
		if(null == dataMap) {
			logger.error("get cmt poiid={} offset={} cmtNum={} is error  ",poiId,cmtGet,cmtNum);
			return -1;
		}
		
		//first page 
		Map<String,Object> data = ObjectUtil.castMapObj(dataMap.get("data"));
		int total= ObjectUtil.getInt(data,"total");
		MtMeishiInfoPo  totalUpdate = new MtMeishiInfoPo();
		totalUpdate.id = poiId;
		totalUpdate.cmtNum = total;
		mtMeishiInfoPoMapper.updateMeishiInfoSelective(totalUpdate);
		
		int totalPage = (total+pageSize-1)/pageSize;
		logger.info("poiId={} has total={} cmt offset={}   ",po.id,total,cmtGet);
		//save data 
		saveCmt(po, cmtGet, data);
		for(int i=2;i<= totalPage;i++) {
			logger.info("poiid={} get {} page cmt total={} ",poiId,i,total);
			int offset = (i-1)*pageSize+cmtGet;
			if(offset >= cmtNum) {
				return 0;
			}
			dataMap = getCmt(poiId, offset,0);
			if(null != dataMap) {
				data = ObjectUtil.castMapObj(dataMap.get("data"));
				saveCmt(po,offset,data);
			}
		}
		return 0;
	}
}

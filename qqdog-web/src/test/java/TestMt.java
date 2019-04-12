import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.utils.FileCharsetDetector;
import com.man.qqdog.client.dto.MtNextDetailDto;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;

public class TestMt {

	
	@Test
	public void test01() throws IOException {
		LinkedList<MtMeishiInfoPo> queue = new LinkedList<>();
		
		for(int i = 0;i<3;i++) {
			MtMeishiInfoPo po = new MtMeishiInfoPo();
			po.id = (long) (i);
			po.cmtNum = i+100;
			po.cmtGet = 1;
			queue.add(po);
		}
		System.out.println("size =="+queue.size());
		System.out.println(queue.getFirst().id);
		queue.removeFirst();
		System.out.println("size =="+queue.size());
		System.out.println(queue.getFirst().id);
		queue.removeFirst();
		System.out.println("size =="+queue.size());
		
		queue.removeFirst();
		System.out.println("size =="+queue.size());
		
		MtMeishiInfoPo po = new MtMeishiInfoPo();
		po.id = 100L;
		queue.add(po);
		System.out.println(queue.getFirst().id);
		queue.removeFirst();
		System.out.println("size =="+queue.size());
		
		System.out.println(JSON.toJSONString(null));
	}
	
	@Test
	public void testSize() {
		File file = new File("D:\\01.pdf");
		System.out.println(file.length());
	}
	
	@Test
	public void testExec() throws Exception {
		String chrome = "chrome www.baidu.com?q=ok ";
		 Process process = Runtime.getRuntime().exec("cmd /k " + chrome);
		 
		 
		 
	}
	
	
	@Test
	public void testRemoveList() {
		List<Long> aL = new ArrayList<>();
		aL.add(1L);
		aL.add(2L);
		aL.add(3L);
		aL.add(2L);
		
		List<Long> bL = new ArrayList<>();
	bL.add(2L);
		bL.add(3L);
		
		System.out.println(aL.removeAll(bL));
		
		System.out.println(aL);
	}
	
	@Test
	public void testtime() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
		
	}
	
	@Test
	public void testTime() {
		MtNextDetailDto a1 = new MtNextDetailDto(1,2,3);
		
		MtNextDetailDto b1 = new MtNextDetailDto(12,22,32);
		
		List<MtNextDetailDto> datas = new ArrayList<>();
		datas.add(a1);
		datas.add(b1);
		System.out.println(JSON.toJSONString(datas));
		datas.remove(a1);
		
		System.out.println(JSON.toJSONString(datas));
	}
	
	@Test
	public void testHttpR() throws IOException {
		Map<String,String> h = new HashMap<>();
		String url = "http://www.meituan.com/meishi/112131982/";
		h.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/65.0");
		//h.put("Cookie","_lxsdk_cuid=16772e423992-005ffce547e72d-5e442e19-144000-16772e4239ac8; _hc.v=4ff6733e-3286-14a3-3995-17b7f00af901.1551945572; iuuid=7B4D70D451FC84F4A31EC0CD07D1430C58DF8D0E520F1E5DD69C15EAE1412E4F; cityname=%E6%98%86%E6%98%8E; _lxsdk=7B4D70D451FC84F4A31EC0CD07D1430C58DF8D0E520F1E5DD69C15EAE1412E4F; userId=469734992; token=QDEhl_fqDDh5by_aHkUwNEhuoPkAAAAAEggAALVk-zQIaDoPMXSzwCWudeIPG6JTJIQo_amjU52NUvy05O5Jek_3qQ8VMrhPEqn0BA; client-id=a871ac8c-c7f2-4ba7-a65e-2ea98ea9db30; __mta=245734971.1552653810326.1552653817708.1552653849314.3; uuid=cb04b2ed-2d5c-4379-8432-7233199767d7; ci=73; rvct=73%2C1; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; lat=25.015874; lng=102.740346; _lxsdk_s=1698e9a5db2-26a-8eb-bf5%7C%7C23");
		h.put("Cookie","_lxsdk_cuid=16772e423992-005ffce547e72d-5e442e19-144000-16772e4239ac8; _hc.v=4ff6733e-3286-14a3-3995-17b7f00af901.1551945572; iuuid=7B4D70D451FC84F4A31EC0CD07D1430C58DF8D0E520F1E5DD69C15EAE1412E4F; cityname=%E6%98%86%E6%98%8E; _lxsdk=7B4D70D451FC84F4A31EC0CD07D1430C58DF8D0E520F1E5DD69C15EAE1412E4F; userId=469734992; token=QDEhl_fqDDh5by_aHkUwNEhuoPkAAAAAEggAALVk-zQIaDoPMXSzwCWudeIPG6JTJIQo_amjU52NUvy05O5Jek_3qQ8VMrhPEqn0BA; client-id=a871ac8c-c7f2-4ba7-a65e-2ea98ea9db30; __mta=245734971.1552653810326.1552653817708.1552653849314.3; uuid=cb04b2ed-2d5c-4379-8432-7233199767d7; ci=73; rvct=73%2C1; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; lat=39.970939; lng=116.489239; _lxsdk_s=1698f4595ff-a4-468-2ec%7C%7C6");
		//h.put("Host","www.meituan.com");
	    String content = YhHttpUtil.sendHttpGet(url, null,h);
	    System.out.println(content);
	    System.out.println(content.length());
	}
	
	@Test
	public void testCmtGet() throws Exception{
		Map<String,String> h = new HashMap<>();
		h.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		//String url = "http://www.meituan.com/meishi/api/poi/getMerchantComment?uuid=cb04b2ed-2d5c-4379-8432-7233199767d7&platform=1&partner=126&originUrl=http%3A%2F%2Fwww.meituan.com%2Fmeishi%2F169036537%2F&riskLevel=1&optimusCode=1&id=169036537&userId=&offset=0&pageSize=10&sortType=1";
		//2437494
		//4817511
		//h.put("Cookie","uuid=da664a6a801545c19d1d.1553069894.1.0.0; _lxsdk_cuid=1699a2defe8c8-08b78a860f5201-5e442e19-144000-1699a2defe8c8; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; __mta=156078980.1553127851745.1553127851745.1553127851745.1; webloc_geo=25.061803%2C102.658775%2Cwgs84; ci=952; rvct=952; _lxsdk_s=1699da245b3-534-172-59b%7C%7C6");
		long poiId = 6786835;
		Map<String,Object> param = new HashMap<>();
		param.put("platform","1");
		param.put("partner","126");
		param.put("originUrl","http%3A%2F%2Fwww.meituan.com%2Fmeishi%2F"+poiId+"%2F");
		param.put("id",poiId);
		param.put("sortType","1");
		param.put("riskLevel", "1");
		param.put("optimusCode","1");
		param.put("offset","0");
		param.put("pageSize","400");
		
		String url = "http://www.meituan.com/meishi/api/poi/getMerchantComment";
		String content = YhHttpUtil.sendHttpGet(url, param,h);
		System.out.println(content);
	}
	
	@Test
	public void testCook() {
		boolean isMatch = Pattern.matches("http[s]*://www.meituan.com/meishi/[0-9]+/","https://www.meituan.com/meishi/6376791/");
		System.out.println(isMatch);
	}
	
	@Test
	public void testHtml() throws FileNotFoundException, IOException {
		String path ="D:\\dxmtools\\myxm\\mytools\\meit\\41517689.html";
		File file = new File(path);
		 System.out.println("文件编码:" + new FileCharsetDetector().guessFileEncoding(file));
		//System.out.println(IOUtils.toString(new FileInputStream(file),"GBK"));
//		String str = IOUtils.toString(new FileInputStream(file),"UTF-8");
//		System.out.println(new String(str.getBytes("UTF-8"),"UTF-8"));
	}
	
	@Test
	public void testGetFileName() {
		String path ="D:\\dxmtools\\myxm\\mytools\\meit\\";
		File file = new File(path);
		String[] names = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				//System.out.println(name);
				return name.endsWith(".html");
			}
		});
		System.out.println(JSON.toJSONString(names));
		Set<String> ids = new HashSet<>();
		for(String name : names) {
			if(Pattern.matches("[0-9]+",FilenameUtils.getBaseName(name))) {
				ids.add(FilenameUtils.getBaseName(name));
			}
		}
		System.out.println(JSON.toJSONString(ids));
	}
	
	@Test
	public void testDealFileName() throws FileNotFoundException, IOException {
		String path ="D:\\dxmtools\\myxm\\mytools\\meit\\";
		File fileDir = new File(path);
		File[] files = fileDir.listFiles();
		Set<Long> okIds = new HashSet<>();
		Set<Long> errIds = new HashSet<>();
		for(File file : files ) {
			String fileName = FilenameUtils.getBaseName(file.getName());
			if(Pattern.matches("[0-9]+",fileName)) {
				long id = ObjectUtil.parseLong(fileName);
				long fileSize = file.length();
				if(fileSize >= 65536-20*1024  && fileSize <= 65536+20*1024) {
					okIds.add(id);
				}else {
					errIds.add(id);
				}
			}
		}
		
		IOUtils.write(JSON.toJSONString(okIds),new FileOutputStream(new File("D:\\okIds.json")),"utf-8");
		IOUtils.write(JSON.toJSONString(errIds),new FileOutputStream(new File("D:\\errIds.json")),"utf-8");
		System.out.println(" is ok ");
	}
	
	@Test
	public void testData() throws FileNotFoundException, IOException {
		String filePath = "D:\\dxmtools\\myxm\\mytools\\meit\\4952489.html";
		String content = IOUtils.toString(new FileInputStream(new File(filePath)),"GBK");
		String dxm = "djskdskds278237283djskdjskd";
		Pattern pattern = Pattern.compile("<script>window._appState =(.*?);</script>");
		Matcher matcher = pattern.matcher(content);
		if(matcher.find()) {
				System.out.println(matcher.group(1));
		}
	}
	
	
	@Test
	public void testZmmCp() throws Exception{
		String filePath = "D:\\zmm\\jiyin";
		File fileDir = new File(filePath);
		File[] files = fileDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".fasta");
			}
		});
		Pattern pattern = Pattern.compile("([a-zA-Z]+)-.*?([A-Za-z0-9]+)\\.");
		File newFile = new File("D:\\zmm\\all.fasta");
		FileOutputStream fos = new FileOutputStream(newFile);
		for(File file : files) {
			String filename = file.getName();
			Matcher matcher = pattern.matcher(filename);
			String newname = filename;
			if(matcher.find()) {
				newname=">"+matcher.group(1)+matcher.group(2);
			}
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			while((str=bf.readLine()) != null) {
				if(str.startsWith(">")) {
					str = newname;
				}
				if(str.length() > 0) {
					IOUtils.write(str+"\r\n", fos,"utf-8");
				}
			}
		}
		IOUtils.closeQuietly(fos);
	}
	
	@Test
	public void testMeit01() throws Exception {
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
				if(i == totalThreads) {
					size = lastSize;
				}
				System.out.println(i+"----"+(i-1)*pageSize+"---------"+size);
			}
		
		
		
		System.out.println(names[0]);
		String content = IOUtils.toString(new FileInputStream(new File(filePath+names[0])),"utf-8");
		System.out.println(content);
	}
	
	
}

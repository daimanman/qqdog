import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;

public class TestRR {

	@Test
	public void getUnivsData() throws Exception {
		String collegeJson = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\college.json")));
		Map<String,Object> map = JSON.parseObject(collegeJson,Map.class);
		List provs = ObjectUtil.castListObj(map.get("provs"));
		List<String> schoolIds = new ArrayList<String>();
		StringBuffer sqlSb = new StringBuffer();
		for(Object prov : provs) {
			Map<String,Object> provMap = ObjectUtil.castMapObj(prov);
			String provinceId = ObjectUtil.getStr(provMap,"id");
			String provinceName = ObjectUtil.getStr(provMap,"name");
			StringBuffer provSb = new StringBuffer("insert into college_info(school_name,school_id,province_name,province_id) values ");
			List<Map<String,Object>> univs = ObjectUtil.castListObj(provMap.get("univs"));
			int index = 0;
			int size = univs.size();
			for(Map<String,Object> univMap : univs) {
				String schoolName = ObjectUtil.getStr(univMap,"name");
				String schoolId =  ObjectUtil.getStr(univMap,"id");
				String sql = String.format("('%s',%s,'%s',%s)",schoolName,schoolId,provinceName,provinceId);
				schoolIds.add(schoolId);
				provSb.append(sql);
				index++;
				if(index < size ) {
					provSb.append(",");
				}
			}
			sqlSb.append(provSb.toString()).append(";\n");
		}
		
		IOUtils.write(JSON.toJSONString(schoolIds),new FileOutputStream(new File("D:\\\\dxmtools\\\\myxm\\\\mytools\\\\renren\\\\schoolIds.json")));
		IOUtils.write(sqlSb.toString(),new FileOutputStream(new File("D:\\\\dxmtools\\\\myxm\\\\mytools\\\\renren\\\\school.sql")));
		System.out.println("is ok");
	}
	
	@Test
	public void testGetDepart() throws Exception {
		downDepart(Arrays.asList("1009"));
	}
	public void downDepart(List<String> schooldIds) throws FileNotFoundException, IOException {
		if(null == schooldIds || schooldIds.size() == 0) {
			return;
		}
		for(String id : schooldIds) {
			String url = "http://www.renren.com/GetDep.do?id="+id;
			String content = YhHttpUtil.sendHttpGetWithRetry(url, null,null);
			IOUtils.write(content,new FileOutputStream(new File("D:\\\\dxmtools\\\\myxm\\\\mytools\\\\renren\\\\"+id+".html")));
			System.out.println(id+" is ok");
		}
	}
	
	@Test
	public void testDownAllDepart() throws Exception {
		String json = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\schoolIds.json")));
		List<String> sids = JSON.parseObject(json,List.class);
		int size = sids.size();
		int pageSize = 300;
		int pages = (size+pageSize-1)/pageSize;
		for(int page = 1 ;page<=pages;page++) {
			int start = (page-1)*pageSize;
			int end = start+pageSize-1;
			List<String> tids = new ArrayList<>();
			for(;(start<=end && start<size);start++) {
				tids.add(sids.get(start));
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						downDepart(tids);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		System.out.println("wait---");
		Thread.sleep(1000*3600);
	}
	
	
	@Test
	public void testProvs() throws Exception {
		String json = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\prov.json")));
		Map<String,Object> map  = JSON.parseObject(json,Map.class);
		Set<String> keys = map.keySet();
		StringBuffer sqlSb = new StringBuffer();
		for(String name : keys) {
			String cid = ObjectUtil.getStr(map,name).substring(0,2);
			String pid = "0";
			String sql = String.format("insert into city_info(cid,pid,name) values (%s,%s,'%s');\n",cid,pid,name);
			sqlSb.append(sql);
		}
		IOUtils.write(sqlSb.toString(),new FileOutputStream(new File("D:\\\\dxmtools\\\\myxm\\\\mytools\\\\renren\\\\prov.sql")));
	}
	
	
	@Test
	public void testCityTextJson() throws Exception {
		String json = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\city.txt.json")));
		Map<String,Object> map  = JSON.parseObject(json,Map.class);
		Set<String> keys = map.keySet();
		StringBuffer sqlSb = new StringBuffer("insert into city_info(cid,pid,name) values");
		int i = 0;
		for(String cid : keys) {
			String name = ObjectUtil.getStr(map,cid);
			String pid = cid.substring(0,2);
			if(cid.length() == 6) {
				pid = cid.substring(0,4);
			}
			String sql = String.format(" (%s,%s,'%s') ",cid,pid,name);
			sqlSb.append(sql);
			i++;
			if(i < keys.size()) {
				sqlSb.append(",");
			}
			
		}
		IOUtils.write(sqlSb.toString(),new FileOutputStream(new File("D:\\\\dxmtools\\\\myxm\\\\mytools\\\\renren\\\\city.txt.sql")));
	}
	
	
	@Test
	public void testDowbAllHighschool() throws FileNotFoundException, IOException {
		Set<String> zhixiashi = new HashSet<>(Arrays.asList("1101","1201","3101","5001"));
		String json = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\city.txt.json")));
		Map<String,Object> map  = JSON.parseObject(json,Map.class);
		Set<String> keys = map.keySet();
		for(String cityId : keys) {
			String cid = cityId.substring(0,4);
			if(zhixiashi.contains(cid)) {
				continue;
			}
			String url = "http://support.renren.com/highschool/"+cityId+".html";
			String content = YhHttpUtil.sendHttpGetWithRetry(url, null, null);
			IOUtils.write(content,new FileOutputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\high\\"+cityId+".html")));
			System.out.println(cityId+" is ok ");
		}
	}
	
	
	@Test
	public void testDownZhiXiaShiAllHighschool() throws FileNotFoundException, IOException {
		Set<String> zhixiashi = new HashSet<>(Arrays.asList("1101","1201","3101","5001"));
		for(String cityId : zhixiashi) {
			String url = "http://support.renren.com/highschool/"+cityId+".html";
			String content = YhHttpUtil.sendHttpGetWithRetry(url, null, null);
			IOUtils.write(content,new FileOutputStream(new File("D:\\dxmtools\\myxm\\mytools\\renren\\high\\"+cityId+".html")));
			System.out.println(cityId+" is ok ");
		}
	}
	
	@Test
	public void testRR() {
			String sql = "(10328622,'Pope Paul Vi College',810404,''''葵青区''";
			System.out.println(sql.replaceAll("[']+","'"));
	}
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestSql {

	
	public Map<String,Double> getTabMap() throws Exception{
		String path = "D:\\pgsql\\tab.json";
		return JSON.parseObject(IOUtils.toString(new FileInputStream(new File(path))),Map.class);
	}
	@Test
	public void test01() throws Exception {
		System.out.println(getTabMap());
	}
	
	@Test
	public void test02() throws Exception{
		String filepath = "D:\\pgsql\\postgresql-2019-01-22_180816.log";
		FileReader fr = new FileReader(new File(filepath));
		BufferedReader bf = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String str = "";
		Map<String,Double> tabMap = getTabMap();
		Set<String> tabs = tabMap.keySet();
		int i = 0;
		while((str = bf.readLine()) != null) {
			for(String tab : tabs) {
				if(str.toLowerCase().indexOf(tab) >=0 ) {
					sb.append(tab).append(" ").append(tabMap.get(tab)+"").append(" ");
					sb.append(str).append("\n");
//					System.out.println(tab);
//					System.out.println(str);
					i++;
					break;
				}
			}
		}
		IOUtils.write(sb.toString(),new FileOutputStream(new File("D:\\pgsql\\180816_out.log")));
		System.out.println(i);

	}
}

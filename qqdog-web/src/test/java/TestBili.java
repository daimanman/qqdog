import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.utils.ObjectUtil;

public class TestBili {

	int i = 0;
	List<String> names = new ArrayList<>();
	List<String> paths = new ArrayList<>();
	
	public String getName(String path) {
		try {
			String json = IOUtils.toString(new FileInputStream(new File(path)));
			Map<String,Object> map = JSON.parseObject(json,Map.class);
			return ObjectUtil.getStr(ObjectUtil.castMapObj(map.get("page_data")),"part");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	@Test
	public void testBili() {
		String newPath = "D:\\dxmtools\\myxm\\mytools\\bili\\01\\";
		Map<String,String> maps = new HashMap<>();
		getFile(new File("D:\\dxmtools\\myxm\\mytools\\bili\\36798233"));
		for(int i = 0;i<names.size();i++) {
			maps.put(names.get(i), paths.get(i));
			String newName = getName(names.get(i));
			System.out.println(i+"-"+newName);
			new File(paths.get(i)).renameTo(new File(newPath+i+"-"+newName+".mp4"));
		}
		
		//System.out.println(maps);
	
	}
	
	public void getFile(File file) {
		File[] files = file.listFiles();
		
		
		if(files.length > 0) {
			for(File f : files) {
				if(f.isDirectory()) {
					getFile(f);
				}else {
					if("entry.json".equals(f.getName())) {
						names.add(f.getPath());
					}
					if(f.getName().endsWith(".blv")) {
						paths.add(f.getPath());
					}
				}
			}
		}
	}
}

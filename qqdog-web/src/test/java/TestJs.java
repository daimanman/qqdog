import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;

public class TestJs {

	
	
	@Test
	public void testDemo01() {
		System.setProperty("webdriver.chrome.driver","D:\\dxmtools\\myxm\\js\\chromedriver.exe");//chromedriver服务地址
        WebDriver driver =new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动
        driver.get("http://www.baidu.com");//打开指定的网站
        driver.findElement(By.id("kw")).sendKeys(new  String[] {"hello"});//找到kw元素的id，然后输入hello
        driver.findElement(By.id("su")).click(); //点击按扭
        try {
            /**
             * WebDriver自带了一个智能等待的方法。
            dr.manage().timeouts().implicitlyWait(arg0, arg1）；
            Arg0：等待的时间长度，int 类型 ；
            Arg1：等待时间的单位 TimeUnit.SECONDS 一般用秒作为单位。
             */
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);        
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * dr.quit()和dr.close()都可以退出浏览器,简单的说一下两者的区别：第一个close，
         * 如果打开了多个页面是关不干净的，它只关闭当前的一个页面。第二个quit，
         * 是退出了所有Webdriver所有的窗口，退的非常干净，所以推荐使用quit最为一个case退出的方法。
         */
        driver.close();
        //driver.quit();//退出浏览器
	}
	
	@Test
	public void testDemo02() throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\dxmtools\\myxm\\js\\chromedriver.exe");//chromedriver服务地址
        WebDriver browser =new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动
        browser.get("https://i.qq.com");//打开指定的网站
        browser.switchTo().frame("login_frame");
        Thread.sleep(500);  

        WebElement button = browser.findElement(By.id("switcher_plogin"));
        button.click();
        
        Thread.sleep(500);
        browser.findElement(By.id("u")).sendKeys("2700024009");
        
        Thread.sleep(500);
        browser.findElement(By.id("p")).sendKeys("654321manxiao");
        
        Thread.sleep(500);
       // browser.findElement(By.id("login_buttom")).click();
        
        
	}
	
	@Test
	public void testDemo03() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("D:\\dxmtools\\myxm\\qqdog\\tools\\helper.crx"));
		System.setProperty("webdriver.chrome.driver","D:\\dxmtools\\myxm\\js\\chromedriver.exe");//chromedriver服务地址
        WebDriver browser =new ChromeDriver(options); 
        browser.get("https://i.qq.com");
        browser.switchTo().frame("login_frame");
        Thread.sleep(500);  

        WebElement button = browser.findElement(By.id("switcher_plogin"));
        button.click();
        
        Thread.sleep(500);
        browser.findElement(By.id("u")).sendKeys("2821224626");
        
        Thread.sleep(500);
        browser.findElement(By.id("p")).sendKeys("2635913manxiao");
        
        Thread.sleep(500);
        //browser.findElement(By.id("login_button")).click();
        
        browser.get("about:blank?qq=133");
	}
	
	@Test
	public void testDemo04() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("D:\\dxmtools\\myxm\\qqdog\\tools\\helper.crx"));
		System.setProperty("webdriver.chrome.driver","D:\\dxmtools\\myxm\\js\\chromedriver.exe");//chromedriver服务地址
        WebDriver browser =new ChromeDriver(options); 
        browser.get("about:blank/128912");
        String url = browser.getCurrentUrl();
        System.out.println(url);
        Thread.sleep(500);
        ((JavascriptExecutor) browser).executeScript("window.open(\"http://baidu.com\");");
        Set<String> winHandels = browser.getWindowHandles();
        List<String> it = new ArrayList<String>(winHandels);
        browser.switchTo().window(it.get(1));
        url = browser.getCurrentUrl();
        System.out.println(url);
	}
	
	public Map<String,String> getQMap() throws FileNotFoundException, IOException{
		String cm = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\1.json")),"utf-8");
		return JSON.parseObject(cm,new TypeReference<Map<String,String>>(){});
	}
	
	public List<String> getQList() throws FileNotFoundException, IOException{
		String cm = IOUtils.toString(new FileInputStream(new File("D:\\dxmtools\\myxm\\3.json")),"utf-8");
		return JSON.parseObject(cm,new TypeReference<List<String>>(){});
	}
	
	
	
	@Test
	public void saveQList() throws Exception {
		Set<String> keys = getQMap().keySet();
		System.out.println(keys.size());
		IOUtils.write(JSON.toJSONString(keys),new FileOutputStream(new File("D:\\\\dxmtools\\\\myxm\\\\3.json")));
		
	}
	
	@Test
	public void testSize() throws FileNotFoundException, IOException {
		System.out.println(getQList().size());
	}
	
	public void loginq( WebDriver browser,String u,String p) throws Exception {
			//browser.get("https://i.qq.com");
	        browser.switchTo().frame("login_frame");
	        Thread.sleep(500);  

	        WebElement button = browser.findElement(By.id("switcher_plogin"));
	        button.click();
	        
	        Thread.sleep(500);
	        browser.findElement(By.id("u")).sendKeys(u);
	        
	        Thread.sleep(500);
	        browser.findElement(By.id("p")).sendKeys(p);
	        
	       // Thread.sleep(500);
	        //browser.findElement(By.id("login_button")).click();
	}
	
	@Test
	public void testDemo192_0_10() throws Exception {
		Map<String,String> qmap = getQMap();
		List<String> qList = getQList();
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("D:\\dxmtools\\myxm\\helper-192.crx"));
		System.setProperty("webdriver.chrome.driver","D:\\dxmtools\\myxm\\js\\chromedriver.exe");//chromedriver服务地址
        WebDriver browser =new ChromeDriver(options);
        int size = qList.size();
        int end = size/2;
        for(int i = 0 ;i<end;i++) {
        	String url = String.format("https://i.qq.com/?key=%s",qList.get(i));
        	if(i == 0) {
        		browser.get(url);
        	}else {
        		 ((JavascriptExecutor) browser).executeScript("window.open(\""+url+"\");");
        	}
        }
        Set<String> winHandels = browser.getWindowHandles();
        List<String> itList = new ArrayList<String>(winHandels);
        for(String it:itList) {
        	 browser.switchTo().window(it);
             String[] urlArr = browser.getCurrentUrl().split("=");
             String kq = "";
             if(urlArr.length >= 2) {
             	kq = urlArr[1];
             	String p = qmap.get(kq);
             	 System.out.println(kq+"--"+p);
                 loginq(browser, kq, p);
                 Thread.sleep(5000);
             }
        }
	}
	@Test
	public void testDemo193() throws Exception {
		Map<String,String> qmap = getQMap();
		List<String> qList = getQList();
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("D:\\dxmtools\\myxm\\helper-193.crx"));
		System.setProperty("webdriver.chrome.driver","D:\\dxmtools\\myxm\\js\\chromedriver.exe");//chromedriver服务地址
        WebDriver browser =new ChromeDriver(options);
        int size = qList.size();
        int end = size/2;
        for(int i = end ;i<size;i++) {
        	String url = String.format("https://i.qq.com/?key=%s",qList.get(i));
        	if(i == 0) {
        		browser.get(url);
        	}else {
        		 ((JavascriptExecutor) browser).executeScript("window.open(\""+url+"\");");
        	}
        }
        Set<String> winHandels = browser.getWindowHandles();
        List<String> itList = new ArrayList<String>(winHandels);
        for(String it:itList) {
        	 browser.switchTo().window(it);
             String[] urlArr = browser.getCurrentUrl().split("=");
             String kq = "";
             if(urlArr.length >= 2) {
             	kq = urlArr[1];
             	String p = qmap.get(kq);
             	 System.out.println(kq+"--"+p);
                 loginq(browser, kq, p);
                 Thread.sleep(5000);
             }
        }
	}
	
	
	@Test
	public void testSql() throws Exception {
		List<String> qList1 = getQList();
		List<String> qList = new ArrayList<>();
		int size = qList1.size();
        int end = size/2;
        for(int i = end ;i<size;i++) {
        	qList.add(qList1.get(i));
        }
		Map<String,String> qM = new HashMap<String,String>();
		for(String q:qList) {
			qM.put(q,q);
		}
        String content = YhHttpUtil.sendHttpGet("http://192.168.1.193:54321/showUid",null,null);
        Map<String,Object> cm = ObjectUtil.castMapObj(JSON.parseObject(content,Map.class));
        List cmList = ObjectUtil.castListObj(cm.get("msg"));
        Map<String,Object> cmMap = new HashMap<>();
        for(Object cmk:cmList) {
        	cmMap.put(ObjectUtil.toString(cmk,""),cm);
        }
        System.out.println(cmMap.keySet());
        for(String q:qList) {
        	if(cmMap.get(q)==null) {
        		System.out.println(q);
        	}
        }
        
        
	}
	
	
	
	
}

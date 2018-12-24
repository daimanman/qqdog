import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
	
	
}

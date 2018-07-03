package demo;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoClass {
	@Test
	public void openbrowser() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("chromedriver.exe");
        File f = new File("Driver");
        if (!f.exists()) {
            f.mkdirs();
        }
        File chromeDriver = new File("Driver" + File.separator + "chromedriver.exe");
        if (!chromeDriver.exists()) {
            chromeDriver.createNewFile();
            org.apache.commons.io.FileUtils.copyURLToFile(resource, chromeDriver);
        }
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        WebDriver  driver= new ChromeDriver(); 
		driver.get("http://ec2-54-191-190-7.us-west-2.compute.amazonaws.com/");
		driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(4000, TimeUnit.SECONDS);	

		driver.findElement(By.xpath("//li[@class='trainer']/a")).click();
		driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
		
		Set<String> WindowCount = driver.getWindowHandles(); 		
		Iterator<String> iter = WindowCount.iterator();
						
		String parentwindowid = iter.next();
		String Childwindowid = iter.next();
			
		driver.switchTo().window(Childwindowid);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//li[@class='login']/a")).click();
		Thread.sleep(3000);    
		
	}
	
}

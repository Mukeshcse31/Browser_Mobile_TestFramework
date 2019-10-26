package commonLib;

import java.io.File;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Initialize extends SetUp{
	
	public WebDriver driver=null;
	
	/*
	 * Start browser, get Data
	 */
	
	public Initialize Init(String tc){
		
		getData(tc);
		if(!(data==null)){
			
			LinkedHashMap<String,String> tempdata;
			tempdata=data.get("Run");
				
		String path;
		String browser=tempdata.get("Browser");
		switch (browser){
			case "chrome" :
				
				 path=new File(System.getProperty("user.dir")).getAbsolutePath()+ "\\lib\\chromedriver.exe";
				  System.setProperty("webdriver.chrome.driver", path);
				  driver=new ChromeDriver();
			break;
			
			case "iexplore" :
				 path=new File(System.getProperty("user.dir")).getAbsolutePath()+ "\\lib\\IEDriverServer.exe";
				  System.setProperty("webdriver.ie.driver", path);
				  driver=new InternetExplorerDriver();
			break;
				  
			case "firefox" :
				DesiredCapabilities caps = DesiredCapabilities.firefox();
				caps.setCapability("unexpectedAlertBehaviour", "ignore");
				driver = new FirefoxDriver(caps);
				break;
			}
		}
		
		Initialize init=new Initialize();
		init.data=data;
		init.driver=driver;
		//init.A_driver=A_driver;
		
	  return init;
	}
	
}
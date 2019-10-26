package commonLib;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public class A_Initialize extends SetUp {

	public AppiumDriver driver;
	//public WebDriver driver;
	DesiredCapabilities capabilities;

	/*
	 * Start browser, get Data
	 */

	public A_Initialize Init(String tc) throws MalformedURLException {
		A_Initialize init = new A_Initialize();
		getData(tc);
		if (!(data == null)) {

			capabilities = new DesiredCapabilities();

			capabilities.setCapability("deviceName", "SGH-I747");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability(CapabilityType.VERSION, "4.4");
			capabilities.setCapability(CapabilityType.PLATFORM, "Windows");

			// Here we mention the app's package name, to find the package name
			// we have to convert .apk file into java class files
			capabilities.setCapability("appPackage", "com.XXXjohns.android");
			// Here we mention the activity name, which is invoked initially as
			// app's first page.
			capabilities.setCapability("appActivity", ".IntroActivity");
			Reporter.log("Appium Driver " + "\n");
			AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			//WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
			Reporter.log("Appium Driver " + "\n");
	
			init.data = data;
			init.driver = driver;
		}
		return init;
	}
}
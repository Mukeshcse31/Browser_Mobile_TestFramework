package commonLib;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.appium.java_client.AppiumDriver;

public class A_ReusableLibrary extends ReusLibrary{
	
	protected AppiumDriver driver;
	//protected WebDriver driver;
	private WebDriverWait wait, wait_2;
	
	private String Pass=" PASS \t";
	private String Fail=" FAIL \t";
	
	public A_ReusableLibrary(A_ReusableLibrary reusableLibrary){
		super(reusableLibrary);
		if(!(reusableLibrary == null)){
		this.driver=reusableLibrary.driver;
		
		wait = new WebDriverWait(driver,2);
		wait_2 = new WebDriverWait(driver,2);
		
		}
	}
	
	public void setDriver(AppiumDriver driver) {
		// TODO Auto-generated method stub
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	  public void afterMethod(ITestResult result){
		  
	 String status="";
	  reportInfo.setTestMethod(result.getName());
	 
	  if (result.getStatus()==1)
		  status="Pass";
	  else
		  status="Fail";
	  
	  Reporter.log(reportInfo.TcName + "\t" + "Method : " + result.getName() + " - "+ status + "\n");
	  Logs.info(reportInfo.TcName + "\t" + "Method : " + result.getName() + " - "+ status + "\n");
	  
	  TakeScreenshot(); 
}
		public synchronized void TakeScreenshot(){
			
			  reportInfo.addStep();
			
			  String screenshotPath=reportInfo.path.toAbsolutePath() + "\\" + reportInfo.TcName + "_" + reportInfo.TestMethod + "_" + reportInfo.StepNo + ".jpg";
			  
				try {
					
					// execute A_Screenshot.bat
					Runtime.getRuntime().exec("cmd /c start A_Screenshot.bat " + screenshotPath);
					
					  Logs.info(reportInfo.TcName + "\t" + "SCREENSHOT taken : " + screenshotPath);
					  Reporter.log(reportInfo.TcName + "\t" + "SCREENSHOT taken : " + screenshotPath);
				} catch (IOException e) {
					Logs.fatal(reportInfo.TcName + "\t" + Fail + "Exception in taking SCREENSHOT : " + e.getCause());
					Reporter.log(reportInfo.TcName + "\t" + Fail + "Exception in taking SCREENSHOT : " + e.getCause());
					e.printStackTrace();
				}
		}
		
			public void sendKey(int key){
			
				try {
				
					Runtime.getRuntime().exec("cmd /c start adb shell input keyevent " + key);
					Thread.sleep(500);
					Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

					  Logs.info(reportInfo.TcName + "\t" + " The KeyEvent : " + key + " is applied");
					  Reporter.log(reportInfo.TcName + "\t" + " The KeyEvent : " + key + " is applied");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
			
			public void sendKeys(int key){
				
				driver.sendKeyEvent(key);
				Logs.info(reportInfo.TcName + "\t" + " The KeyEvent : " + key + " is applied");
				Reporter.log(reportInfo.TcName + "\t" + " The KeyEvent : " + key + " is applied");
		}
			
		public void ClickElement(By Element, String strdesc) throws Exception
		{
			try
			{
				WebElement element = driver.findElement(Element);
				
				if(element.isDisplayed()){
					element.click();
					
					Logs.info(reportInfo.TcName + "\t" + Pass + "The field " + strdesc + " is clicked ");
					Reporter.log(reportInfo.TcName + "\t" + Pass + "The field " + strdesc + " is clicked " + ".\n");
				}
			}
			catch(Exception e)
			{
				Logs.fatal(reportInfo.TcName + "\t" + Fail + "exception occurred in clicking " + strdesc + "Error : " + e.getCause());
				Reporter.log(reportInfo.TcName + "\t" + Fail + "exception occurred in clicking " + strdesc + "Error : " + e.getCause());
				throw new Exception("Error : " + e.getCause());
			}
		}
		

		public void EnterText(By elementQuery, String strValue, String strdesc)
		{
			try
			{ 
				WebElement element = driver.findElement(elementQuery);
				if(!strValue.isEmpty()){
					strValue = strValue.trim();
					element.click();
					element.clear();
					element.sendKeys(strValue);
				}
				Reporter.log(reportInfo.TcName + "\t" + Pass + "The value " + strValue + " is entered for " + strdesc + ".\n");
				Logs.info(reportInfo.TcName + "\t" + Pass + "The value " + strValue + " is entered for " + strdesc);
			}
			catch(Exception e)
			{
				//TakeScreenshot("Setting value",strdesc);
				Reporter.log(reportInfo.TcName + "\t" + Pass + "The value " + strValue + " is not entered for " + strdesc + ".\n");
				Logs.fatal(reportInfo.TcName + "\t" + Fail + strValue+" is not entered in "+strdesc + "Error : " + e.getCause());
			}
		}
		
		public String getText(By Element, String strdesc)
		{
			String str="";
			try
			{
				str = driver.findElement(Element).getText();
				Logs.info(reportInfo.TcName + "\t" + Pass + "The value of " + strdesc + " is : " + str);
				Reporter.log(reportInfo.TcName + "\t" + Pass + "The value of " + strdesc + " is : " + str + ".\n");
			}
			catch(ElementNotFoundException e){
				Reporter.log(reportInfo.TcName + "\t" + Fail + "The field " + strdesc + " is not present " + ".\n");
				Logs.fatal(reportInfo.TcName + "\t" + Fail + "The field " + strdesc + " is not present " + "Error : " + e.getCause());
			}
			catch(Exception e)
			{
				Reporter.log(reportInfo.TcName + "\t" + Fail + "exception occurred in " + strdesc + "Error : " + e.getCause());
				Logs.fatal(reportInfo.TcName + "\t" + Fail + "exception occurred in " + strdesc + "Error : " + e.getCause());
				}
		return str;
		}
		
		public void aJaxWait(){
			
			try{
				
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ProgressBar")));
				
				wait_2.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
			}
			catch(Exception e){}
			finally{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
		}
		
		public void WaitFor(By by){
			
			try{
			wait_2.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
			catch(Exception e){}
		}
		
		public void switchFrame(int a){
			
			driver.switchTo().frame(a);
		}
		
		public void closeDriver(){
			
			try{
			driver.closeApp();
			}
			catch(Exception e){}
			
			Reporter.log(reportInfo.TcName + "\t" + "Ending..." + "\n");
			  Logs.info(reportInfo.TcName + "\t" + "Ending..." + "\n");
			  System.out.println("Ending..." + reportInfo.TcName);
		}
		
	}
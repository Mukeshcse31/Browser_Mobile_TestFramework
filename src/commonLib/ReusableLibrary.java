package commonLib;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class ReusableLibrary extends ReusLibrary{
	
	protected WebDriver driver;
	public static String screenshot="";
	private WebDriverWait wait, wait_2;
	
	private String Pass=" PASS \t";
	private String Fail=" FAIL \t";
	
	public ReusableLibrary(ReusableLibrary reusableLibrary){
		super(reusableLibrary);
		if(!(reusableLibrary == null)){
		this.driver=reusableLibrary.driver;
				
		wait = new WebDriverWait(driver,5);
		wait_2 = new WebDriverWait(driver,20);
		
		}
	}
	
	public void setDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		this.driver=driver;
		
	}
	
	public void invokeURL(String url){

		String ur=getData("Run",url);
	//driver.get(ur);
	driver.navigate().to(ur);
	driver.manage().window().maximize();
	Reporter.log(reportInfo.TcName + "\t" + Pass + "The URL invoked is " + ur);
	Logs.info(reportInfo.TcName + "\t" + Pass + "The URL invoked is " + ur);
	
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
  
	public void TakeScreenshot(){
		
		  reportInfo.addStep();
		
		  String screenshotPath=reportInfo.path.toAbsolutePath() + "\\" + reportInfo.TcName + "_" + reportInfo.TestMethod + "_" + reportInfo.StepNo + ".jpg";
		  
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath));
			// Now you can do whatever you need to do with it, for example copy somewhere
			  Logs.info(reportInfo.TcName + "\t" + "SCREENSHOT taken : " + screenshotPath);
			  Reporter.log(reportInfo.TcName + "\t" + "SCREENSHOT taken : " + screenshotPath);
		} catch (IOException e1) {
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "Exception in taking SCREENSHOT : " + e1.getCause());
			Reporter.log(reportInfo.TcName + "\t" + Fail + "Exception in taking SCREENSHOT : " + e1.getCause());
		}
	}
	
	private void TakeScreenshot(String... status){
		
		reportInfo.addStep();
		String screenshotPath=reportInfo.path.toAbsolutePath() + "\\" + reportInfo.TcName + "_" + status[1] + "_" + reportInfo.StepNo + ".jpg";
		
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
			try {
				FileUtils.copyFile(scrFile, new File(screenshotPath));
			} catch (IOException e1) {
				Logs.fatal(reportInfo.TcName + "\t" + Fail + "Exception in taking SCREENSHOT : " + e1.getCause());
			}
		Reporter.log(reportInfo.TcName + "\t" + "Error in " + status[0] + " for " + "<a href='"+ screenshotPath +"'>" + status[1] + "</a>");
}


	
	public boolean isElmPresent(By by,String desc){
		aJaxWait();
		try{
		if(driver.findElement(by).isDisplayed())
		{
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The Element " + desc + " is present " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The Element " + desc + " is present " + ".\n");
			return true;
		}
		else{
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The Element " + desc + " is not present " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The Element " + desc + " is not present " + ".\n");
		}
		}
		catch(Exception e){

			Reporter.log(reportInfo.TcName + "\t" + Pass + "The Element " + desc + " is not present " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The Element " + desc + " is not present " + ".\n");
		}
	return false;		
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
				element.sendKeys(Keys.TAB);
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
	

	public Set getWindows()
	{
		Set<String> windows=null;
		try
		{ 
			windows=driver.getWindowHandles();
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The current windows are taken " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The current windows are taken " + ".\n");
		}
		catch(Exception e)
		{	
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The current windows are not taken " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The current windows are not taken " + ".\n");
		}
		finally{
		
		}
		return windows;
	}
	
	public void SendKey(By elementQuery, Integer strValue, String strdesc)
	{
		try
		{ 
			
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);

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
	
	
	/*
	 * Checks if the element passed is displayed
	 */
	public boolean IsPresent(By Element, String desc){
		
		if (driver.findElement(Element).isDisplayed()){
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The field " + desc + " is present " + ".\n"); 
			Logs.info(reportInfo.TcName + "\t" + Pass + "The field " + desc + " is present ");
		}
		else{
			Reporter.log(reportInfo.TcName + "\t" + Fail +"The field " + desc + " is not present " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Fail +"The field " + desc + " is not present ");
		}
		return driver.findElement(Element).isDisplayed() ? true : false;
	}
	
	
	public void ClickElement(By Element, String strdesc) throws Exception
	{
		try
		{
			aJaxWait();
			WebElement element = driver.findElement(Element);
			if(element.isEnabled()){
				element.click();
				
				Logs.info(reportInfo.TcName + "\t" + Pass + "The field " + strdesc + " is clicked ");
				Reporter.log(reportInfo.TcName + "\t" + Pass + "The field " + strdesc + " is clicked " + ".\n");
			}
		}
		
		/*
		catch(ElementNotFoundException e){
			Reporter.log(reportInfo.TcName + "\t" + Fail + "The field " + strdesc + " is not present " + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "The field " + strdesc + " is not present " + "Error : " + e.getCause());
		} */
		catch(Exception e)
		{
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "exception occurred in clicking " + strdesc + "Error : " + e.getCause());
			Reporter.log(reportInfo.TcName + "\t" + Fail + "exception occurred in clicking " + strdesc + "Error : " + e.getCause());
			throw new Exception("Error : " + e.getCause());
		}
	}
	
	
	public String getProperty(By Element, String prop)
	{
		String str="";
		try
		{
			str = driver.findElement(Element).getAttribute(prop);
			Logs.info(reportInfo.TcName + "\t" + Pass + "The value of " + prop + " is : " + str);
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The value of " + prop + " is : " + str + ".\n");
		}
		catch(ElementNotFoundException e){
			Reporter.log(reportInfo.TcName + "\t" + Fail + "The field " + prop + " is not present " + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "The field " + prop + " is not present " + "Error : " + e.getCause());
		}
		catch(Exception e)
		{
			Reporter.log(reportInfo.TcName + "\t" + Fail + "exception occurred in " + prop + "Error : " + e.getCause());
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "exception occurred in " + prop + "Error : " + e.getCause());
			}
	return str;
	}
	
	public void goBack(){
		
		driver.navigate().back();
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
	

	public Boolean compareText(By Element, String sheet, String col)
	{
		String str="";
		String datavalue="";
		Boolean status=false;
		
			//str = driver.findElement(Element).getText();
			str=getText(Element,sheet + "-" + col);
			datavalue=getData(sheet,col);
			if (str.contains(datavalue)){
				status=true;
			Logs.info(reportInfo.TcName + "\t" + Pass + "The value " + datavalue + " is presemt in: " + str);
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The value " + datavalue + " is present in : " + str + ".\n");
			
			}
			else
			{
				Logs.info(reportInfo.TcName + "\t" + Fail + "The value " + datavalue + " is not presemt in: " + str);
				Reporter.log(reportInfo.TcName + "\t" + Fail + "The value " + datavalue + " is not present in : " + str + ".\n");
				
			}
		
		return status;
	}
	
	public Boolean compareText(By Element, String val)
	{
		String str="";
		
		Boolean status=false;
		
			str = driver.findElement(Element).getText();
			if (str.contains(val)){
				status=true;
			Logs.info(reportInfo.TcName + "\t" + Pass + "The value " + val + " is presemt in: " + str);
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The value " + val + " is present in : " + str + ".\n");
			
			}
			else
			{
				Logs.fatal(reportInfo.TcName + "\t" + Fail + "The value " + val + " is not presemt in: " + str);
				Reporter.log(reportInfo.TcName + "\t" + Fail +"The value " + val + " is not present in : " + str + ".\n");
				
			}
		
		return status;
	}
	
	public void ListMultiSelectValue(By objName,String strValue,String strdesc) {
		try {
			ImplicitWaitSwitchOFF();
			Select select = new Select(driver.findElement(objName));
			if (strValue != null) {
				select.deselectAll();
				select.selectByVisibleText(strValue);
				Logs.info(reportInfo.TcName + "\t" + "The value " + strValue + " is selected for " + strdesc);
				Reporter.log(reportInfo.TcName + "\t" + "The value " + strValue + " is selected for " + strdesc + ".\n");
			}
	        ImplicitWaitSwitchON();
		} catch (NoSuchElementException e) {
			Reporter.log(reportInfo.TcName + "\t" + "The field " + strdesc + " is not present " + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + "exception occurred in selecting  " + strdesc + "Error : " + e.getCause());
			
		}catch(NullPointerException e){
			Reporter.log(reportInfo.TcName + "\t" + "The value " + strValue + " is not present for " + strdesc + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + "exception occurred in selecting  " + strdesc + "Error : " + e.getCause());
		}catch(Exception e){
			Reporter.log(reportInfo.TcName + "\t" + "The value " + strValue + " is not present for " + strdesc + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + "exception occurred in selecting  " + strdesc + "Error : " + e.getCause());
		}

	}

	
	
	public void SelectValue(String objName,String strValue,String strdesc) {
		
		boolean exception=false;
		String cause="";

			try {
				
				//driver.findElement(objName).findElement(By.xpath("//li/div[contains(text(),'" + strValue + "'])")).click();
				
				driver.findElement(By.xpath("//*[@id='" + objName + "']/li/div[contains(text(),'" + strValue + "')]")).click();
								
				Reporter.log(reportInfo.TcName + "\t" + Pass + "The value " + strValue + " is selected for " + strdesc + ".\n");
				Logs.info(reportInfo.TcName + "\t" + Pass + "The value " + strValue + " is selected for " + strdesc + ".\n");
			}
			catch(NullPointerException e){
				exception=true;
							
			}
			catch(ElementNotFoundException e){
				exception=true;
				
			}
			catch (NoSuchElementException e) {
				exception=true;
				
			}
			catch (ElementNotVisibleException e) {
				exception=true;
				
			}
			catch(StaleElementReferenceException e){
				RefreshElement(By.id(objName));
			}
			catch(Exception e){
				exception=true;
				cause=e.getCause().toString();
			}
		
		
		if(exception){
			Reporter.log(reportInfo.TcName + "\t" + Fail + "An exception " + cause + " occurred while selecting for " + strdesc + "\n");
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "An exception " + cause + " occurred while selecting for " + strdesc + "\n");
			//TakeScreenshot(objName,strValue);
			
		}
	}
	
	public void SelectValue1(By strobj,String SelVal, String desc){
		WebElement select = driver.findElement(strobj);
	    List<WebElement> options = select.findElements(By.tagName("option"));
	   int i=1;
	    for (WebElement option : options) {
	        if(option.getText().equalsIgnoreCase(SelVal)){
	        	option.click();
	        	//driver.findElement(By.xpath("//select[@id ='" + strobj + "']//option[" + i + "]")).click();
	        	Reporter.log(reportInfo.TcName + "\t" + Pass + "The value " + SelVal + " is selected for " + desc + ".\n");
	        	break;
	        }
	    i=i+1;
	    }
	    }

	/*
	 * This function creates an element when it returns StaleElementReferenceException
	 * 
	 */
	private WebElement RefreshElement(By by){
		
		try{
			return driver.findElement(by);
		}
		catch(StaleElementReferenceException e){
			 return RefreshElement(by);
		}
	}

	public void JSClickElement(By by, String desc){
		
		try{
			WebElement element = driver.findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();",element);
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The element " + desc + " is clicked " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The element " + desc + " is clicked " + ".\n");
		
		}
		catch(Exception e){
			Reporter.log(reportInfo.TcName + "\t" + Fail + "The element " + desc + " is not clicked " + e.getCause() + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "The element " + desc + " is not clicked " + e.getCause() + ".\n");
		}
	}
	
	public void closeDriver(){
		
		try{
			ImplicitWaitSwitchOFF();
		driver.close();
		driver.quit();
		}
		catch(Exception e){}
		
		Reporter.log(reportInfo.TcName + "\t" + "Ending..." + "\n");
		  Logs.info(reportInfo.TcName + "\t" + "Ending..." + "\n");
		  System.out.println("Ending..." + reportInfo.TcName);
	}
	
	
	public void switchToFrame(By by,By by1){
		try{
		driver.switchTo().frame(driver.findElement(by));
		}
		catch(Exception e){
			driver.switchTo().frame(driver.findElement(by1));
		}
	}
	
public void swithDefault(){
		
		driver.switchTo().defaultContent();
	}
	


	public void ImplicitWaitSwitchON()
	{
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
	}
	public void ImplicitWaitSwitchOFF()
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		
	}
	
	public void aJaxWait(){
		
		try{
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ajaxIndicator")));
			
			wait_2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxIndicator")));
		}
		catch(Exception e){}
	}
	
	public void AcceptPop(){
		
		driver.switchTo().alert().accept();	
		driver.switchTo().defaultContent();
	}
	/* Project related reusable functions 
	 * 
	 */
	
	public void checkItem(String val) throws Exception{
		
		aJaxWait();
		String mycart=driver.findElement(By.xpath("//div[@id='yourCart']")).getText();
		if(mycart.contains(val)){
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The item " + val + " is present in " + "My Cart " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The item " + val + " is present in " + "My Cart " + ".\n");
	}
		else
		{
			Reporter.log(reportInfo.TcName + "\t" + Fail + "The item " + val + " is not present in " + "My Cart " + ".\n");
			Logs.fatal(reportInfo.TcName + "\t" + Fail + "The item " + val + " is not present in " + "My Cart " + ".\n");
		throw new Exception(" Failed in validation ");
		}
	}
	
	public void checAmount(String item, String val) throws Exception{
		
		try{
			aJaxWait();
		String mycart=driver.findElement(By.xpath("//div[@id='yourCart']//div[contains(.,'" + item + "')]/following-sibling::div")).getText();
		if(mycart.contains(val)){
			Reporter.log(reportInfo.TcName + "\t" + Pass + "The item " + val + " is present in " + "My Cart " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + "The item " + val + " is present in " + "My Cart " + ".\n");
		}
		else
		{
			Reporter.log(reportInfo.TcName + "\t" + Fail +"The item " + val + " is not present in " + "My Cart " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Fail + "The item " + val + " is not present in " + "My Cart " + ".\n");
		throw new Exception(" Failed in validation ");
		}
		}
		catch(Exception e){
			throw new Exception(reportInfo.TcName + "\t" + Fail +" The item " + item + " is not present ");
		}
	}
	
	public void switchToMode(String mode){
		
		try{
		driver.findElement(By.linkText("Switch to " + mode));
		Reporter.log(reportInfo.TcName + "\t" + Pass + " Switch To " + mode + " is clicked " + ".\n");
		Logs.info(reportInfo.TcName + "\t" + Pass + " Switch To " + mode + " is clicked " + ".\n");
		}
		catch(Exception e){
			Reporter.log(reportInfo.TcName + "\t" + Pass + " Already in " + mode + " " + ".\n");
			Logs.info(reportInfo.TcName + "\t" + Pass + " Already in " + mode + ".\n");
		}
	}
	
	
}
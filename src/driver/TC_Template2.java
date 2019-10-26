package driver;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import a_Pages.*;
import commonLib.A_ReusableLibrary;
import commonLib.A_Initialize;
import commonLib.ReportInfo;

public abstract class TC_Template2 {

	protected A_ReusableLibrary rL=new A_ReusableLibrary(null);
	protected ReportInfo reportInfo;
	protected String TCName;
	protected ITestResult result;
	public A_Home home;

  @BeforeTest()
  public void beforeTest() throws MalformedURLException {
	  
	  A_Initialize init=new A_Initialize().Init(TCName);
	  
	  // Data
  rL.setData(init.data);
  if(init.data==null)
	  throw new SkipException(TCName + " not selected ");
  
  // Driver	  
  rL.setDriver(init.driver);
  
 // Logs
  rL.setLog(Logger.getLogger("XXX_Smoke")); 
  }

  @BeforeTest(dependsOnMethods={"beforeTest"})
  public void beforeClass(){
	  
	  System.out.println("Starting... " + TCName);
	  
	  reportInfo=new ReportInfo();
	  reportInfo.setTCName(TCName);
	  reportInfo.setPath(M1.imageFolder);
	  rL.setReportInfo(reportInfo);
	  
  }
  
  @AfterMethod
  public void afterMethod(ITestResult result){
	  
	rL.afterMethod(result);
	  
  }
  
  @AfterClass(alwaysRun=true)
  public void afterTest() {
	  
	  rL=null;
	 home.closeBrowser();
	  
  }
  
}


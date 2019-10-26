package driver;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import rmw_Pages.*;
import commonLib.Initialize;
import commonLib.ReportInfo;
import commonLib.ReusableLibrary;

public abstract class TC_Template3 {

	protected ReusableLibrary rL=new ReusableLibrary(null);
	protected ReportInfo reportInfo;
	protected String TCName;
	protected ITestResult result;
	protected Home home;

  @BeforeTest()
  public void beforeTest() {
	  
	  Initialize init=new Initialize().Init(TCName);
	  
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


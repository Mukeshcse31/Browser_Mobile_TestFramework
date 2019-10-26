package script;

import org.testng.annotations.Test;

import driver.TC_Template1;
import pages.*;

public class TC_27 extends TC_Template1{

	public TC_27(){
		
		TCName="TC27";
	}
		// Pages
	Payment payment;
	
	
	@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	 home.signIn();
	  
  }
 
	
	@Test(priority=2)
	  public void browser2() throws Exception{
		home.browsers();
	}
	
	@Test(priority=3)
	  public void signIn2() throws Exception{

		 home.signIn();
		  
	  }
}

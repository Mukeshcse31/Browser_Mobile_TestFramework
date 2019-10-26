package script;

import org.testng.annotations.Test;

import driver.TC_Template1;
import pages.*;

public class TC_28 extends TC_Template1{

	public TC_28(){
		
		TCName="TC28";
	}
		// Pages
	Account account;
	
	@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	 home.signIn();
	  
  }
 
	 @Test(priority=2)
	  public void GoToAccount() throws Exception{
		  
		  account=home.myAccount();
		  account.changePass();
		  
	 }
	 
	@Test(priority=3)
	  public void browser2() throws Exception{
		home.browsers();
		home.login_new();
		
	}
}
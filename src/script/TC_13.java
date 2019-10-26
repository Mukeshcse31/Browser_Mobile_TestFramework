package script;

import org.testng.annotations.Test;
import pages.*;
import driver.TC_Template1;
public class TC_13 extends TC_Template1{
public TC_13(){
		
		TCName="TC13";
	}
	
	//Pages
	Account account;
	  
@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	  home.signIn();

  }
  

  @Test(priority=2)
  public void GoToAccount() throws Exception{
	  
	  account=home.myAccount();
	  
	  account.personal();
	  account.addResidence();
	  account.payment();
	  account.comm();
	  account.Rewards(); // Enroll
	  account.Rewards(); // Opt Out
	  account.pastOrder();
	  
	  
  }

}

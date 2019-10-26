package script_RMW;

import org.testng.annotations.Test;
import rmw_Pages.Account;
import rmw_Pages.Home;
import driver.TC_Template3;
public class RMW_TC13 extends TC_Template3{
public RMW_TC13(){
		
		TCName="RMW_TC13";
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

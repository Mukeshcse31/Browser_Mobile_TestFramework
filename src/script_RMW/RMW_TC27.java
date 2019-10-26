package script_RMW;

import org.testng.annotations.Test;
import rmw_Pages.*;
import driver.TC_Template3;

public class RMW_TC27 extends TC_Template3{
public RMW_TC27(){
		
		TCName="RMW_TC27";
	}
		// Pages
	Payment payment;
	@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	  home.signIn();

  }
  
  @Test(priority=2)
  public void back() throws Exception{
	  home.back();
  }
   
  @Test (priority = 3)
public void signIn_2() throws Exception{
	  home.signIn();
	  
	}
       
}

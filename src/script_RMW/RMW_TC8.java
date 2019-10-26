package script_RMW;

import org.testng.annotations.Test;
import driver.TC_Template3;
import rmw_Pages.*;

public class RMW_TC8 extends TC_Template3{

	public RMW_TC8(){
		
		TCName="RMW_TC8";
	}
		// Pages
	Payment payment;
	
	
	@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	  home.signIn();
  }
 
  @Test(priority=2)
  public void addPizza() throws Exception{
	  
	payment=home.addPizza();
  }
   
  @Test (priority = 3)
public void payment() throws Exception{
	  
	  payment.payment();
	  
	}
   
  @Test(priority=4)
  public void orderNo() throws Exception{
	  
	  payment.orderNo();
  }
}

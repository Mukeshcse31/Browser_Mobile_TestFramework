package script_RMW;

import org.testng.annotations.Test;
import rmw_Pages.*;
import driver.TC_Template3;

public class RMW_TC11 extends TC_Template3{

public RMW_TC11(){
		
		TCName="RMW_TC11";
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
	  
	home.Offers();
	payment=home.alsoLike();
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

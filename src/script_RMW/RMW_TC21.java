package script_RMW;

import org.testng.annotations.Test;
import rmw_Pages.*;
import driver.TC_Template3;

public class RMW_TC21 extends TC_Template3{
public RMW_TC21(){
		
		TCName="RMW_TC21";
	}
		// Pages
	Payment payment;
	@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	  home.signIn();
	  home.setLocation();

  }
  

  @Test(priority=2)
  public void addPizza() throws Exception{
	  
	payment=home.addPizza();
  }
  
   
  @Test (priority = 3)
public void payment() throws Exception{
	  
	  payment.payment();
	  payment.contact();
	  
	}
   
  @Test(priority=4)
  public void orderNo() throws Exception{
	  
	  payment.orderNo();
  }
    
}

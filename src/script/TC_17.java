package script;

import org.testng.annotations.Test;
import pages.*;
import driver.TC_Template1;
public class TC_17 extends TC_Template1{

public TC_17(){
		
		TCName="TC17";
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
	  
	  payment.DeliveryAdr();
	  payment.setAge();
	  payment.payment();
	  
	}
   
  @Test(priority=4)
  public void orderNo() throws Exception{
	  
	  payment.orderNo();
  }
    
}

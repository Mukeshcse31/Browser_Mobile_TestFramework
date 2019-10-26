package script;

import org.testng.annotations.Test;

import driver.TC_Template1;
import pages.*;

public class TC_1 extends TC_Template1{

	public TC_1(){
		
		TCName="TC1";
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
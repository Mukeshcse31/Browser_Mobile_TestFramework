package script_RMW;

import org.testng.annotations.Test;
import rmw_Pages.*;
import driver.TC_Template3;
public class RMW_TC14 extends TC_Template3{

public RMW_TC14(){
		
		TCName="RMW_TC14";
	}
	
	//Pages
	MyOrder myOrder;
	Payment payment;
	Account account;
	  
@Test(priority=1)
  public void signIn() throws Exception{

	  home =new Home(rL);
	  home.signIn();

  }
  

  @Test(priority=2)
  public void Edit_Save_Fave() throws Exception{
	  
	myOrder =new MyOrder(rL);
	account=home.myAccount();
	account.DeleteFave();
	account.modifySave();
  }
    
  @Test (priority = 3)
public void payment() throws Exception{
	  
	  payment =new Payment(rL);
	  payment.payment();
	  
	}
   
  @Test(priority=4)
  public void orderNo() throws Exception{
	  
	  payment.orderNo();
  }
      
}

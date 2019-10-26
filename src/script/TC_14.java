package script;

import org.testng.annotations.Test;
import pages.*;
import driver.TC_Template1;
public class TC_14 extends TC_Template1{

public TC_14(){
		
		TCName="TC14";
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
	home.pastOrder(); // Select the 1st past Order
	myOrder.modify();
	myOrder.SaveFave();
	
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

package a_Script;

import org.testng.annotations.Test;

import a_Pages.A_Home;
import a_Pages.A_Payment;
import driver.TC_Template2;

public class A_TC1 extends TC_Template2{

	public A_TC1(){
		
		TCName="A_TC1";
	}
		// Pages
	A_Payment Payment;
	
	@Test(priority=1)
	public void clearData() throws Exception{
		
		home =new A_Home(rL);
		home.clearData1();
		  home.open();
		  
		 
	}

	@Test(priority=2)
	public void Order() throws Exception{
		
		Payment=home.order();
	}
	
	@Test(priority=3)
	public void payment() throws Exception{
		
		Payment.payment();
	}
}
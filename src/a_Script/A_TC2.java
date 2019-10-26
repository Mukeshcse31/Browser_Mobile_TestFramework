package a_Script;

import org.testng.annotations.Test;
import a_Pages.A_Home;
import driver.TC_Template2;

public class A_TC2 extends TC_Template2{

	public A_TC2(){
		
		TCName="A_TC2";
	}
		// Pages
	
	
	@Test(priority=1)
  public void set() throws Exception{
		
		  home.open();
		  home.order();	
  }
 
	@Test(priority=2)
	  public void signIn() throws Exception{
		
	  //home.signIn();
	  System.out.println(" Mobile : ");
	 }
}

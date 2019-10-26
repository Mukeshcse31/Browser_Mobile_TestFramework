package pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import commonLib.ReusableLibrary;

public class Home extends ReusableLibrary{

		//*** TEXT ***//
		static By Txt_StAddr=By.id("streetAddress");
		static By Txt_zip=By.id("zip");
		static By Txt_UserName=By.id("userName");
		static By Txt_pwd=By.id("pwd");

		//Link
		
		static By Lnk_Basic=By.id("switchToBasic");
		static By Lnk_Sauce=By.id("yourSauce-Button");
		static By Lnk_Sides=By.xpath("//a[contains(@href,'sides')]");
		static By Lnk_Extras=By.xpath("//a[contains(@href,'extras')]");
		static By Lnk_SignIn=By.id("topNavBtnSignIn");
		static By Lnk_Menu=By.id("headerNavLink0");
		static By Lnk_Account=By.linkText("My Account");
		static By Lnk_PastOrder=By.id("pastOrderFlyoutBtn");
		static By Lnk_1PastOrder=By.xpath("(//div[contains(.,'Past Orders')]/ul/li/a[contains(.,'Add to Order')])[1]");
		static By Lnk_AddCust=By.xpath("(//a[contains(.,'Add & Customize All Large Pizzas')])[1]");
		static By Lnk_alsoLike=By.xpath("//a[@class='getItNowBtn']");
		static By Lnk_Guest=By.id("guestCheckoutLink");
		By Lnk_fullsite=By.partialLinkText("You are currently on the full site");
		By Lnk_SignOut=By.id("topNavBtnSignout");
		
		//Radio 
		static By Rdo_Business=By.id("addressType-2-img");
		static By Rdo_Carry=By.id("carryout-img");
		static By Rdo_Meat=By.id("customizationView-sku-1-img");
		
		
		//Button
		static By Btn_TheWork=By.xpath("//button[contains(.,'Add & Customize The Works')]");
		static By Btn_AddToOrder=By.id("orderBuilderContinueBtn");
		static By Btn_ContinueShopping=By.id("continueShoppingBtn");
		static By Btn_Breadsticks=By.xpath("//button[contains(.,'Breadsticks')]");
		static By Btn_Cinnapie=By.xpath("//button[contains(.,'Cinnapie')]");
		static By Btn_1stAdd=By.name("addBtn");
		static By Btn_SignIn=By.id("btnModalSignIn");
		static By Btn_ProceedToCheckOut=By.id("readyToCheckoutBtn");
		
		
		// Menu
		static By Mnu_MyOrder=By.id("topNavSubMenu");
		static By Mnu_Offers=By.id("headerNavLink1");
		
		//List
		
		static String Lst_Sauce = "yourSauce-List";

		// Button
		static By Btn_SubmitLoc=By.id("setLocationSubmit");
		static By Btn_1Carry=By.xpath("(//a/img[contains(@alt,'Carryout')])[1]");
		
		// Frames
		static By Frm_SignIn=By.xpath("//iframe[contains(@id,'signInFrame')]");
		static By Frm_SignIn1=By.xpath("//*[contains(@id,'signIn')]");

		public Home(ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}
		
		public void signIn() throws Exception{
			// Sign-In
			String URL=getData("Run","Region");
			
			if(URL.equalsIgnoreCase("QA"))
				URL="Qa_Url";
			else
				URL="Dev_Url";
			
			invokeURL(URL);
			String user=getData("Home","UserName");
			if(!(user==null || user=="")){
			
			ClickElement(Lnk_SignIn," Sign In ");
			switchToFrame(Frm_SignIn,Frm_SignIn1);
			aJaxWait();
			EnterText(Txt_UserName,user, " User Name ");
			EnterText(Txt_pwd,getData("Home","Pwd") , " Password ");
			ClickElement(Btn_SignIn, " Sign In button ");
			swithDefault();
		}
		
		}
		
		public Payment addPizza() throws Exception{
		
		  ClickElement(Lnk_Menu, " Menu ");
		  switchToMode(getData("Home","Mode"));
		  ClickElement(Btn_1stAdd, " Add Sausage Pizza ");
		  ClickElement(Btn_ContinueShopping, "Continue Shopping");
		  ClickElement(Btn_ProceedToCheckOut, " Proceed to CheckOut ");
		  if(getData("Payment","Guest").equalsIgnoreCase("Yes")){
			  aJaxWait();
			  switchToFrame(Frm_SignIn,Frm_SignIn1);
			  ClickElement(Lnk_Guest, "Continue as Guest ");
			  swithDefault();
		  }
		  return new Payment((ReusableLibrary) reusableLibrary);
		  
	  }
	  
		// Select the 1st past Order
		public void pastOrder() throws Exception{
		
			ClickElement(Lnk_Menu, " Manu ");
			ClickElement(Lnk_PastOrder, " Past Order ");
			ClickElement(Lnk_1PastOrder, " 1st Past Order ");
					
		}
		
		public Account myAccount() throws Exception{
			ClickElement(Lnk_Account, " My Account ");
			return new Account((ReusableLibrary) reusableLibrary);
		}
		
		public void Offers() throws Exception{
			ClickElement(Mnu_Offers, " Special Offers ");
			ClickElement(Lnk_AddCust, " Add & Customize 1st Pizza ");
			ClickElement(Rdo_Meat, " The Meat ");
			ClickElement(Btn_AddToOrder, " Add to Order ");
			ClickElement(Lnk_Basic, " Switch To Basic ");
			AcceptPop();
			ClickElement(Btn_AddToOrder, " Add to Order ");
			
			
		}
		
		public Payment alsoLike() throws Exception{
			ClickElement(Lnk_alsoLike, "1st Get it now");
			ClickElement(Btn_AddToOrder, " Add to Order ");
			ClickElement(Btn_ProceedToCheckOut, " Proceed to CheckOut ");
			return new Payment((ReusableLibrary) reusableLibrary);
			  
		}
		
		public void setLocation() throws Exception{
			
			String mode=getData("Home","Mode");
			EnterText(Txt_StAddr,getData("Home","StAddr"), " St. Addr ");
			EnterText(Txt_zip,getData("Home","Zip"), " Zip ");
			
			
			// Delete if not needed
			if(mode.equalsIgnoreCase("CarryOut"))
				ClickElement(Rdo_Carry, " Set CArryOut ");
			
			ClickElement(Btn_SubmitLoc, " Set Location ");
			
			if(mode.equalsIgnoreCase("CarryOut"))
				ClickElement(Btn_1Carry, " Carry Out #868 ");
			
		}
		
		public void browsers() throws Exception{
		
			// Sign-In
			String URL=getData("Run","Region");
			
			Set<String> set=driver.getWindowHandles();
			JavascriptExecutor executor=(JavascriptExecutor)driver;
			//executor.executeScript("javascript:window.open('https://orderqa.XXXjohns.com/clear.html', '_blank');");
			executor.executeScript("javascript:window.open('" + URL + "', '_newtab');");
			
			Set<String> set1=driver.getWindowHandles();
			
			set1.removeAll(set);
			driver.switchTo().window((String)set1.toArray()[0]);
			driver.manage().window().maximize();
			if(isElmPresent(Lnk_SignOut, "Already signed in"))
			ClickElement(Lnk_SignOut, " Sign Out button ");
		}
		
		public void login_new() throws Exception{
			
			ClickElement(Lnk_SignIn," Sign In ");
			switchToFrame(Frm_SignIn,Frm_SignIn1);
			aJaxWait();
			
			String user=getData("Home","UserName");
			EnterText(Txt_UserName,user, " User Name ");
			EnterText(Txt_pwd,getData("Home","Pwd") , " Password ");
			ClickElement(Btn_SignIn, " Sign In button ");
			
		}
		public void closeBrowser(){
			closeDriver();
		}
		
}

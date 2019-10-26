package rmw_Pages;

import org.openqa.selenium.By;
import rmw_Pages.Account;
import commonLib.ReusableLibrary;

public class Home extends ReusableLibrary{

		
		//*** TEXT ***//
		static By Txt_zip=By.id("zip");
		static By Txt_UserName=By.id("ip-username");
		static By Txt_pwd=By.id("ip-password");
		static By Txt_Promo = By.id("ip-promo-code");

		//Link
		static By Lnk_SignIn=By.linkText("Sign In/Create Account");
		static By Lnk_PayInfo=By.partialLinkText("Your Payment Information");
		By Lnk_CryOut1=By.partialLinkText("Carryout");
		static By Lnk_Basic=By.id("switchToBasic");
		static By Lnk_Sauce=By.id("yourSauce-Button");
		static By Lnk_Sides=By.xpath("//a[contains(@href,'sides')]");
		static By Lnk_Extras=By.xpath("//a[contains(@href,'extras')]");
		By Lnk_KeepOrdering=By.partialLinkText("Keep Ordering");
		static By Lnk_Menu=By.xpath("//li[3]/a[@class='menu-btn ico']");
		By Lnk_Manage=By.xpath("//li[7]/a[@class='menu-btn ico']");
		//By Lnk_SplPizza=By.xpath("//*[contains(text(),'Specialty Pizzas')]");
		
		By Lnk_SplPizza=By.partialLinkText("Specialty Pizzas");
		static By Lnk_Account=By.linkText("My Account");
		static By Lnk_PastOrder=By.id("pastOrderFlyoutBtn");
		static By Lnk_1PastOrder=By.xpath("(//div[contains(.,'Past Orders')]/ul/li/a[contains(.,'Add to Order')])[1]");
		static By Lnk_LrgPizza=By.partialLinkText("All Large Pizzas");
		static By Lnk_alsoLike=By.xpath("//a[@class='getItNowBtn']");
		static By Lnk_Guest=By.id("guestCheckoutLink");
		By Lnk_Find=By.partialLinkText("Find a Store & Offers");
		
		By Lnk_Delivery=By.partialLinkText("Delivery");
		By Lnk_CarryOut=By.partialLinkText("Carryout");
		
		
		// Label
		By Lbl_QPizza=By.xpath("//label[contains(.,'Quick Picks')]");
		By Lbl_Sausage=By.xpath("//label[contains(text(),'The Works')]");
		By Lbl_Dlry=By.xpath("//li[contains(text(),'Delivery')]");
		By Lbl_CryOut=By.xpath("//li[contains(text(),'Carryout')]");
		
		//Radio 
		static By Rdo_Business=By.id("addressType-2-img");
		static By Rdo_Carry=By.id("carryout-img");
		static By Rdo_Meat=By.xpath("//label[contains(text(),'Lg Thin All The Meats')]");
		
		
		//Button
		static By Btn_TheWork=By.xpath("//button[contains(.,'Add & Customize The Works')]");
		//static By Btn_AddToOrder=By.id("orderBuilderContinueBtn");
		static By Btn_AddCheckOut=By.xpath("//button[@value='addToCartAndCheckout']");
		static By Btn_Breadsticks=By.xpath("//button[contains(.,'Breadsticks')]");
		static By Btn_Cinnapie=By.xpath("//button[contains(.,'Cinnapie')]");
		static By Btn_1stAdd=By.xpath("//label[contains(text(),'Sm Orig The')]");
		static By Btn_SignIn=By.className("hot-btn");
		static By Btn_ProceedToCheckOut=By.id("readyToCheckoutBtn");
		By Btn_Apply=By.xpath("//button[contains(.,'Apply')]");
		By Btn_Add2Order=By.xpath("//button[contains(text(),'Add to Order')]");
		By Btn_Go=By.xpath("//button[contains(text(),'Go')]");
		
		
		// Menu
		static By Mnu_MyOrder=By.id("topNavSubMenu");
		static By Mnu_Offers=By.partialLinkText("Special Offers");
		
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
			
			//switchToFrame(Frm_SignIn,Frm_SignIn1);
			aJaxWait();
			EnterText(Txt_UserName,user, " User Name ");
			EnterText(Txt_pwd,getData("Home","Pwd") , " Password ");
			ClickElement(Btn_SignIn, " Sign In button ");
			aJaxWait();
			//swithDefault();
		}
		
		}
		
		public Payment addPizza() throws Exception{
			
			String promo=getData("Payment", "Promo");
			aJaxWait();
		  switchToMode_M(getData("Home","Mode"));
		  
		  //PrmoCode
		  if (!(promo==null || promo=="")){
			EnterText(Txt_Promo, promo, " Promo Code ");
			ClickElement(Btn_Apply, " Apply promocode ");
			ClickElement(Btn_Add2Order, " Add to Order ");
			ClickElement(Lnk_KeepOrdering, " Keep Ordering  ");
			
			
			}
		  else{
			  ClickElement(Lnk_Menu, " Pizzas ");
			  //ClickElement(Lbl_QPizza, " Quick Pizza ");
		  }
		  ClickElement(Lnk_SplPizza, " Specialty Pizzas ");
		  ClickElement(Lbl_Sausage, " Sausage Pizza ");
		  ClickElement(Btn_1stAdd, " Add Sausage Pizza ");
		  
		  ClickElement(Btn_AddCheckOut, " Add & Checkout ");
		  ClickElement(Lnk_PayInfo, " Your Payment Information ");
		
		  return new Payment((ReusableLibrary) reusableLibrary);
		  
	  }
	  
		private void switchToMode_M(String data) throws Exception {
			
			if(data.equalsIgnoreCase("Delivery")){
				String str=getProperty(Lbl_Dlry,"aria-checked");
				if(str.equalsIgnoreCase("false")){
				ClickElement(Lbl_Dlry, " Delivery mode ");
				}
			}
			else if(data.equalsIgnoreCase("CarryOut")){
				
				if(getProperty(Lbl_CryOut,"aria-checked").equalsIgnoreCase("false")){
					ClickElement(Lbl_CryOut, " CarryOut mode ");
					ClickElement(Lnk_CryOut1, " 1st CarryOut Store ");	
				}
				
			}
			aJaxWait();
		}

		public void Offers() throws Exception{
			ClickElement(Mnu_Offers, " Special Offers ");
			ClickElement(Lnk_LrgPizza, " All Large Pizzas ");
			ClickElement(Rdo_Meat, " The Meat ");
			ClickElement(Payment.Btn_Next, " Next ");
			ClickElement(Btn_Add2Order, " Add to Order ");
			
		}
		
		public Payment alsoLike() throws Exception{
			
			ClickElement(Account.Lnk_AddOrder, " Add to Order ");
			//ClickElement(Payment.Btn_Next, " Next ");
			ClickElement(Btn_AddCheckOut, " Add & Checkout ");
			ClickElement(Lnk_PayInfo, " Your Payment Information ");
			return new Payment((ReusableLibrary) reusableLibrary);
			  
		}
		
		public void setLocation() throws Exception{
			ClickElement(Lnk_Find, " Find a Store ");
			String mode=getData("Home","Mode");
			aJaxWait();
			EnterText(Account.Txt_StAddr,getData("Home","StAddr"), " St. Addr ");
			EnterText(Account.Txt_Zip,getData("Home","Zip"), " Zip ");
			ClickElement(Btn_Go, " Go ");
			
			// Delete if not needed
			if(mode.equalsIgnoreCase("CarryOut"))
				ClickElement(Lnk_CarryOut, " Set CArryOut ");
			else
				ClickElement(Lnk_Delivery, " Set Delivery ");
		}
		
		
		public void closeBrowser(){
			closeDriver();
		}

		public void back(){
			driver.navigate().back();
		}
		
		public Account myAccount() throws Exception {
			
			ClickElement(Lnk_Manage, " Manage Account ");
			return new Account((ReusableLibrary) reusableLibrary);
		}
		
}

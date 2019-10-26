package rmw_Pages;

import org.openqa.selenium.By;

import commonLib.Datatable;
import commonLib.ReusableLibrary;

public class Payment extends ReusableLibrary{


	//EditBox
	static By Txt_Promo=By.id("promoCode");
	static By Txt_CVV=By.id("ip-cc-verify");
	static By Txt_Gift=By.id("ip-pc-num");
	static By Txt_Pin=By.id("ip-pc-pin");
	static By Txt_CCNo=By.id("ip-cc-num");
	static By Txt_CCName=By.id("ip-cc-card-name");
	static By Txt_F_Name=By.id("ip-first");
	static By Txt_L_Name=By.id("ip-last");
	static By Txt_Phone1=By.id("ip-phone-num");
	static By Txt_Email=By.id("ip-email");
	static By Txt_CEmail=By.id("ip-confirm-email");
	
	
	// Button
	static By Btn_Apply=By.id("submitPromoCode");
	static By Btn_Continue=By.xpath("//cufon[@alt='Apply']");
	static By Btn_Redeem=By.xpath("//div[@id='promoModalContent']//cufon[@alt='Apply']");
	static By Btn_Order=By.xpath("//button[contains(text(),'Place Order Now')]");
	static By Btn_Duplicate=By.xpath("//button[contains(.,'Place Order Now')]");
	static By Btn_Next=By.xpath("//button[contains(text(),'Next')]");
	By Btn_Done=By.partialLinkText("Done");
	
	//Radio
	static By Rdo_Credit=By.id("ip-scc-0");
	static By Rdo_Gift=By.id("paymentGiftCard-img");
	static By Rdo_Cash=By.id("paymentCash-img");
	
	// Image
	static By Img_Credit=By.id("savedCreditCardId-Button");
	static By Img_Credit1=By.id("creditCardType-Button");
	static By Img_Month=By.id("ccExpDateMonth-Button");
	static By Img_Year=By.id("ccExpDateYear-Button");
	
	//Link
	By Lnk_Contact=By.partialLinkText("Your Contact Information");
	
	// List
	static By Lst_Credit=By.xpath("//select[@id='ip-cc-type']/option[contains(text(),'Visa')]");
	static By Lst_Credit1=By.id("ip-cc-type");
	static By Lst_Year=By.id("ip-cc-exp-year");
	static By Lst_Month=By.id("ip-cc-exp-month");
	By Lst_PayType=By.id("ip-payment-method");
	By Lst_PayCredit=By.xpath("//select[@id='ip-payment-method']/option[contains(text(),'Credit Card')]");
	By Lst_Cash=By.xpath("//select[@id='ip-payment-method']/option[contains(text(),'Cash')]");
	By Lst_Gift=By.xpath("//select[@id='ip-payment-method']/option[contains(text(),'Gift Card')]");
	
	//Label
	static By Lbl_Order=By.xpath("//section[@class='confirmation-info'][1]/h3");
	static By Lbl_Duplicate=By.xpath("//p[contains(.,'you have already placed an order')]");
	
	//CheckBox
	static By Chk_Age=By.id("ip-over13");
	
	
		public Payment(ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}
	
	public void payment() throws Exception{
		  
		//ClickElement(Lst_PayType, " Payment Type ");
		
	  
	  String Cash=getData("Payment", "Cash");
	  String Credit=getData("Payment", "Credit");
	  String Gift=getData("Payment", "Gift");
	  String Guest=getData("Payment", "Guest");
	
	  //Cash
	  if (!(Cash==null || Cash=="")){
		  //ClickElement(Lst_Cash, " Cash ");
		  SelectValue1(Lst_PayType, "Cash"," Cash ");
		  
	  }
	  
	  //Credit Card
	  if (!(Credit==null || Credit=="")){
		  aJaxWait();
		  //ClickElement(Lst_PayCredit, " Credit Card ");
		  SelectValue1(Lst_PayType, "Credit Card"," Credit Card ");
		  aJaxWait();
		  
		  if (Guest.equalsIgnoreCase("No")){
			  
			  ClickElement(Rdo_Credit, " Credit Card ending in 6650 ");
		  }
		  else
		  {
			  String CcNo=getData("Payment", "CcNo");
			  String CcName=getData("Payment", "CcName");
			  String cvv=getData("Payment", "CVV");
			  
			 // ClickElement(Lst_Credit1, " Credit Card ");
			  SelectValue1(Lst_Credit1, Credit," Visa ");
			  
			  EnterText(Txt_CCNo, CcNo, " CC No ");
			  EnterText(Txt_CVV, cvv, " CVV ");
			  
			  //ClickElement(Img_Month, " Credit Month drop down ");
			  SelectValue1(Lst_Month,getData("Payment", "CcMon"), " Month ");
			 // ClickElement(Img_Year, " Credit Year drop down ");
			  SelectValue1(Lst_Year, getData("Payment", "CcYear"), " Year ");
			  EnterText(Txt_CCName, CcName, " CC Name ");
			  
			  
		  }
		  
	  }
	  //Gift Card
	  if (!(Gift==null || Gift=="")){
		  //ClickElement(Lst_Gift, " Gift Card ");
		  SelectValue1(Lst_PayType, "Gift Card"," Gift Card ");
		  String pin=getData("Payment", "Pin");
		  EnterText(Txt_Gift, Gift, " Gift Card ");
		  EnterText(Txt_Pin, pin, " PIN ");
	  }
	  
	  ClickElement(Btn_Next, " Next ");
	  }

	public void contact() throws Exception{
		
		ClickElement(Lnk_Contact, " Your Contact Information ");
		EnterText(Txt_F_Name,getData("Payment","F_Name")," First Name ");
		EnterText(Txt_L_Name,getData("Payment","L_Name")," Last Name ");
		
		
		EnterText(Txt_Phone1,getData("Payment","Phone1")," Phone 1 ");
		
		EnterText(Txt_Email,getData("Payment","Email")," Email ");
		EnterText(Txt_CEmail,getData("Payment","Email")," Confirm Email ");
		
		ClickElement(Chk_Age, " Age 13 ");
		ClickElement(Btn_Next, " Next ");
	}
	
	  public void orderNo() throws Exception{
	  	  /*
	   * get the order No and write it to Data-> LAST sheet
	   */
	  ClickElement(Btn_Order, " Place Order Now ");
	  
	  if(isElmPresent(Lbl_Duplicate, " Duplicate Order "))
		  ClickElement(Btn_Order, " Place Order Now ");
	  aJaxWait();
	  String order=getText(Lbl_Order, " Order Number");
	  Datatable.writeOrder(reportInfo.TcName,getData("Run","Description"),order.split(":")[1]);
	  //ClickElement(Btn_Done, " Done ");
}
	  
}

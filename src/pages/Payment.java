package pages;

import org.openqa.selenium.By;

import commonLib.Datatable;
import commonLib.ReusableLibrary;

public class Payment extends ReusableLibrary{


	//EditBox
	static By Txt_Promo=By.id("promoCode");
	static By Txt_CVV=By.id("ccVerificationNumber");
	static By Txt_Gift=By.id("giftCardNumber");
	static By Txt_Pin=By.id("giftCardPin");
	static By Txt_CCNo=By.id("ccNumber");
	static By Txt_CCName=By.id("ccNameOnCard");
	static By Txt_F_Name=By.id("nameFirst");
	static By Txt_L_Name=By.id("nameLast");
	static By Txt_Phone1=By.id("phoneAreaCode");
	static By Txt_Phone2=By.id("phonePrefix");
	static By Txt_Phone3=By.id("phoneSuffix");
	static By Txt_Email=By.id("email");
	static By Txt_CEmail=By.xpath("(//*[@id='email'])[2]");
	
	
	// Button
	static By Btn_Apply=By.id("submitPromoCode");
	static By Btn_Continue=By.xpath("//cufon[@alt='Apply']");
	static By Btn_Redeem=By.xpath("//div[@id='promoModalContent']//cufon[@alt='Apply']");
	static By Btn_Order=By.id("placeOrderBtn");
	static By Btn_Duplicate=By.xpath("//button[contains(.,'Place Order Now')]");
	
	//Radio
	static By Rdo_Credit=By.id("paymentCreditCard-img");
	static By Rdo_Gift=By.id("paymentGiftCard-img");
	static By Rdo_Cash=By.id("paymentCash-img");
	static By Rdo_Check=By.id("paymentCheck-img");
	
	// Image
	static By Img_Credit=By.id("savedCreditCardId-Button");
	static By Img_Credit1=By.id("creditCardType-Button");
	static By Img_Month=By.id("ccExpDateMonth-Button");
	static By Img_Year=By.id("ccExpDateYear-Button");
	
	
	// List
	static String Lst_Credit="savedCreditCardId-List";
	static String Lst_Credit1="creditCardType-List";
	static String Lst_Year="ccExpDateYear-List";
	static String Lst_Month="ccExpDateMonth-List";
	
	//Label
	static By Lbl_Order=By.xpath("//*[@class='orderNumberInfo']/em");
	static By Lbl_Duplicate=By.xpath("//h2[contains(.,'Verify Duplicate Order')]");
	
	//CheckBox
	static By Chk_Age=By.id("minAgeConfirmation-img");
	
	
		public Payment(ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}
	
	public void payment() throws Exception{
		  
	  String promo=getData("Payment", "Promo");
	  String Cash=getData("Payment", "Cash");
	  String Credit=getData("Payment", "Credit");
	  String Gift=getData("Payment", "Gift");
	  String Check=getData("Payment", "Check");
	  String Guest=getData("Payment", "Guest");
	  
	  //PrmoCode
	  if (!(promo==null || promo=="")){
		EnterText(Txt_Promo, promo, " PrmoCode ");
		ClickElement(Btn_Apply, " Apply promocode ");
		ClickElement(Btn_Redeem, " Continue ");
	  }
	 
	  //Cash
	  if (!(Cash==null || Cash=="")){
		  ClickElement(Rdo_Cash, " Credit Card ");
	  }
	  
	  //Check
	  if(!(Check==null || Check==""))
		  ClickElement(Rdo_Check, " Check ");
	  
	  
	  //Credit Card
	  if (!(Credit==null || Credit=="")){
		  String cvv=getData("Payment", "CVV");
		  ClickElement(Rdo_Credit, " Credit Card ");
		  
		  if (Guest.equalsIgnoreCase("No")){
		  
		  
		  ClickElement(Img_Credit, " Credit card drop down ");
		  SelectValue(Lst_Credit, Credit, " Saved Credit Card ");
		  EnterText(Txt_CVV, cvv, " CVV ");
		  
	  }
		  else
		  {
			  String CcNo=getData("Payment", "CcNo");
			  String CcName=getData("Payment", "CcName");
			  
			  ClickElement(Img_Credit1, " Credit card drop down ");
			  SelectValue(Lst_Credit1, Credit, " Saved Credit Card ");
			  EnterText(Txt_CCNo, CcNo, " CC No ");
			  ClickElement(Img_Month, " Credit Month drop down ");
			  SelectValue(Lst_Month,getData("Payment", "CcMon"), " Month ");
			  ClickElement(Img_Year, " Credit Year drop down ");
			  SelectValue(Lst_Year, getData("Payment", "CcYear"), " Year ");
			  EnterText(Txt_CCName, CcName, " CC Name ");
			  EnterText(Txt_CVV, cvv, " CVV ");
			  
			  
		  }
		  
	  }
	  //Gift Card
	  if (!(Gift==null || Gift=="")){
		  ClickElement(Rdo_Gift, " Gift Card ");
		  String pin=getData("Payment", "Pin");
		  EnterText(Txt_Gift, Gift, " Gift Card ");
		  EnterText(Txt_Pin, pin, " PIN ");
	  }
	  }
	  
	public void setAge() throws Exception{
		ClickElement(Chk_Age, " Age 13 ");
	}
	
	public void DeliveryAdr(){
		
		EnterText(Txt_F_Name,getData("Payment","F_Name")," First Name ");
		EnterText(Txt_L_Name,getData("Payment","L_Name")," Last Name ");
		
		EnterText(Txt_Phone1,getData("Payment","Phone1")," Phone 1 ");
		EnterText(Txt_Phone2,getData("Payment","Phone2")," Phone 2 ");
		EnterText(Txt_Phone3,getData("Payment","Phone3")," Phone 3 ");
		
		EnterText(Txt_Email,getData("Payment","Email")," Email ");
		EnterText(Txt_CEmail,getData("Payment","Email")," Confirm Email ");
	}
	  public void orderNo() throws Exception{
	  	  /*
	   * get the order No and write it to Data-> LAST sheet
	   */
	  ClickElement(Btn_Duplicate, " Place Order ");
	  if(isElmPresent(Lbl_Duplicate, " Duplicate Order "))
		  ClickElement(Btn_Duplicate, " Place Order Now ");
	  String order=getText(Lbl_Order, " Order Number");
	  Datatable.writeOrder(reportInfo.TcName,getData("Run","Description"),order);
}
	  
	  
	  public void giftCard() throws Exception{
		  
		  
		  String gift,pin;
		  for(int i=1;i<10;i++){
			
			  gift=getData("Payment","Gift" + i);
			  pin=getData("Payment","Pin" + i);
			  
			  if (pin==null || pin=="")
				  break;
			  
			  ClickElement(Rdo_Gift, " Gift card ");
			  EnterText(Txt_Gift,gift," Gift card " + i);
			  EnterText(Txt_Pin,pin," Gift card Pin " + i);
			  ClickElement(Btn_Duplicate, " Place Order ");
			  
		  }
	  }
}

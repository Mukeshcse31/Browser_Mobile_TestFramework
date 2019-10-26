package pages;

//import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import commonLib.ReusableLibrary;

public class Account extends ReusableLibrary{

		//*** TEXT ***//
		static By Txt_First=By.id("nameFirst");
		static By Txt_StAddr=By.id("address1");
		static By Txt_City=By.id("city");
		static By Txt_Zip=By.id("zipcode");
		static By Txt_LocName=By.id("locationName");
		static By Txt_CurPass=By.id("currentPasswordLabel");
		static By Txt_NewPass=By.id("passwordLabel");
		static By Txt_ConfirmPass=By.id("confirmPasswordLabel");
		
		//Header
		static By Hdr_Personal=By.xpath("//h2[contains(.,'Personal Info')]");
		static By Hdr_Address=By.xpath("//h2[contains(.,'Address Information')]");
		static By Hdr_Payment=By.xpath("//h2[contains(.,'Payment Information')]");
		static By Hdr_Communication=By.xpath("//h2[contains(.,'Communication Preferences')]");
		static By Hdr_Past=By.xpath("//h2[contains(.,'Past Orders')]");
		static By Hdr_Rewards=By.xpath("//h2[contains(.,'XXX Rewards')]");
		static By Hdr_Fave=By.xpath("//h2[contains(.,'Your Faves')]");
		static By Hdr_Credit=By.xpath("//*[@id='editPaymentInfo']//h3");
		static By Hdr_AddrAuto=By.xpath("//*[@id='addAddressAsDefault']//li[contains(.,'Automation')]");
		static By Hdr_Pass=By.id("editSignInInfo");
		
		//Button
		static By Btn_Save=By.xpath("//button[contains(.,'Save Changes')]");
		static By Btn_Save2=By.xpath("(//button[contains(.,'Save Changes')])[2]");
		static By Btn_Save3=By.xpath("(//button[contains(.,'Save Changes')])[3]");
		static By Btn_Delete=By.xpath("//button[contains(.,'Delete')]");
		static By Btn_OptOut=By.id("XXXRewardsInfo");
		static By Btn_OK=By.xpath("//button[contains(.,'OK')]");
		static By Btn_SavePass=By.xpath("//button[contains(.,'Save Password')]");
		
		//CheckBoxes
		static By Chk_Text=By.id("TextOffers-img");
		
		//Link
		static By Lnk_LastDetails=By.xpath("//li[10]//a[contains(.,'Details')]");
		static By Lnk_newAddr=By.linkText("Add a new address");
		static By Lnk_State=By.id("geoAddress.territory.territoryId-Button");
		static By Lnk_Country=By.id("countryType-Button");
		static By Lnk_Delete=By.xpath("//*[@id='addAddressAsDefault']//li[contains(.,'Automation')]/ul//a[contains(.,'Delete')]");
		static By Lnk_FaveDelete=By.xpath("//li[contains(@class,'faveContent ') and contains(.,'Automation')]//a[contains(.,'Delete')]");
		
		//Label
		static By Lbl_LastDetails=By.xpath("//*[@id='pastOrders']//li[10]");
		static By Lbl_Reward=By.xpath("//*[@id='manageXXXRewards']//ul");
		
		//List
		static String Lst_State="geoAddress.territory.territoryId-List";
		static String Lst_Country="countryType-List";
		
		
		//Radio
		static By Rdo_AddrTypr=By.id("manageAccountAddressType-1-img");
		
		public Account(ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}
		
		public void save() throws Exception{
			ClickElement(Btn_Save, " Save Changes ");
		}
	  
		public void personal() throws Exception{
			
			ClickElement(Hdr_Personal, " Personal Info ");
			EnterText(Txt_First, ReusableLibrary.screenshot, " First Name ");
			save();
		}
		
		public void payment() throws Exception{
			
			ClickElement(Hdr_Payment, " Payment Info ");
			compareText(Hdr_Credit,"Payment","Credit");
		}
		
		public void comm() throws Exception{
			ClickElement(Hdr_Communication, " Communication Preferences ");
			ClickElement(Chk_Text, " Text Offers ");
			ClickElement(Btn_Save2, " Save Changes ");
		}
		
		public void pastOrder() throws Exception{
			
			ClickElement(Hdr_Past, " Past Orders ");
			ClickElement(Lnk_LastDetails, " Last Details ");
			compareText(Lbl_LastDetails,"MyOrder","Pizza");
		}
		
		public void addResidence() throws Exception{
			
			ClickElement(Hdr_Address, " Address Information ");
			//Delete if the address exists
			if(isElmPresent(Hdr_AddrAuto, " Automation Addresss ")){
				ClickElement(Lnk_Delete, " Delete existing Address ");
				ClickElement(Btn_Delete, " Delete Confirmation ");
			}
			
			ClickElement(Lnk_newAddr, " Add a new Addresss ");
			ClickElement(Rdo_AddrTypr, " Residence Addresss ");
			ClickElement(Lnk_Country, " Country dropdown ");
			
			SelectValue(Lst_Country,getData("Account", "Country")," Country ");
			EnterText(Txt_StAddr,getData("Account", "StAddr"), " St Address ");
			EnterText(Txt_City,getData("Account", "City"), " City ");
			ClickElement(Lnk_State, " State Dropdown ");
			SelectValue(Lst_State,getData("Account", "State")," State ");
			EnterText(Txt_Zip,getData("Account", "Zip"), " Zip ");
			EnterText(Txt_LocName,getData("Account", "LocName"), " Name of the Location ");
			ClickElement(Btn_Save3, " Save ");
		}
		
		public void Rewards() throws Exception{
			
			String str1="Enroll";
			if (!compareText(Lbl_Reward,"Account","Reward"))
				str1="Opt Out";
			ClickElement(Hdr_Rewards, " XXX Rewards ");
			ClickElement(Btn_OptOut, str1 + " Rewards ");
			ClickElement(Btn_OK, str1 + " Confirmation ");
			
		}
		
		public void DeleteFave() throws Exception{
			
			ClickElement(Hdr_Fave, " Your FAves ");
			aJaxWait();
			if(isElmPresent(Lnk_FaveDelete, " Automation Fave ")){
				ClickElement(Lnk_FaveDelete, " Automation Fave ");
				ClickElement(Btn_Delete, " Delete Confirmation ");
			}
			
		}
		
		public void changePass() throws Exception{
			
			ClickElement(Hdr_Pass, " Email and Password Information ");
			
			EnterText(Txt_CurPass,getData("Home", "Pwd"), " Current Password ");
			EnterText(Txt_NewPass,getData("Home", "new_Pwd"), " New Password ");
			EnterText(Txt_ConfirmPass,getData("Home", "new_Pwd"), " Confirm Password ");
			ClickElement(Btn_SavePass, " Save Password ");		
		}
}

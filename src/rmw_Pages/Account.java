package rmw_Pages;

//import java.util.LinkedHashMap;

import org.openqa.selenium.By;

import commonLib.ReusableLibrary;

public class Account extends ReusableLibrary{

		//*** TEXT ***//
		static By Txt_First=By.id("ip-first-name");
		static By Txt_StAddr=By.id("ip-street-address");
		static By Txt_City=By.id("ip-city");
		static By Txt_Zip=By.id("ip-zip");
		static By Txt_LocName=By.id("ip-address-name");
		By Txt_fave=By.id("ip-save-as-fave");
		
		//Header
		static By Lnk_Personal=By.partialLinkText("Personal Info");
		static By Lnk_Address=By.partialLinkText("Address Info");
		static By Lnk_Payment=By.partialLinkText("Payment Info");
		static By Lnk_Communication=By.partialLinkText("Communication Info");
		static By Lnk_Past=By.partialLinkText("Past Orders");
		static By Lnk_Rewards=By.partialLinkText("XXX Rewards");
		static By Lnk_Fave=By.partialLinkText("Your Faves");
		static By Hdr_Credit=By.xpath("//*[@id='saved-cards']//h3");
		static By Hdr_AddrAuto=By.xpath("//*[@id='saved-addresses']//div[contains(.,'Automation')]");
		
		//Button
		static By Btn_Save=By.xpath("//button[contains(.,'Save Changes')]");
		static By Btn_Save2=By.xpath("//button[contains(.,'Save')]");
		static By Btn_Save3=By.xpath("(//button[contains(.,'Save Changes')])[3]");
		static By Btn_Delete=By.partialLinkText("Delete this Fave");
		static By Btn_OptOut=By.id("XXXRewardsInfo");
		static By Btn_OK=By.xpath("//button[contains(.,'OK')]");
		static By Btn_Plus=By.xpath("//button[contains(text(),'Increment')]");
		static By Btn_CheckOut=By.xpath("//button[contains(text(),'Checkout')]");
		
		//CheckBoxes
		static By Chk_Text=By.id("ip-txt-offers");
		
		//Link
		static By Lnk_LastDetails=By.xpath("//*[@class='past-orders']/li[10]");
		static By Lnk_newAddr=By.linkText("Add a New Address");
		static By Lnk_State=By.id("geoAddress.territory.territoryId");
		static By Lnk_Country=By.id("ip-country-type");
		static By Lnk_Delete=By.xpath("//*[@id='saved-addresses']//fieldset[contains(.,'Automation')]/a[contains(.,'Delete')]");
		By Lnk_FaveAuto=By.partialLinkText("Automation");
		static By Lnk_FaveDelete=By.xpath("//li[contains(@class,'faveContent ') and contains(.,'Automation')]//a[contains(.,'Delete')]");
		By Lnk_Back=By.partialLinkText("Back");
		static By Lnk_AddOrder=By.partialLinkText("Add to Order");
		By Lnk_Edit=By.partialLinkText("Edit");
		
		
		//Label
		static By Lbl_LastDetails=By.xpath("//*[@id='page-container']//h3");
		static By Lbl_Reward=By.className("hot-btn");
		
		//List
		static By Lst_AddrTypr=By.id("ip-address-type");
		static By Lst_Residence=By.xpath("//*[@id='ip-address-type']/option[contains(text(),'Residence')]");
		static By Lst_Country=By.xpath("//*[@id='ip-country-type']/option[contains(text(),'USA')]");
		static By Lst_State=By.xpath("//*[@id='geoAddress.territory.territoryId']/option[contains(text(),'KY')]");
		
		
		public Account(ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}
		
		public void save() throws Exception{
			ClickElement(Btn_Save, " Save Changes ");
		}
	  
		public void personal() throws Exception{
			
			ClickElement(Lnk_Personal, " Personal Info ");
			EnterText(Txt_First, ReusableLibrary.screenshot, " First Name ");
			save();
			//ClickElement(Lnk_Back, " Back ");
		}
		
		public void payment() throws Exception{
			
			ClickElement(Lnk_Payment, " Payment Info ");
			compareText(Hdr_Credit,"Payment","Credit");
			ClickElement(Lnk_Back, " Back ");
		}
		
		public void comm() throws Exception{
			ClickElement(Lnk_Communication, " Communication Preferences ");
			ClickElement(Chk_Text, " Text Offers ");
			save();
		}
		
		public void pastOrder() throws Exception{
			
			ClickElement(Lnk_Past, " Past Orders ");
			ClickElement(Lnk_LastDetails, " Last Details ");
			compareText(Lbl_LastDetails,"MyOrder","Pizza");
		}
		
		public void addResidence() throws Exception{
			
			ClickElement(Lnk_Address, " Address Information ");
			//Delete if the address exists
			if(isElmPresent(Hdr_AddrAuto, " Automation Addresss ")){
				ClickElement(Lnk_Delete, " Delete existing Address ");
				//ClickElement(Btn_Delete, " Delete Confirmation ");
				AcceptPop();
			}
			
			ClickElement(Lnk_newAddr, " Add a new Addresss ");
			ClickElement(Lst_AddrTypr, " Addresss Type ");
			ClickElement(Lst_Residence, " Residence Type ");
			
			ClickElement(Lnk_Country, " Country dropdown ");
			ClickElement(Lst_Country, " Country ");
			
			EnterText(Txt_StAddr,getData("Account", "StAddr"), " St Address ");
			EnterText(Txt_City,getData("Account", "City"), " City ");
			
			ClickElement(Lnk_State, " State Dropdown ");
			ClickElement(Lst_State, " State ");
			aJaxWait();
			ClickElement(Lst_State, " State ");
			
			EnterText(Txt_Zip,getData("Account", "Zip"), " Zip ");
			EnterText(Txt_LocName,getData("Account", "LocName"), " Name of the Location ");
			save();
			ClickElement(Lnk_Back, " Back ");
		}
		
		public void Rewards() throws Exception{
			
			ClickElement(Lnk_Rewards, " XXX Rewards ");
			String str1="Enroll";
			if (!compareText(Lbl_Reward,"Account","Reward"))
				str1="Opt Out";
			
			ClickElement(Lbl_Reward, str1 + " Rewards ");
			//ClickElement(Btn_OK, str1 + " Confirmation ");
			
		}
		
		public void DeleteFave() throws Exception{
			
			ClickElement(Lnk_Fave, " Your FAves ");
			aJaxWait();
			if(isElmPresent(Lnk_FaveAuto, " Automation Fave ")){
				ClickElement(Lnk_FaveAuto, " Automation Fave ");
				ClickElement(Btn_Delete, " Delete Confirmation ");
				AcceptPop();
				goBack();
			}
			goBack();
		}
		
		public void modifySave() throws Exception{
			
			ClickElement(Lnk_Past, " Past Orders ");
			ClickElement(Lnk_LastDetails, " Last Details ");
			ClickElement(Lnk_AddOrder, " Add To Cart ");
			ClickElement(Lnk_Edit, " Modify ");
			ClickElement(Btn_Plus, " Increment ");
			
			ClickElement(Btn_Save2, " Save ");
			EnterText(Txt_fave,ReusableLibrary.screenshot, "Fave Name");
			ClickElement(Btn_CheckOut, " CheckOut ");
			ClickElement(Home.Lnk_PayInfo, " Your Payment Information ");
			
		}

}

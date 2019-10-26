package pages;

import org.openqa.selenium.By;

import commonLib.ReusableLibrary;

public class MyOrder extends ReusableLibrary{

	public MyOrder(ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}
	
	// LINK
	static By Lnk_MyOrder=By.xpath("//a[contains(.,'My Order')]");
	static By Lnk_Modify=By.linkText("Modify");
	static By Lnk_Basic=By.id("switchToBasic");
	static By Lnk_SaveFave=By.xpath("//a[contains(.,'Save this order as a Fave')]");
	static By Lnk_CheckOut=By.xpath("//a[contains(.,'Checkout')]");
	
	//Check Box
	static By Chk_GrnPeppers=By.id("topping_47-img");
	static By Chk_Onions=By.id("topping_25-img");
	
	//Button
	static By Btn_Save=By.id("orderBuilderContinueBtn");
	static By Btn_Continue=By.id("continueShoppingBtn");
	static By Btn_SaveFave=By.id("saveAsFaveSubmit");
	
	
	//TextBox
	static By Txt_FaveName=By.name("saveAsFaveName");
	
	public void modify() throws Exception{
		
		ClickElement(Lnk_MyOrder, " My Order Cart ");
		ClickElement(Lnk_Modify, " Modify ");
		ClickElement(Lnk_Basic, " Switch To Basic ");
		AcceptPop();
		ClickElement(Chk_GrnPeppers, " Green Peppers ");
		ClickElement(Chk_Onions, " Onions ");
		ClickElement(Btn_Save, " Save ");
		ClickElement(Btn_Continue, " Continue ");
		
	}

	public void SaveFave() throws Exception{
		ClickElement(Lnk_MyOrder, " My Order Cart ");
		ClickElement(Lnk_SaveFave, " Save as Fave ");
		EnterText(Txt_FaveName, getData("MyOrder","Fave"), " Fave Name ");
		ClickElement(Btn_SaveFave, " Save ");
		ClickElement(Lnk_CheckOut, " CheckOut ");
		
	}
	

	
}

package a_Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import commonLib.A_ReusableLibrary;
import commonLib.Datatable;

public class A_Home extends A_ReusableLibrary{

		//*** TEXT ***//
	static By Tx=By.className("android.widget.EditText");
	
		//Options
		static By Opt_More=By.name("More");
		static By Opt_Clear=By.name("Clear Data");
		static By Opt_Ok=By.name("Yes");
		
		//Button
		static By Btn_SignIn=By.name("Sign In / Create Account");
		static By Btn_1=By.className("android.widget.Button");
		static By Btn_3=By.xpath("(//*[@class='android.widget.Button'])[3]");
		static By Btn_Pizza=By.name("Pizzas");
		static By Btn_popular=By.name("Most Popular");
		static By Btn_Sausage=By.name("Sausage Pizza");
		static By Btn_SmSausage=By.name("Sm Orig Sausage");
		static By Btn_Add2Order=By.name("Add to Order");
		static By Btn_Checkout=By.name("Checkout");
		
		//EditBox
		static By Txt_1=By.className("android.widget.EditText");
		static By Txt_2=By.xpath("(//*[@class='android.widget.EditText'])[2]");
		
		// Payment\
		static By Btn_Cash=By.name("Cash");
		static By Btn_Save=By.name("Save");
		static By Btn_Place=By.name("Place Your Order");
		static By Lbl_Order=By.xpath("(//*[@class='android.widget.TextView'])[6]");
		
		public A_Home(A_ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}

		
		 public void clearData1() throws Exception{
		 
			 Thread.sleep(9000);
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 WaitFor(Btn_SignIn);
		 sendKeys(82);
		 ClickElement(Opt_More," More ");
		 
			ClickElement(Opt_Clear," Clear ");
			ClickElement(Opt_Ok," OK ");
			aJaxWait();
			
		}

		public void open() throws Exception{
		
			String user=getData("Home","UserName");
			String pwd=getData("Home","Pwd");
			
			WaitFor(Btn_SignIn);
			ClickElement(Btn_SignIn," Sign_In ");
			EnterText(Txt_1,user, " User Name ");
			EnterText(Txt_2,pwd, " Password ");
			ClickElement(Btn_1," Sign In ");
			
		}
		
		public A_Payment order() throws Exception{
		
			ClickElement(Btn_Pizza," Pizza ");
			ClickElement(Btn_popular," Most Popular ");
			ClickElement(Btn_Sausage," Sausage ");
			ClickElement(Btn_SmSausage," Small Sausage ");
			ClickElement(Btn_Add2Order," Add to Order ");
			ClickElement(Btn_1," Cart ");
			ClickElement(Btn_Checkout," CheckOut ");
			return new A_Payment((A_ReusableLibrary) reusableLibrary);
		}
		
		public void closeBrowser(){
			closeDriver();
		}
}

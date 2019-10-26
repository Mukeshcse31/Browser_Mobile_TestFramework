package a_Pages;

import org.openqa.selenium.By;
import commonLib.A_ReusableLibrary;
import commonLib.Datatable;

public class A_Payment extends A_ReusableLibrary{
		
		//Button
		static By Btn_3=By.xpath("(//*[@class='android.widget.Button'])[3]");

		// Payment\
		static By Btn_Cash=By.name("Cash");
		static By Btn_Save=By.name("Save");
		static By Btn_Place=By.name("Place Your Order");
		static By Lbl_Order=By.xpath("(//*[@class='android.widget.TextView'])[6]");
		static By Btn_Done=By.name("Done");
		
		public A_Payment(A_ReusableLibrary reusableLibrary) {
		super(reusableLibrary);
		// TODO Auto-generated constructor stub
	}

public void payment() throws Exception{
			
			ClickElement(Btn_3," Payment Information ");
			ClickElement(Btn_Cash," Cash ");
			ClickElement(Btn_Save," Save ");
			ClickElement(Btn_Place," Place Your Order ");
			
			String order=getText(Lbl_Order, " Order Number");
			Datatable.writeOrder(reportInfo.TcName,getData("Run","Description"),order);
		System.out.println(order);
		
		//ClickElement(Btn_Done," Done ");
		
		}
}

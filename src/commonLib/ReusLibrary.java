package commonLib;

import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public abstract class ReusLibrary {
	
	protected LinkedHashMap<String,LinkedHashMap<String,String>> data;
	protected ReusLibrary reusableLibrary;
	protected ReportInfo reportInfo;
	protected Logger Logs;
	public static String screenshot="";
	private String Pass=" PASS \t";
	private String Fail=" FAIL \t";
	
	public ReusLibrary(ReusLibrary reusableLibrary){
		if(!(reusableLibrary == null)){
			
		this.data=reusableLibrary.data;
		this.reportInfo=reusableLibrary.reportInfo;
		this.Logs=reusableLibrary.Logs;
		this.reusableLibrary=reusableLibrary;
		
		}
	}
	
	public void setData(LinkedHashMap<String,LinkedHashMap<String,String>> data) {
		// TODO Auto-generated method stub
		this.data=data;		
	}
	
	public void setLog(Logger log){
		this.Logs=log;
	}
	
	public void setReportInfo(ReportInfo info){
		this.reportInfo=info;
	}
	
	public String getData(String sheet,String col){
		  
		  LinkedHashMap<String,String> tempdata;
			tempdata=data.get(sheet);
			return tempdata.get(col);
		  
	  }
	

}
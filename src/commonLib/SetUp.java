package commonLib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Properties;
import org.testng.Reporter;

import commonLib.Datatable;

public abstract class SetUp {
	
	
	public LinkedHashMap<String,LinkedHashMap<String,String>> data;
	
	
	public void getData(String tc){
		
		LinkedHashMap<String,String> tempdata;
		data=Datatable.inputdata(tc);
		tempdata=data.get("Run");
		if(tempdata.get("Execute").equalsIgnoreCase("No"))
			data=null;
		
	}
	/*
	 * Creates a result folder with name as timestamp and a screenshot folder inside it
	 */
	public static Path CreateResultFolder(){
		
		DateFormat dateFormat = new SimpleDateFormat("ddMMMYYYY_hh_mm_ss_a");
		Calendar calendar = Calendar.getInstance();
		String temp=dateFormat.format(calendar.getTime());
		
		// Set the system property
		System.setProperty("myrun.log4j", temp);
		
		setProperty("build.properties","thisRun",temp);
		
		//Set the path of screenshot folder
		ReusableLibrary.screenshot=temp;
		
		
		//setProperty("log4j.properties","log4j.appender.dest1.File","Result\\" + temp + "\\application.log");
		
		String pp="Result/" + temp;
		pp=pp+  "/Screenshots";
		Path path=Paths.get(pp);
		
		try {
			Files.createDirectories(path);
			
			Reporter.log("Run Directory CREATED :: " + temp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(NullPointerException e){}
		return path;
		
	}
	
	private static void setProperty(String fileName, String Prop, String Value){
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream(fileName);
	 
			// set the properties value
			prop.setProperty(Prop, Value);

			// save properties to project root folder
			prop.store(output, null);
			
			Reporter.log(" thisRun property updated ");
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
}

package driver;

import java.nio.file.Path;
import org.testng.annotations.BeforeSuite;
import commonLib.Initialize;

//@Listeners({TestListenerAdapter.class})

public class M1{

	public static Path imageFolder;
	protected static org.apache.log4j.Logger Logs;
	
	
	@BeforeSuite
	public void beforeSuite(){
		
		try{
		imageFolder=Initialize.CreateResultFolder();
		//BasicConfigurator.configure();
		}
		catch(Exception e){}
	}
	
	
}

package driver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;

import commonLib.ReusableLibrary;

public class XsltReport {

	@AfterSuite
	public void generateReport() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("cmd /c start report.bat");
		Thread.sleep(1000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

	
	@AfterSuite
	private static void copyfolder() {

		File srcDir1= new File(System.getProperty("user.dir") + "\\test-output");
		File destDir  = new File(System.getProperty("user.dir") + "\\Result\\" + ReusableLibrary.screenshot);
		
		try {
			FileUtils.copyDirectoryToDirectory(srcDir1, destDir);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	
}

package commonLib;

import java.nio.file.Path;

public class ReportInfo {

	/*
	 * This class contains the information about a test - Name, MethodName, Step No and Screenshot path
	 */
	public String TcName;
	//private String PageName;
	public String TestMethod;
	public int StepNo;
	public Path path;

	public void setPath(Path path){
		this.path=path;
	}
	public void setTCName(String tc){
		this.TcName=tc;
	}
	
	public void setTestMethod(String method){
		this.TestMethod=method;
	}
	
	public void addStep(){
		StepNo+=1;
	}
}

package commonLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

public class Datatable {

	public synchronized static LinkedHashMap<String,LinkedHashMap<String,String>> inputdata(String tc){
			
		String path=new File(System.getProperty("user.dir")).getAbsolutePath() + "\\Data.xls";
		String testCase=tc;
		FileInputStream Run=null;
		HSSFWorkbook book=null;
		
		try {
			Run=new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedHashMap<String,LinkedHashMap<String,String>> data=new LinkedHashMap<>();
		LinkedHashMap<String, String> rowData=new LinkedHashMap<>();
		try {
			book=new HSSFWorkbook(Run);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("sctive sheet " + book.getActiveSheetIndex());
		//System.out.println("the last sheet " + );
		
		
		int last=book.getSheetIndex("LAST");
		
		for(int j=0;j<last;j++){
		HSSFSheet sheet=book.getSheetAt(j);
		String name=sheet.getSheetName();
		
		for(int i=0;i<=sheet.getLastRowNum();i++){
			
			try{
		HSSFRow row=sheet.getRow(i);
		HSSFCell cell=row.getCell(0);
		
		if(cell.getStringCellValue().equalsIgnoreCase(testCase)){
			//System.out.println("SHEET  ----> "+ name );
			rowData=collectRow(sheet.getRow(0),row);
		break;
		}}
		catch(Exception e){
			//System.out.println(e.getCause());
		}
		//System.out.println(cell.getStringCellValue());
		
		}
		data.put(name,rowData);
		rowData=null;
		}
		
		book=null;
		
		return data;
		
	}
	
	public static LinkedHashMap<String, String> collectRow(HSSFRow rowHeading,HSSFRow row){
		LinkedHashMap<String, String> temp=new LinkedHashMap<String, String>();
		String key="";
		String cellValue = null;
		int length=row.getLastCellNum();
		for (int i = 0; i <length ; i++) {
			try{
			key=rowHeading.getCell(i).getStringCellValue();
			
			HSSFCell cell=row.getCell(i);
			
			switch (cell.getCellType())
			{
			case Cell.CELL_TYPE_BLANK: 
				cellValue  = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:						
				//cellValue = Double.toString(cell.getNumericCellValue());		
				DataFormatter formatter = new DataFormatter();
			     cellValue = formatter.formatCellValue(cell);
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = Boolean.toString(cell.getBooleanCellValue());
				break;
			}
			
			//value = row.getCell(i).getStringCellValue();
			
			temp.put(key, cellValue);
			//System.out.println("the key is "+ key + " the value is " + cellValue);
			
		}
		
		catch(NullPointerException e){}
		catch(Exception e){
			
			System.out.println("exception : " + e.toString() + " cause is : " + e.getCause());
		}
		}
		
		/*for(Entry<String,String> entry:temp.entrySet()){
			System.out.println("the key is "+ entry.getKey() + " the value is " + entry.getValue());
	}*/
		return temp;
		
	}
	
	public synchronized static void writeOrder(String tc,String desc, String order){
	try {
	    FileInputStream file = new FileInputStream(new File("Data.xls"));
	 
	    HSSFWorkbook workbook = new HSSFWorkbook(file);
	    HSSFSheet sheet = workbook.getSheet("LAST");
	    HSSFCell cell = null;
	    
	    int row=sheet.getLastRowNum() +1;
	    //Update the value of cell
	    
	    HSSFRow dataRow = sheet.createRow(row);
	    dataRow.createCell(0).setCellValue(tc);
	    dataRow.createCell(1).setCellValue(desc);
	    dataRow.createCell(2).setCellValue(order);
	    dataRow.createCell(3).setCellValue(ReusableLibrary.screenshot);
	    file.close();
	     
	    FileOutputStream outFile =new FileOutputStream(new File("Data.xls"));
	    workbook.write(outFile);
	    outFile.close();
	     
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	}
	
	
	public synchronized void dBTest(){
		
		Connection conDatabase=null;
		Statement stDatabase=null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conDatabase = DriverManager.getConnection("jdbc:postgresql://10.30.8.88:5432/profit", "dba", "dba");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stDatabase = ((Connection) conDatabase).createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strFormattedQuery = "";
		try {
			stDatabase.executeUpdate(strFormattedQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

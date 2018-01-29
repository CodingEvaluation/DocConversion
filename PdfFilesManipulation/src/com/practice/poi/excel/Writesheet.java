package com.practice.poi.excel;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Writesheet {

	 public static void main(String[] args) throws Exception {

	      //Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook(); 

	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");

	      //Create row object
	      XSSFRow row;

	      //This data needs to be written (Object[])
	      Map < String, Object[] > empinfo = 
	      new TreeMap < String, Object[] >();
	      empinfo.put( "1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
	      empinfo.put( "2", new Object[] { "e01", "Gopal", "Technical Manager" });
	      empinfo.put( "3", new Object[] { "e02", "Manisha", "Developer" });
	      empinfo.put( "4", new Object[] { "e03", "Masthan", "Developer" });
	      empinfo.put( "5", new Object[] { "e04", "Satish", "QA" });
	      empinfo.put( "6", new Object[] { "e05", "Krishna", "UAT" });
	      
	      //Iterate over data and write to sheet
	      Set < String > keyid = empinfo.keySet();
	      int rowid = 0;

	      for (String key : keyid) {
	         row = spreadsheet.createRow(rowid++);
	         Object [] objectArr = empinfo.get(key);
	         int cellid = 0;

	         for (Object obj : objectArr) {
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)obj);
	         }
	      }

	      //Write the workbook in file system
	      FileOutputStream out = new FileOutputStream(new File("D:/Pdf Files/Excel/Writesheet.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println("Writesheet.xlsx written successfully");
	   }
}

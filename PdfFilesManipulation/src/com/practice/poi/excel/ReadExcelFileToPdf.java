package com.practice.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFileToPdf {

	static XSSFRow row;

	public static void main(String[] args) throws Exception {

		FileInputStream fis = new FileInputStream(new File(
				"D:/Pdf Files/Excel/test.xlsx"));

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = spreadsheet.iterator();
		List<String> FolderIdList=new ArrayList<String>();
		List<String> documentIdlist=new ArrayList<String>();

		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

			if(cell.getColumnIndex()==1)
			{
			
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					//System.out.print(cell.getNumericCellValue() + " \t\t ");
					int num= (int) cell.getNumericCellValue();
					String s=num+"";
					//System.out.print(s + " \t\t ");
					FolderIdList.add(s);
					//System.out.print(cell.getRowIndex() + " \t\t ");
					break;

				case Cell.CELL_TYPE_STRING:
					//System.out.print(cell.getStringCellValue() + " \t\t ");
					FolderIdList.add(cell.getStringCellValue());
				
					break;
				}
				
			}else 	if(cell.getColumnIndex()==4)
			{
			
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					//System.out.print(cell.getNumericCellValue() + " \t\t ");
					int num= (int) cell.getNumericCellValue();
					String s=num+"";
					//System.out.print(s + " \t\t ");
					documentIdlist.add(s);
					//System.out.print(cell.getRowIndex() + " \t\t ");
					break;

				case Cell.CELL_TYPE_STRING:
					//System.out.print(cell.getStringCellValue() + " \t\t ");
					documentIdlist.add(cell.getStringCellValue());
					
				
				
					break;
				}
				
			}
			}
			//System.out.println();
		}
		fis.close();
		FolderIdList.remove(0);
		documentIdlist.remove(0);
		//System.out.println(documentIdlist);
		for(String s:FolderIdList){
			System.out.println("FolderId :"+s);
			//System.out.println("FolderId :"+s);
		}
		for(String s:documentIdlist){
			String [] splt= s.split("\n");
			for (String string : splt) {
				System.out.println("documentId "+string);
			}
			
		}
	}

}

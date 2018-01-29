package com.practice.itext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFListExample {
	public static void main(String args[]){
	    try {
		//Create Document instance.
		Document document = new Document();
	 
		//Create OutputStream instance.
		OutputStream outputStream = 
		    new FileOutputStream(new File("D:\\TestListFile.pdf"));
	 
		//Create PDFWriter instance.
	        PdfWriter.getInstance(document, outputStream);
	 
	        //Open the document.
	        document.open();
	 
	        //Create ordered list object
	        List orderedList = new List(List.ORDERED);
	        orderedList.add(new ListItem("Oredered List item1"));
	        orderedList.add(new ListItem("Oredered List item2"));
	        orderedList.add(new ListItem("Oredered List item3"));
	        orderedList.add(new ListItem("Oredered List item4"));
	 
	        //Create unordered list object
	        List unorderedList = new List(List.UNORDERED);
	        unorderedList.add(new ListItem("Unoredered List item1"));
	        unorderedList.add(new ListItem("Unoredered List item2"));
	        unorderedList.add(new ListItem("Unoredered List item3"));
	        unorderedList.add(new ListItem("Unoredered List item4"));
	 
	        //Add content to the document using List objects.
	        document.add(orderedList);
	        document.add(unorderedList);
	 
	        //Close document and outputStream.
	        document.close();
	        outputStream.close();
	 
	        System.out.println("Pdf created successfully.");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }


}

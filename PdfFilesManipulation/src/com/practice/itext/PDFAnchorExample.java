package com.practice.itext;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
public class PDFAnchorExample {
	
	public static void main(String args[]){
	    try {
		//Create Document instance.
		Document document = new Document();
	 
		//Create OutputStream instance.
		OutputStream outputStream = 
		    new FileOutputStream(new File("D:\\Practice\\TestAnchorFile.pdf"));
	 
		//Create PDFWriter instance.
	        PdfWriter.getInstance(document, outputStream);
	 
	        //Open the document.
	        document.open();
	 
	        //Create Anchor object
	        Anchor anchor = new Anchor("http://google.com/");
	        anchor.setReference("http://google.com/");
	 
	        //Add content to the document using Anchor object.
	        document.add(anchor);
	 
	        //Close document and outputStream.
	        document.close();
	        outputStream.close();
	 
	        System.out.println("Pdf created successfully.");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}

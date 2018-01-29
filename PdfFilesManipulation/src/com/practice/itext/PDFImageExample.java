package com.practice.itext;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFImageExample {
	
	public static void main(String args[]){
	    try {
		//Create Document instance.
		Document document = new Document();
	 
		//Create OutputStream instance.
		OutputStream outputStream = 
		    new FileOutputStream(new File("D:\\Practice\\TestImageFile.pdf"));
	 
		//Create PDFWriter instance.
	        PdfWriter.getInstance(document, outputStream);
	 
	        //Open the document.
	        document.open();
	 
	        //Create Image object
	 
	        Image image = Image.getInstance("D:\\Practice\\Penguins.jpg",false );
	 
	        //Add content to the document using Image object.
	        document.add(image);
	 
	        //Close document and outputStream.
	        document.close();
	        outputStream.close();
	 
	        System.out.println("Pdf created successfully.");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

}

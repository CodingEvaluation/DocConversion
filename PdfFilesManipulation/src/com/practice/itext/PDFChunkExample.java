package com.practice.itext;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
public class PDFChunkExample {
	
	  public static void main(String args[]){
		    try {
			//Create Document instance.
			Document document = new Document();
		 
			//Create OutputStream instance.
			OutputStream outputStream = 
				new FileOutputStream(new File("D:\\Practice\\TestChunkFile.pdf"));
		 
			//Create PDFWriter instance.
		        PdfWriter.getInstance(document, outputStream);
		 
		        //Open the document.
		        document.open();
		 
		        //Add content to the document using chunk objects.
		        document.add(new Chunk("Test chunk1."));
		        document.add(new Chunk("Test chunk2."));
		 
		        //Close document and outputStream.
		        document.close();
		        outputStream.close();
		 
		        System.out.println("Pdf created successfully.");
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		  }

}

package com.practice.itext;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
public class PDFReadExample {
	
	 public static void main(String args[]){
		    try {
			//Create PdfReader instance.
			PdfReader pdfReader = new PdfReader("D:\\Practice\\TestFile.pdf");	
		 
			//Get the number of pages in pdf.
			int pages = pdfReader.getNumberOfPages(); 
		 
			//Iterate the pdf through pages.
			for(int i=1; i<=pages; i++) { 
			  //Extract the page content using PdfTextExtractor.
			  String pageContent = 
			  	PdfTextExtractor.getTextFromPage(pdfReader, i);
		 
			  //Print the page content on console.
			  System.out.println("Content on Page "
			  		              + i + ": " + pageContent);
		      }
		 
		      //Close the PdfReader.
		      pdfReader.close();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  }

}

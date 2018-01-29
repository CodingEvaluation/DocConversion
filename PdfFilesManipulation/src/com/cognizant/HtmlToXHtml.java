package com.cognizant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.w3c.tidy.Tidy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
public class HtmlToXHtml {
	
	public static void main(String[] args) throws DocumentException, IOException {
		
		/*  File inputHtmlFile=new File("D:/Pdf Files/Html/DemoTest/input/H4.html");
		  File inputXhtmlFile=new File("D:/Pdf Files/Html/DemoTest/input/H4.xhtml");
		  File outputPdf=new  File("D:/Pdf Files/Html/DemoTest/input/HM4.pdf");*/
		
		File inputHtmlFile=new File("D:/Pdf Files/Word/input/sample2Doc.html");
		  File inputXhtmlFile=new File("D:/Pdf Files/Html/DemoTest/input/sample2Doc.xhtml");
		  File outputPdf=new  File("D:/Pdf Files/Html/DemoTest/input/sample2Doc.pdf");
		 
		  
		  /*Document document = new Document();
			//PdfDocument document = new PdfDocument();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPdf));
			document.open();
			
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(inputHtmlFile));
			document.close();*/
		  
		  Tidy tidy = new Tidy(); //HTML parser and pretty printer.
		  tidy.setXHTML(true); //true if tidy should output XHTML
		  tidy.parse(new FileInputStream(inputHtmlFile), new FileOutputStream(inputXhtmlFile));
		 
		  Document document = new Document();
		    // step 2
		    PdfWriter writer = PdfWriter.getInstance(document,
		        new FileOutputStream(outputPdf));
		    // step 3
		    document.open();
		    // step 4
		    XMLWorkerHelper.getInstance().parseXHtml(writer, document,
		            new FileInputStream(inputXhtmlFile));
		    // step 5
		    document.close();
		  
	
	
	
	}
	


}

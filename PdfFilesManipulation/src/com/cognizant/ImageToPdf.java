package com.cognizant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageToPdf {
/*	public static void main(String[] args) throws Exception {
		String dir = "D:/Pdf Files/demo/Merging different type of documents/input";
		String temp = "D:/Pdf Files/demo/Merging different type of documents/output/temp";
		 String dest = "D:/Pdf Files/demo/Merging different type of documents/output";
		 File f=new File("D:/Pdf Files/demo/Merging different type of documents/input/Penguins.tif");
		 
		 
		 OutputStream outputFile = new FileOutputStream(temp+"/"+"PenguinsPdf"+".pdf");
			ImageToPdf.convert(f,outputFile);
			System.out.println("pngPdf is done");
	}
*/
	public static void convert(File f, OutputStream outputFile)
			throws Exception {
		
		
		
		Document document = new Document();
		Image image = Image.getInstance(f.getAbsolutePath());
	
		PdfWriter writer = PdfWriter.getInstance(document, outputFile);
	    writer.open();
	    document.open();
	    document.setPageSize(new Rectangle(image.getScaledWidth() + 100, image.getScaledHeight() + 100 ));
	    document.newPage();
	    image.setAlignment(Element.ALIGN_CENTER);
	    document.add(image);
	    document.close();
	    writer.close();

		/*// String outputFile = "some.pdf";

		
		 * List<String> files = new ArrayList<String>(); files.add("some.jpg");
		 
         // f=new File("D:/Pdf Files/demo/Merging different type of documents/input");
		//Rectangle rc=new Rectangle(rect)
		Document document = new Document();

		PdfWriter.getInstance(document, outputFile);
		
		Rectangle one = new Rectangle(450,600);
		//Rectangle two = new Rectangle(700,400);
		document.setPageSize(one);
		document.setMargins(2, 2, 2, 2);
		document.open();

		document.newPage();
		Image image = Image.getInstance(f.getAbsolutePath());
		   document.setPageSize(image);		  
		   document.newPage();
		  // image.scaleToFit(495, 701);
		image.setAbsolutePosition(0, 0);
		
		image.scaleAbsoluteHeight(PageSize.A4.getHeight());
		image.scaleAbsoluteWidth(PageSize.A4.getWidth());
		document.add(image);

		document.close();*/

	}
}

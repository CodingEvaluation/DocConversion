package com.practice.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordToPdfConverter {

	/*public static void main(String[] args) throws Exception {
		// OPCPackage opc=new ZipPackagePart(container, partName, contentType)
		XWPFDocument doc2 = new XWPFDocument(new FileInputStream(
				"D:\\Pdf Files\\word\\input\\sample2.docx"));
		XWPFDocument doc3 = new XWPFDocument(new FileInputStream(
				"D:\\Pdf Files\\word\\input\\sample2.docx"));
		OutputStream out = new FileOutputStream(
				"D:\\Pdf Files\\word\\output\\sample22.docx");

		doc2.write(out);
		//doc3.write(out);
		out.flush();
		out.close();
		System.out.println("WordToPdfConverter is done");
	}*/
	public static void convert(InputStream inputstream,OutputStream out) throws Exception{
		

		
			/*InputStream inputstream = new FileInputStream(
					"D:\\Pdf Files\\word\\sample2.docx");*/
	//	HWPFDocument document1 = new HWPFDocument(inputstream);

	 	
		 	XWPFDocument document = new XWPFDocument(inputstream);

			
			
			PdfOptions options = PdfOptions.create().fontEncoding(
					"windows-1250");
			PdfConverter.getInstance().convert(document, out, options);
			

		 	
		} 

		


	public static void main(String[] args) throws Exception {
		
		
		
		String dir = "D:/Pdf Files/Word/input";
		String temp = "D:/Pdf Files/Word/output/temp";
		//String dest = "D:/Pdf Files/Html/DemoTest/output/";
	
		File[] files = new File(dir).listFiles();
       for (File file : files) {
    	  String s= file.getAbsolutePath();
    	   if (s.endsWith(".docx")) {

				//InputStream inputstream = new FileInputStream(f);
				OutputStream out = new FileOutputStream(temp + "/" +file.getName()
						+ ".pdf");

				XWPFDocument document = new XWPFDocument(new FileInputStream(file));

				
				
				PdfOptions options = PdfOptions.create().fontEncoding(
						"windows-1250");
				PdfConverter.getInstance().convert(document, out, options);
				System.out.println(file.getName()+"docx Pdf is done");
				// docsList.add(s); 
			}
	/*	long startTime = System.currentTimeMillis();

		try {
			// 1) Load docx with POI XWPFDocument
			// XWPFDocument document = new XWPFDocument(
			// Data.class.getResourceAsStream( "sample2.docx" ) );
			InputStream inputstream = new FileInputStream(
					"D:\\Pdf Files\\word\\sample2.docx");

			XWPFDocument document = new XWPFDocument(inputstream);

			// 2) Convert POI XWPFDocument 2 PDF with iText
			
			 * File outFile = new File( "target/sample22.pdf" );
			 * outFile.getParentFile().mkdirs();
			 

			OutputStream out = new FileOutputStream(
					"D:\\Pdf Files\\word\\wordpdf2.pdf");
			PdfOptions options = PdfOptions.create().fontEncoding(
					"windows-1250");
			PdfConverter.getInstance().convert(document, out, options);

		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("Generate  pdf with "
				+ (System.currentTimeMillis() - startTime) + " ms.");

	}
*/
}
       }
}

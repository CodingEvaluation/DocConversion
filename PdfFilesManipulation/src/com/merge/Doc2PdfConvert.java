package com.merge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


public class Doc2PdfConvert {
	public static void main2(String args[]) throws IOException {
	    /*
	     * Create input stream of the doc file - Provide exact path of the doc
	     * file to read
	     */
	    FileInputStream fistream = new FileInputStream(
	        "D:/Pdf Files/Word/Doc/input/P6.doc");
	    HWPFDocument document = new HWPFDocument(fistream);
	    System.out.println("Length of docment is :"
	        + document.characterLength());
	   // System.out.println(document.getText());
	    System.out.println(document.getDocumentText());
	  }

	//private static final String PDF = "src/main/resources/pdf.pdf";
	//private static final String HTML = "src/com/merge/input.html";

	public static void main3(String[] args) {
		String HTML = "D:/Pdf Files/Word/Doc/input/test.htm";
		try {
			//generateHTMLFromPDF(PDF);
			generatePDFFromHTML(HTML);
			System.out.println("Html to Pdf is done ");
		} catch (IOException | ParserConfigurationException | DocumentException e) {
			e.printStackTrace();
		}
	}
	public static void generatePDFFromHTML(String filename) throws ParserConfigurationException, IOException, DocumentException {
		Document document = new Document();
		//PdfDocument document = new PdfDocument();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/Pdf Files/Word/Doc/output/temp/HtmPdf.pdf"));
		document.open();
		
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(filename));
		document.close();
	} 
	public static void main4(String[] args) {
		 POIFSFileSystem fs = null;  
		    Document document = new Document();
		  //  PdfDocument document = new PdfDocument();
		     try {  
		         System.out.println("Starting the test");  
		         fs = new POIFSFileSystem(new FileInputStream("D:/Pdf Files/Word/Doc/input/P6.doc"));  

		         HWPFDocument doc = new HWPFDocument(fs);  
		         WordExtractor we = new WordExtractor(doc); 
		        /* String text=we.getText();
		         File f=new File("D:/Pdf Files/Word/Doc/output/temp/test.pdf");
		      Writer output=   	new BufferedWriter(new FileWriter(f));
		         output.write(text);
		         System.out.println("End the test");  */


		        // OutputStream file = new FileOutputStream(new File("D:\\test.pdf"));
		         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/Pdf Files/Word/Doc/output/temp/test.pdf"));  

					Range range = doc.getRange();
					document.open();
					writer.setPageEmpty(true);
					document.newPage();
					writer.setPageEmpty(true);
		         String[] paragraphs = we.getParagraphText();  
		    
		         for (int i = 0; i < paragraphs.length; i++) {  
		        	 org.apache.poi.hwpf.usermodel.Paragraph pr = range
								.getParagraph(i);
						// CharacterRun run = pr.getCharacterRun(i);
						// run.setBold(true);
						// run.setCapitalized(true);
						// run.setItalic(true);
						paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
						System.out.println("Length:" + paragraphs[i].length());
						System.out.println("Paragraph" + i + ": "
								+ paragraphs[i].toString());

						// add the paragraph to the document
						document.add(new Paragraph(paragraphs[i]));

						pr.clone(); 
		         
		         }  
		         System.out.println("Document testing completed");  
		     } catch (Exception e) {  
		         System.out.println("Exception during test");  
		         e.printStackTrace();  
		     } finally {  
		         document.close();  
		     }  
	}
	
	public static void main1(String[] args) throws Exception {
		List<OutputStream> tempFileList = new ArrayList<OutputStream>();
		// List<InputStream> intempFileList = new ArrayList<InputStream>();

		String dir = "D:/Pdf Files/Word/Doc/input";
		String temp = "D:/Pdf Files/Word/Doc/output/temp";
		String output = "D:/Pdf Files/Word/Doc/output";

		File f = new File(dir);
		File[] filenames = f.listFiles();
		for (int i = 0; i < filenames.length; i++) {

			InputStream inputstream = new FileInputStream(filenames[i]);

			HWPFDocument document = new HWPFDocument(inputstream);
                                 //   document.
			OutputStream out = new FileOutputStream(temp + "/" + "Output" + i
					+ ".pdf");
			//document.
			PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
			//PdfConverter.getInstance().convert(document, options, out);
			tempFileList.add(out);
		
	}
	}
}

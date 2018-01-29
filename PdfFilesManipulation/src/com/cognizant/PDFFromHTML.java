package com.cognizant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFFromHTML {
	public static void main(String[] args) throws Exception {
		String dir = "D:/Pdf Files/Html/DemoTest/input/temp";
		String temp = "D:/Pdf Files/Html/DemoTest/output/";
		String dest = "D:/Pdf Files/Html/DemoTest/output/";
		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files = new File(dir).listFiles();
       for (File file : files) {
    	  String s= file.getAbsolutePath();
    	   if (s.endsWith(".html")) {

				//InputStream inputstream = new FileInputStream(f);
				OutputStream out = new FileOutputStream(temp + "/" +file.getName()
						+ ".pdf");

				PDFFromHTML.generatePDFFromHTML(new File("D:/Pdf Files/Html/DemoTest/input/temp/H4.html").getAbsoluteFile(), out);
				System.out.println("Html Pdf is done");
				// docsList.add(s); 
			}else if (s.endsWith(".htm")) {

				//InputStream inputstream = new FileInputStream(f);
				OutputStream out = new FileOutputStream(temp + "/" +file.getName()
						+ ".pdf");

				//PDFFromHTML.generatePDFFromHTML(file, out);
				System.out.println("Htm Pdf is done");
				// docsList.add(s); 
			}
		}
	}
		
			
	
		 
		/*for (File f : files) {
			
		
		OutputStream out = new FileOutputStream(temp + "/" +f.getName()
				+ ".pdf");

		generatePDFFromHTML(f, out);
		System.out.println("HtmlPdf is done");
		}*/
		
	public static void generatePDFFromHTML(File filename,OutputStream out) throws ParserConfigurationException, IOException, DocumentException {
		Document document = new Document();
		//PdfDocument document = new PdfDocument();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();
		
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(filename));
		document.close();
	} 
	/*public static	void main(String[] args) throws Exception {
		
		
		try {
			String path = "D:/Pdf Files/Html/DemoTest/input/H33.pdf";
			PdfWriter pdfWriter = null;

			// create a new document
			Document document = new Document();
			
			
		
			String str = "";
			StringBuilder contentBuilder = new StringBuilder();
			BufferedReader in = null;

            // get Instance of the PDFWriter
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
                    path));
            //pdfWriter.setPdfVersion(PdfWriter.PDF_VERSION_1_4);
            pdfWriter.setLinearPageMode();
            pdfWriter.setFullCompression();


            // document header attributes
            document.addAuthor("");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("aaa");
            document.addTitle("");
            document.setPageSize(PageSize.A4);

            // open document
            document.open();

            HTMLWorker htmlWorker = new HTMLWorker(document);

            
            

            System.out.println("Html Content :");
            try {
                in = new BufferedReader(new FileReader("D:/Pdf Files/Html/DemoTest/input/H3.html"));

                while ((str = in.readLine()) != null) {

                    contentBuilder.append(str);
                    System.out.println(str);
                }
            } catch (IOException e) {
                System.out.print("HTML file close problem:" + e.getMessage());
            } finally {

                in.close();
                System.gc();
            }
            String content = contentBuilder.toString();

            htmlWorker.parse(new StringReader(content));

            document.close();

            pdfWriter.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
	}
	*/
}


package com.cognizant.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.HtmlToPdf;
import com.cognizant.ImageToPdf;
import com.cognizant.PDFFromHTML;
import com.itextpdf.text.DocumentException;
import com.practice.poi.excel.WordToPdfConverter;

public class Mergingdifferenttypeofdocuments {

	public static void main(String[] args) throws Exception {

		String dir = "D:/Pdf Files/demo/Merging different type of documents/input";
		String temp = "D:/Pdf Files/demo/Merging different type of documents/output/temp";
		String dest = "D:/Pdf Files/demo/Merging different type of documents/output";
		/*String dir = "D:/Pdf Files/Images/DemoTest/input/";
		String temp = "D:/Pdf Files/Images/DemoTest/output/temp";
		String dest = "D:/Pdf Files/Images/DemoTest/output/";*/
		//long time=System.currentTimeMillis();
		//System.out.println(" start time :: "+System.currentTimeMillis());

		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files = new File(dir).listFiles();

		/*
		 * List<String> imagesList=new ArrayList<String>(); List<String>
		 * pdfList=new ArrayList<String>(); List<String> docsList=new
		 * ArrayList<String>();
		 */
		for (File f : files) {

			// inputPdfList.add(new FileInputStream(f));
			// System.out.println(f.getName());
			String s = f.getAbsolutePath();

			if (s.endsWith(".png")) {
				// System.out.println("png files:: "+s);
				// InputStream inputstream = new FileInputStream(f);

				// String destTep=temp+"/"+"pngPdf"+".pdf";
				OutputStream outputFile = new FileOutputStream(temp + "/"
						+ f.getName() + ".pdf");
				ImageToPdf.convert(f, outputFile);
				System.out.println("pngPdf is done");
				// ConvertImageToPdf.convert(f, destTep);
				// pdfList.add(s);

			} else if (s.endsWith(".tif")) {
				// System.out.println("tif files:: "+s);
				OutputStream outputFile = new FileOutputStream(temp + "/"
						+ f.getName() + ".pdf");
				ImageToPdf.convert(f, outputFile);
				System.out.println("tifPdf is done");
				// inputPdfList.add(new FileInputStream(f));

				// createPdf(inputPdfList, dest);

			} else if (s.endsWith(".jpg")) {
				// System.out.println("jpeg files:: "+s);
				OutputStream outputFile = new FileOutputStream(temp + "/"
						+ f.getName() + ".pdf");
				ImageToPdf.convert(f, outputFile);
				System.out.println("jpgPdf is done");
				// inputPdfList.add(new FileInputStream(f));

				// createPdf(inputPdfList, dest);

			} else if (s.endsWith(".docx")) {
				// System.out.println("docx files:: "+s);
				// s = s.replace(s.substring(s.length()-5), "");
				// System.out.println(str);

				InputStream inputstream = new FileInputStream(f);
				OutputStream out = new FileOutputStream(temp + "/" + f.getName()
						+ ".pdf");

				WordToPdfConverter.convert(inputstream, out);
				System.out.println("docxPdf is done");
				// docsList.add(s);
			} else if (s.endsWith(".html")) {

				//InputStream inputstream = new FileInputStream(f);
				OutputStream out = new FileOutputStream(temp + "/" +f.getName()
						+ ".pdf");

				PDFFromHTML.generatePDFFromHTML(f, out);
				System.out.println("Html Pdf is done");
				// docsList.add(s); 
			}  else if (s.endsWith(".htm")) {

				//InputStream inputstream = new FileInputStream(f);
				OutputStream out = new FileOutputStream(temp + "/" +f.getName()
						+ ".pdf");

				PDFFromHTML.generatePDFFromHTML(f, out);
				System.out.println("Htm Pdf is done");
				// docsList.add(s);
			} else if (s.endsWith(".pdf")) {
				List<InputStream> inptupdf=new ArrayList<InputStream>();
				inptupdf.add(new FileInputStream(f));

				OutputStream out = new FileOutputStream(temp + "/" + f.getName()
						+ ".pdf");
				
				MultiplePDFsinto1PDF.mergePdfFiles(inptupdf, out);

				//HtmlToPdf.convert(f, out);
				System.out.println("Pdf is done");
				// docsList.add(s);
			}

		}
		// List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files1 = new File(temp).listFiles();

		try {

			for (File file : files1) {
				inputPdfList.add(new FileInputStream(file));
			}
			// Prepare output stream for merged pdf file.
			OutputStream outputStream = new FileOutputStream(dest + "/"
					+ "Output" + ".pdf");
			// call method to merge pdf files.
			MultiplePDFsinto1PDF.mergePdfFiles(inputPdfList, outputStream);
			//System.out.println("Pdf files merged successfully.");
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

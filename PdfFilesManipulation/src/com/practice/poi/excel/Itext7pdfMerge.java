package com.practice.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cognizant.demo.MultiplePDFsinto1PDF;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfWriter;

public class Itext7pdfMerge {
	public static void main(String[] args) throws Exception {
		String dir = "D:/Pdf Files/Images/DemoTest/input/";
		String temp = "D:/Pdf Files/Images/DemoTest/output/temp";
		String dest = "D:/Pdf Files/Images/DemoTest/output/";
		//long time=System.currentTimeMillis();
		System.out.println(" start time :: "+System.currentTimeMillis());

		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files1 = new File(temp).listFiles();

		try {

			for (File file : files1) {
				inputPdfList.add(new FileInputStream(file));
			}
			// Prepare output stream for merged pdf file.
			OutputStream outputStream = new FileOutputStream(dest + "/"
					+ "Output" + ".pdf");
			// call method to merge pdf files.
			Itext7pdfMerge.mergePdfFiles(inputPdfList, outputStream);
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println(" start time :: "+System.currentTimeMillis());
	}

		
	

	public static void mergePdfFiles(List<InputStream> inputPdfList,
			OutputStream outputStream) throws Exception {

		// Create document and pdfReader objects.
		Document document = new Document();
		List<PdfReader> readers = new ArrayList<PdfReader>();
		int totalPages = 0;
		// Create pdf Iterator object using inputPdfList.
		Iterator<InputStream> pdfIterator = inputPdfList.iterator();

		// Create reader list for the input pdf files.
		while (pdfIterator.hasNext()) {
			InputStream pdf = pdfIterator.next();
			PdfReader pdfReader = new PdfReader(pdf);
			readers.add(pdfReader);
			totalPages = totalPages + pdfReader.getNumberOfPages();
		}
		// Create writer for the outputStream
		// PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		PdfCopy copy = new PdfSmartCopy(document, outputStream);
		// Open document.
		document.open();
		// Contain the pdf data.

		// PdfContentByte pageContentByte = writer.getDirectContent();

		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		// Iterate and process the reader list.
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			// Create page and add content.
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				copy.addDocument(pdfReader);
			//	pdfImportedPage = writer.getImportedPage(pdfReader,currentPdfReaderPage);
				//pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}

		// Close document and outputStream.
		outputStream.flush();
		document.close();
		outputStream.close();

		System.out.println("Pdf files merged successfully.");

	}

}

package com.cognizant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class NnumberPdfFilesMerge {

	public static void main(String[] args) {

		// new FileInputStream(file)
		try {
			// Prepare input pdf file list as list of input stream.
			List<InputStream> inputPdfList = new ArrayList<InputStream>();
			// List<InputStream> inputPdfList11 = new ArrayList<InputStream>();
			File file = new File("D:/Pdf Files/170");
			File[] files = file.listFiles();

			for (File f : files) {
				// System.out.println(f.getName());
				inputPdfList.add(new FileInputStream(f));
			}
			

			// Prepare output stream for merged pdf file.
			OutputStream outputStream = new FileOutputStream(
					"D:\\Pdf Files\\Refernce Books\\NewMergedPdfFile.pdf");
			// call method to merge pdf files.
			mergePdfFiles(inputPdfList, outputStream);

		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	private static void mergePdfFiles(List<InputStream> inputPdfList,
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
			System.out.println(totalPages);
		}
		// Create writer for the outputStream
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		// Open document.
		document.open();
		// Contain the pdf data.
		PdfContentByte pageContentByte = writer.getDirectContent();

		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		// Iterate and process the reader list.
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			// Create page and add content.
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				pdfImportedPage = writer.getImportedPage(pdfReader,
						currentPdfReaderPage);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}

		// Close document and outputStream.
		outputStream.flush();
		document.close();
		outputStream.close();
		System.out.println(readers.size());
		System.out.println("Pdf files merged successfully.");

	}

}

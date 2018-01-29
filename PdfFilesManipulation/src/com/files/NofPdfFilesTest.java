package com.files;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class NofPdfFilesTest {

	public static void main(String[] args) {
		String outputFileName = "D:/Pdf Files/demo/Huge number of PDFs into 1 PDF/output/output.pdf";
		String folderLocation = "D:/Pdf Files/demo/Huge number of PDFs into 1 PDF/input";

		// Prepare input pdf file list as list of input stream.
		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files = new File(folderLocation).listFiles();

		try {

			for (File f : files) {
				inputPdfList.add(new FileInputStream(f));
			}
			// Prepare output stream for merged pdf file.
			OutputStream outputStream = new FileOutputStream(outputFileName);
			// call method to merge pdf files.
			mergePdfFiles(inputPdfList, outputStream);
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void mergePdfFiles(List<InputStream> inputPdfList,
			OutputStream outputStream) throws DocumentException, IOException {

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
			// System.out.println(totalPages);
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
		System.out.println("Start Time ::" + System.currentTimeMillis());
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
		System.out.println("End Time ::" + System.currentTimeMillis());
		System.out.println(readers.size());

		System.out.println("Pdf files merged successfully.");

	}

}

package com.cognizant.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MultiplePDFsinto1PDF {
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
			int totalNoPages = pdfReader.getNumberOfPages();

			for (int pageNo = 1; pageNo <= totalNoPages; pageNo++) {
				// Create page and add content.
				document.setPageSize(pdfReader.getPageSize(pageNo));
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

		System.out.println("Pdf files merged successfully.");

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * //new FileInputStream(file) try { // Prepare input pdf file list as list
	 * of input stream. List<InputStream> inputPdfList = new
	 * ArrayList<InputStream>(); inputPdfList.add(new FileInputStream(
	 * "D:/Pdf Files/demo/Multiple PDFs into 1 PDF/input/TestFile.pdf"));
	 * inputPdfList.add(new FileInputStream(
	 * "D:/Pdf Files/demo/Multiple PDFs into 1 PDF/input/TestFile2.pdf"));
	 * inputPdfList.add(new FileInputStream(
	 * "D:/Pdf Files/demo/Multiple PDFs into 1 PDF/input/TestFile3.pdf"));
	 * inputPdfList.add(new FileInputStream(
	 * "D:/Pdf Files/demo/Multiple PDFs into 1 PDF/input/sample2.pdf"));
	 * inputPdfList.add(new
	 * FileInputStream("D:/Pdf Files/demo/Multiple PDFs into 1 PDF/input/Btech.pdf"
	 * ));
	 * 
	 * 
	 * //Prepare output stream for merged pdf file. OutputStream outputStream =
	 * new FileOutputStream(
	 * "D:/Pdf Files/demo/Multiple PDFs into 1 PDF/output/output.pdf"); //call
	 * method to merge pdf files. mergePdfFiles(inputPdfList, outputStream);
	 * 
	 * } catch (FileNotFoundException e) { 
	 * e.printStackTrace(); } catch (Exception e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	/*
	 * public static void mergePdfFiles(List<InputStream> inputPdfList,
	 * OutputStream outputStream) throws Exception {
	 * 
	 * // Create document and pdfReader objects. Document document = new
	 * Document(); List<PdfReader> readers = new ArrayList<PdfReader>(); int
	 * totalPages = 0; // Create pdf Iterator object using inputPdfList.
	 * Iterator<InputStream> pdfIterator = inputPdfList.iterator();
	 * 
	 * // Create reader list for the input pdf files. while
	 * (pdfIterator.hasNext()) { InputStream pdf = pdfIterator.next(); PdfReader
	 * pdfReader = new PdfReader(pdf); readers.add(pdfReader); totalPages =
	 * totalPages + pdfReader.getNumberOfPages(); } // Create writer for the
	 * outputStream PdfWriter writer = PdfWriter.getInstance(document,
	 * outputStream);
	 * 
	 * // Open document. document.open(); // Contain the pdf data.
	 * PdfContentByte pageContentByte = writer.getDirectContent();
	 * 
	 * PdfImportedPage pdfImportedPage; int currentPdfReaderPage = 1;
	 * Iterator<PdfReader> iteratorPDFReader = readers.iterator();
	 * 
	 * // Iterate and process the reader list. while
	 * (iteratorPDFReader.hasNext()) { PdfReader pdfReader =
	 * iteratorPDFReader.next(); // Create page and add content. while
	 * (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
	 * document.newPage(); pdfImportedPage = writer.getImportedPage(pdfReader,
	 * currentPdfReaderPage); pageContentByte.addTemplate(pdfImportedPage, 0,
	 * 0); currentPdfReaderPage++; } currentPdfReaderPage = 1; }
	 * 
	 * // Close document and outputStream. outputStream.flush();
	 * document.close(); outputStream.close();
	 * 
	 * //System.out.println("Pdf files merged successfully.");
	 * 
	 * }
	 */
}

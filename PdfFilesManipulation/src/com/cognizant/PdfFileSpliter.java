package com.cognizant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfFileSpliter {

	public static void main(String[] args) {
		String fileName = "D:\\Pdf Files\\New Book\\Spring.pdf";

		PdfReader reader = null;
		try {
			reader = new PdfReader(fileName);
			System.out.println("Successfully read input file: " + fileName
					+ "n");
			int totalPages = reader.getNumberOfPages();
			System.out.println("There are total " + totalPages
					+ " pages in this input filen");
			int split = 0;
			int splittedPageSize = 1;
			for (int pageNum = 1; pageNum <= totalPages; pageNum += splittedPageSize) {
				split++;
				String outFile = fileName
						.substring(0, fileName.indexOf(".pdf"))
						+ "-split-"
						+ split + ".pdf";
				Document document = new Document(
						reader.getPageSizeWithRotation(1));
				PdfCopy writer = new PdfCopy(document, new FileOutputStream(
						outFile));
				document.open();
				int tempPageCount = 0;
				for (int offset = 0; offset < splittedPageSize
						&& (pageNum + offset) <= totalPages; offset++) {
					PdfImportedPage page = writer.getImportedPage(reader,
							pageNum + offset);
					writer.addPage(page);
					tempPageCount++;
				}

				document.close();
				writer.close();

				System.out.println("Split: [" + tempPageCount + " page]: "
						+ outFile);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPdfFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void WordFileCreation(String args[]) {
		try {
			// Create Document instance.
			Document document = new Document();

			// Create OutputStream instance.
			OutputStream outputStream = new FileOutputStream(new File(
					"D:\\Pdf Files\\TestFile6.pdf"));

			// Create PDFWriter instance.
			PdfWriter.getInstance(document, outputStream);

			// Open the document.
			document.open();

			// Add content to the document.
			document.add(new Paragraph(
					"Hello world, "
							+ "this is a test 3 pdf file.   "
							+ "We develop software solutions for the healthcare industry and need developers who are skilled at writing well designed, correct, efficient, testable and maintainable code. You’ll need to understand not just how to put those solutions together, but how to deploy, debug and maintain those solutions. "
							+ "A key point to note, is that we are a R&D oriented team and as such we require people who can take a brief and put together a prototype which then leads to a solution, as many of the problems we solve are non-standard and don not have a ready-made specification available.  This makes for an environment where the creative developer can excel."
							+ "As we journey from a monolithic to a more modular architecture, we are looking for experienced Spring Boot practitioners that can help guide our organisation.  In addition to developing performant Spring Boot applications using Hibernate and other database access techniques, you will have extensive experience deploying, scaling and managing complex distributed solutions."));

			// Close document and outputStream.
			document.newPage();
			document.add(new Paragraph(
					"Hello world  new page, "
							+ "this is a test 3 pdf file.   "
							+ "We develop software solutions for the healthcare industry and need developers who are skilled at writing well designed, correct, efficient, testable and maintainable code. You’ll need to understand not just how to put those solutions together, but how to deploy, debug and maintain those solutions. "
							+ "A key point to note, is that we are a R&D oriented team and as such we require people who can take a brief and put together a prototype which then leads to a solution, as many of the problems we solve are non-standard and don not have a ready-made specification available.  This makes for an environment where the creative developer can excel."
							+ "As we journey from a monolithic to a more modular architecture, we are looking for experienced Spring Boot practitioners that can help guide our organisation.  In addition to developing performant Spring Boot applications using Hibernate and other database access techniques, you will have extensive experience deploying, scaling and managing complex distributed solutions."));

			document.close();
			outputStream.close();

			System.out.println("Pdf created successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

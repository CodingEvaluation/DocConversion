package com.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfFileGenerator {
	public static void main(String args[]) throws IOException {
		Document document = null;
		OutputStream outputStream = null;
		try {
			String fileName = "D:\\TestPdfFiles\\TestFile.pdf";
			
			document = new Document();
			// Create Document instance.
			for (int i = 1; i <=200; i++) {

				// Create OutputStream instance.
				String outFile = fileName
						.substring(0, fileName.indexOf(".pdf"))
						+ "-split-"
						+i+".pdf";
				outputStream = new FileOutputStream(new File(outFile));
				// Create PDFWriter instance.
				PdfWriter.getInstance(document, outputStream);

			}
			// Open the document.
			document.open();
			// Add content to the document.
			document.add(new Paragraph(
					"Hello world, "

							+ "Cognizant enables global enterprises to address a dual mandate: to make their current operations as efficient and cost-effective as possible and to invest in "
							+ "innovation to unleash new potential across their organizations."
							+ "What makes Cognizant unique is our ability to help clients meet both challenges. We help them enhance productivity by ensuring that vital business functions work "
							+ "faster, cheaper and better. And, our ability to conceptualize, architect and implement new and expanded capabilities allows clients to transform legacy models to take "
							+ "their business to the next level."
							+ "this is a test pdf file."));

			System.out.println("Pdf created successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Close document and outputStream.
		document.close();
		outputStream.close();
	}

}

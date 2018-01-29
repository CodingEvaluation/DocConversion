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

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.DocumentException;

public class MultipleDocumentsinto1PDF {

	public static void main(String[] args) throws Exception {
		List<OutputStream> tempFileList = new ArrayList<OutputStream>();
		// List<InputStream> intempFileList = new ArrayList<InputStream>();

		String dir = "D:/Pdf Files/demo/Multiple documents into 1 PDF/input";
		String temp = "D:/Pdf Files/demo/Multiple documents into 1 PDF/output/temp";
		String output = "D:/Pdf Files/demo/Multiple documents into 1 PDF/output/";

		File f = new File(dir);
		File[] filenames = f.listFiles();
		for (int i = 0; i < filenames.length; i++) {

			InputStream inputstream = new FileInputStream(filenames[i]);

			XWPFDocument document = new XWPFDocument(inputstream);

			OutputStream out = new FileOutputStream(temp + "/" + "Output" + i
					+ ".pdf");
			PdfOptions options = PdfOptions.create().fontEncoding(
					"windows-1250");
			PdfConverter.getInstance().convert(document, out, options);
			tempFileList.add(out);
		}
		System.out.println("Document to Pdf Conversion is done ::");

		// System.out.println("====================================");
		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files = new File(temp).listFiles();

		try {

			for (File file : files) {
				inputPdfList.add(new FileInputStream(file));
			}
			// Prepare output stream for merged pdf file.
			OutputStream outputStream = new FileOutputStream(output + "/"
					+ "Output" + ".pdf");
			// call method to merge pdf files.
			MultiplePDFsinto1PDF.mergePdfFiles(inputPdfList, outputStream);
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

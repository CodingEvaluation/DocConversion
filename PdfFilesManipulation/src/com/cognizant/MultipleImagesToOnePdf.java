package com.cognizant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class MultipleImagesToOnePdf {

	public static void main(String[] args) throws IOException,
			DocumentException {
		File f = null;
		String[] IMAGES = null;
		//String DEST = "D:/Pdf Files/Images/MergedImage.pdf";
		// create new file
		f = new File("D:/Pdf Files/Images");

		// array of files and directory
		IMAGES = f.list();

		
		String DEST = "D:/Pdf Files/Images/MergedImage.pdf";

		File file = new File(DEST);
		file.getParentFile().mkdirs();

		
		// create new file
		f = new File("D:/Pdf Files/Images");

		// array of files and directory
		IMAGES = f.list();
		Image img = Image.getInstance(IMAGES[0]);
		Document document = new Document(img);
		PdfWriter.getInstance(document, new FileOutputStream(DEST));
		document.open();
		for (String image : IMAGES) {
			img = Image.getInstance(image);
			document.setPageSize(img);
			document.newPage();
			img.setAbsolutePosition(0, 0);
			document.add(img);
		}
		document.close();
	}

}

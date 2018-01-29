package com.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.docx4j.Docx4J;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class WordToPDFConverterDocx4j {
	public static void main(String[] args)  {
		 WordprocessingMLPackage wordMLPackage;
		
		try {
			wordMLPackage = WordprocessingMLPackage.load(new File("D:\\Pdf Files\\sample2.docx"));
			
			// Set up font mapper
	        Mapper fontMapper = new IdentityPlusMapper();
	        wordMLPackage.setFontMapper(fontMapper);

	        // Example of mapping missing font Algerian to installed font Comic Sans MS
	        PhysicalFont font 
	                = PhysicalFonts.getPhysicalFonts().get("Comic Sans MS");
	        fontMapper.getFontMappings().put("Algerian", font);   
	        Docx4J.toPDF(wordMLPackage, new FileOutputStream("D:\\Pdf Files\\WordMerge.pdf"));

	        //org.docx4j.convert.out.pdf.PdfConversion c 
	           // = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
	        //  = new org.docx4j.convert.out.pdf.viaIText.Conversion(wordMLPackage);
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        

      		
	}

}

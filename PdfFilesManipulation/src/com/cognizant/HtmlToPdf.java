package com.cognizant;
import java.io.*;

import com.lowagie.text.DocumentException;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class HtmlToPdf {
    
    public static void main(String[] args) 
            throws IOException, DocumentException {
    	
        String File_To_Convert = "D:/Pdf Files/Word/Doc/input/test.htm";
        String url = new File(File_To_Convert).toURI().toURL().toString();
        System.out.println(""+url);
        String HTML_TO_PDF = "D:/Pdf Files/Word/Doc/input/newHtm.pdf";
        OutputStream os = new FileOutputStream(HTML_TO_PDF);       
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);      
        renderer.layout();
        renderer.createPDF(os);        
        
        os.close();
        System.out.println("ConvertedFile is done");
    }
    public static void convert(File f,OutputStream os) 
            throws IOException, DocumentException {
      
    	String url=	f.toURI().toURL().toString();
    	
    	/*  String File_To_Convert = f.getAbsolutePath();
          String url = new File(File_To_Convert).toURI().toURL().toString();
        */   
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(f.toURI().toURL().toString());      
        renderer.layout();
        renderer.createPDF(os);        
        
        os.close();
        System.out.println("ConvertedFile is done");
    }
}
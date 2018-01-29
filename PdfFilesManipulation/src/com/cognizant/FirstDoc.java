package com.cognizant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;



public class FirstDoc {
    
    public static void main(String[] args) 
            throws IOException, DocumentException, Exception {
        String File_To_Convert = "D:/Pdf Files/Word/Doc/input/test.htm";
        String url = new File(File_To_Convert).toURI().toURL().toString();
        System.out.println(""+url);
        String HTML_TO_PDF = "D:/Pdf Files/Word/Doc/input/testNew.pdf";
        OutputStream os = new FileOutputStream(HTML_TO_PDF);       
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);      
        renderer.layout();
        renderer.createPDF(os);        
        os.close();
    }
}
package com.cognizant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class ConvertImageToPdf {

	/*public static void ImageToPdf(String arg[]) throws Exception {

		File root = new File("C:/Users/674710/Desktop");

		String outputFile = "some11.pdf";

		List<String> files = new ArrayList<String>();
		files.add("some.jpg");
		files.add("some2.jpg");
		files.add("some3.jpg");
		files.add("some4.jpg");
		files.add("some5.jpg");
		files.add("some6.jpg");

		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
		document.open();

		for (String f : files) {
			document.newPage();
			Image image = Image.getInstance(new File(root, f).getAbsolutePath());
			image.setAbsolutePosition(0, 0);
			image.setBorderWidth(0);
			image.scaleAbsoluteHeight(PageSize.A4.getHeight());
			image.scaleAbsoluteWidth(PageSize.A4.getWidth());
			document.add(image);
		}
		System.out.println(" ImageToPdf Success");

		document.close();

	}*/
	/*public static final String[] IMAGES = {
        "D:\\Pdf Files\\Images\\P1.tif",
        "D:\\Pdf Files\\Images\\P2.tif",
        "D:\\Pdf Files\\Images\\P3.tif"
    };*/
    public static final String DEST = "D:\\Pdf Files\\Images\\image123.pdf";
 
   
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
      //  file.getParentFile().mkdirs();
        new ConvertImageToPdf().createPdf(DEST);
    }
    public void createPdf(String dest) throws IOException, DocumentException {
    	
    	 String[] IMAGES = {
            "D:\\Pdf Files\\Images\\P1.tif",
            "D:\\Pdf Files\\Images\\P2.tif",
            "D:\\Pdf Files\\Images\\P3.tif"
        };
    	 for (String s : IMAGES) {
			File f=new File(s);
		}
        Image img = Image.getInstance(IMAGES[0]);
        Document document = new Document(img);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        for (String image : IMAGES) {
            img = Image.getInstance(image);
            document.setPageSize(img);
            document.newPage();
            img.setAbsolutePosition(0, 0);
            document.add(img);
        }
        
        document.close();
        System.out.println(" ImageToPdf Success");
    }
    public static void convert(File f,String dest) throws Exception {
    	//String input=f.getName();
       Image img = Image.getInstance(f.getName());
       Document document = new Document(img);
       PdfWriter.getInstance(document, new FileOutputStream(dest));
       document.open();
       
       document.setPageSize(new Rectangle(img.getScaledWidth() + 100, img.getScaledHeight() + 100 ));
      //     document.setPageSize(img);
           document.newPage();
           img.setAlignment(Element.ALIGN_CENTER);
           //img.setAbsolutePosition(0, 0);
           document.add(img);
      document.close();
       System.out.println(" ImageToPdf Success");
   }
    
    }


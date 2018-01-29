package com.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TestAll2Pdf {
	public static void main(String[] args) throws Exception {
		String folderLocation = "D:/Pdf Files/Test";
		 String dest = "D:\\Pdf Files\\Images\\images1111.pdf";
		 
		// Prepare input pdf file list as list of input stream.
		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		File[] files = new File(folderLocation).listFiles();

	List<String> imagesList=new ArrayList<String>();
	List<String> pdfList=new ArrayList<String>();
	List<String> docsList=new ArrayList<String>();

			for (File f : files) {
				
				inputPdfList.add(new FileInputStream(f));
				//System.out.println(f.getName());
				String s=f.getName();
				if(s.endsWith(".pdf")){
					System.out.println("pdf files:: "+s);
					pdfList.add(s);
					
				}else if (s.endsWith(".tif")) {
					System.out.println("tif files:: "+s);
					inputPdfList.add(new FileInputStream(f));
					
					createPdf(inputPdfList, dest);
					
				}else if (s.endsWith(".docx")) {
					System.out.println("docx files:: "+s);
					docsList.add(s);
				}
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
	public static void Word2Pdf( OutputStream out) {
	long startTime = System.currentTimeMillis();

    try
    {
        // 1) Load docx with POI XWPFDocument
        //XWPFDocument document = new XWPFDocument( Data.class.getResourceAsStream( "sample2.docx" ) );
        InputStream inputstream = new FileInputStream("D:\\Pdf Files\\word\\sample2.docx");
        
        XWPFDocument document =  new XWPFDocument(inputstream);

      
        //out = new FileOutputStream( "D:\\Pdf Files\\word\\wordpdf2.pdf");
        PdfOptions options = PdfOptions.create().fontEncoding( "windows-1250" );
        PdfConverter.getInstance().convert( document, out, options );
    }
    catch ( Throwable e )
    {
        e.printStackTrace();
    }

    System.out.println( "Generate wordpdf111.pdf with " + ( System.currentTimeMillis() - startTime ) + " ms." );
	
	

}
	
	public static void createPdf(List<InputStream> inputPdfList,String dest) throws IOException, DocumentException {
		
       // String [] IMAGES=(String[]) imagesList.toArray();
		Iterator<InputStream> itr= inputPdfList.iterator();
		while (itr.hasNext()) {
			InputStream inputStream = (InputStream) itr.next();
		String filename=	inputStream.toString();
		Image img = Image.getInstance(filename);
		 Document document = new Document(img);
		 PdfWriter.getInstance(document, new FileOutputStream(dest));
		  document.open();
		  document.setPageSize(img);
          document.newPage();
          img.setAbsolutePosition(0, 0);
          document.add(img);
		}
		
       
        
      
      /*  for (String image : IMAGES) {
            img = Image.getInstance(image);
            document.setPageSize(img);
            document.newPage();
            img.setAbsolutePosition(0, 0);
            document.add(img);
        }
        
        document.close();*/
        System.out.println(" ImageToPdf Success");
    }
}

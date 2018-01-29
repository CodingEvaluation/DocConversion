package com.cognizant;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.w3c.dom.Document;

public class Converter {
	private File docFile;
	private File file;

	public Converter(File docFile) {
		this.docFile = docFile;
	}

	public void convert(File file) {
		this.file = file;

		try {
			FileInputStream finStream = new FileInputStream(
					docFile.getAbsolutePath());
			HWPFDocument doc = new HWPFDocument(finStream);
			WordExtractor wordExtract = new WordExtractor(doc);
			Document newDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			/*
			 * WordToHtmlConverter wordToHtmlConverter = new
			 * WordToHtmlConverter(newDocument);
			 * wordToHtmlConverter.processDocument(doc);
			 */

			HWPFDocumentCore wordDocument = WordToHtmlUtils
					.loadDoc(new FileInputStream(docFile));
			WordToHtmlConverter wordToHtmlConverter = new InlineImageWordToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder()
							.newDocument());
			wordToHtmlConverter.processDocument(wordDocument);

			StringWriter stringWriter = new StringWriter();
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.METHOD, "html");
			transformer.transform(

			new DOMSource(wordToHtmlConverter.getDocument()), new StreamResult(
					stringWriter));

			String html = stringWriter.toString();

			FileOutputStream fos;
			DataOutputStream dos;

			try {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file)));
				
				

				out.write(html);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*JEditorPane editorPane = new JEditorPane();
			editorPane.setContentType("text/html");
			editorPane.setEditable(false);

			editorPane.setPage(file.toURI().toURL());

			JScrollPane scrollPane = new JScrollPane(editorPane);
			JFrame f = new JFrame("Display Html File");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.getContentPane().add(scrollPane);
			f.setSize(512, 342);
			f.setVisible(true);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {

		Converter c = new Converter(new File(
				"D:/Pdf Files/Word/input/sample2Doc.doc"));
		c.convert(new File("D:/Pdf Files/Word/input/sample2Doc.html"));
	}
}
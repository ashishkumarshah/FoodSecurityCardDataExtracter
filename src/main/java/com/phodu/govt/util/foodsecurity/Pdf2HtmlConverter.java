package com.phodu.govt.util.foodsecurity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.PDFText2HTML;

public class Pdf2HtmlConverter {
	private String inputFileName, outputFileName;

	public Pdf2HtmlConverter(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

	public void convert() throws IOException {
		PDDocument document = PDDocument.load(new File(inputFileName));
		
		Writer w = new FileWriter(outputFileName);

		PDFTextStripper stripper;
		// HTML stripper can't work page by page because of startDocument() callback
		stripper = new PDFText2HTML();
		stripper.setSortByPosition(false);
		stripper.setShouldSeparateByBeads(true);

		// Extract text for main document:
		stripper.writeText(document, w);
	}

}

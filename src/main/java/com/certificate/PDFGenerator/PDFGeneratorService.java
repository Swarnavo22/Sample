package com.certificate.PDFGenerator;

//package com.internity.certificateGenerator;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PDFGeneratorService {
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document (PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
	}
}


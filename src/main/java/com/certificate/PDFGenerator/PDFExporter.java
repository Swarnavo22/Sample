package com.certificate.PDFGenerator;

//package com.internity.certificateGenerator;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFExporter {

	private List<DeveloperTutorial> listUsers ;

	public PDFExporter(List<DeveloperTutorial> listUsers) {
		super();
		this.listUsers = listUsers;
	}
	
	 private void writeTableHeader(PdfPTable table) {	              
	    
	 }
	     
	    private void writeTableData(PdfPTable table) {

	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        //font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of Users", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	         
	        document.close();
	         
	    }
	
}

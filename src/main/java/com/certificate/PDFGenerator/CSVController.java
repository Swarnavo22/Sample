package com.certificate.PDFGenerator;

import java.io.File;
import java.io.FileOutputStream;
//package com.internity.certificateGenerator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

  @Autowired
  CSVService fileService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/csv/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<DeveloperTutorial>> getAllTutorials() {
    try {
      List<DeveloperTutorial> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }
  
  @GetMapping("/users/export")
  public void export(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Dispositon";
		String headerValue = "attachment; filename=users.pdf";
		response.setHeader(headerKey, headerValue);
		List<DeveloperTutorial> listUsers = fileService.listAll();
		PDFExporter exporter = new PDFExporter(listUsers);
		exporter.export(response);
		
//	  	Document document = new Document (PageSize.A4);
//		PdfWriter.getInstance(document, response.getOutputStream());
//		document.open();
	}
  
  ///////////////////////////////////////////////////////////////////////////
  
//  @RequestMapping(value = "/student" , method = RequestMethod.GET)
//  
//  public String template(Model m,@RequestParam("id") long studentId) {	//, RedirectAttributes redirectAttribute
//	  DeveloperTutorial obj = this.fileService.getStudent(studentId);
//	  System.out.println(studentId);
//	  System.out.println(obj);
//	  m.addAttribute("student",obj);
//	  m.addAttribute("standardDate", new Date());
//	  System.out.println(m.getAttribute("student"));
//	  return "template";
//	  
////	  ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
////	    templateResolver.setSuffix(".html");
////	    templateResolver.setTemplateMode(TemplateMode.HTML);
////
////	    TemplateEngine templateEngine = new TemplateEngine();
////	    templateEngine.setTemplateResolver(templateResolver);
////
////	    Context context = new Context();
////	    //context.setVariable("to", "Baeldung");
////
////	    return templateEngine.process("template",context);
//  
//  
//  }
//  
////  public String parseThymeleafTemplate() {
////	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
////	    templateResolver.setSuffix(".html");
////	    templateResolver.setTemplateMode(TemplateMode.HTML);
////
////	    TemplateEngine templateEngine = new TemplateEngine();
////	    templateEngine.setTemplateResolver(templateResolver);
////
////	    Context context = new Context();
////	    //context.setVariable("to", "Baeldung");
////
////	    return templateEngine.process("template",context);
////	}
//  
//  @RequestMapping(value = "/test" , method = RequestMethod.GET)
//  public void method(Model m) throws IOException {
//	  DeveloperTutorial obj = this.fileService.getStudent(1);
//	  System.out.println(1);
//	  System.out.println(obj);
//	  m.addAttribute("student",obj);
//	  m.addAttribute("standardDate", new Date());
//	  System.out.println(m.getAttribute("student"));
//	  //return "template";
//	  String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
//	    OutputStream outputStream = new FileOutputStream(outputFolder);
//
//	    ITextRenderer renderer = new ITextRenderer();
//	    renderer.setDocumentFromString("template");
//	    renderer.layout();
//	    renderer.createPDF(outputStream);
//
//	    outputStream.close();
//  }
//  public void generatePdfFromHtml(String html) throws IOException {
//	    String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
//	    OutputStream outputStream = new FileOutputStream(outputFolder);
//
//	    ITextRenderer renderer = new ITextRenderer();
//	    renderer.setDocumentFromString(html);
//	    renderer.layout();
//	    renderer.createPDF(outputStream);
//
//	    outputStream.close();
//	}
//  
  
  
  
  /////////////////////////////////////////////////////////////////////////////
  
  
  @RequestMapping(value = "/student" , method = RequestMethod.GET)
 
  public String template(Model m,@RequestParam("id") long studentId) {	//, RedirectAttributes redirectAttribute
	  DeveloperTutorial obj = this.fileService.getStudent(studentId);
	  System.out.println(studentId);
	  System.out.println(obj);
	  m.addAttribute("student",obj);
	  m.addAttribute("standardDate", new Date());
	  System.out.println(m.getAttribute("student"));
	  return "template";
  }
  
  

}
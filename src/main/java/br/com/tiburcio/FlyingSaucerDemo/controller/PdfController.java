package bremailT.com.tiburcio.FlyingSaucerDemo.controller;

import br.com.tiburcio.FlyingSaucerDemo.service.PdfService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<byte[]> getPdf() throws IOException, DocumentException {
        byte[] bytes = pdfService.createPdf();
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename("message.pdf").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);
    }
}

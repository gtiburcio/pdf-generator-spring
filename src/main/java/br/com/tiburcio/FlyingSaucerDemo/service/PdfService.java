package br.com.tiburcio.FlyingSaucerDemo.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] createPdf() throws IOException, DocumentException {
        Context context = new Context();
        context.setVariable("name", "Tiburcio");
        String processHtml = templateEngine.process("helloworld", context);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(processHtml);
        iTextRenderer.layout();
        iTextRenderer.createPDF(baos);
        byte[] byteArray = baos.toByteArray();
        baos.close();
        return byteArray;
    }
}

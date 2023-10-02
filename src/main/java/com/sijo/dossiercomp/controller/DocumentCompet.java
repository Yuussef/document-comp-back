package com.sijo.dossiercomp.controller;

import com.sijo.dossiercomp.service.EditionDossierService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class DocumentCompet {


        private final EditionDossierService editionDossierService;

        @GetMapping("/pdf")
        public void generatePdfReport(HttpServletResponse response) throws IOException, JRException {
                byte[] pdfReport = editionDossierService.generatePdfReport();

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=report.pdf");
                response.getOutputStream().write(pdfReport);
                response.getOutputStream().flush();
        }

        @CrossOrigin
        @GetMapping(value = "/word")
        public void exportDynamicTransactionReport(
                                                            HttpServletResponse response) throws IOException, JRException{
                editionDossierService.generateWordReport( response);
}

        }
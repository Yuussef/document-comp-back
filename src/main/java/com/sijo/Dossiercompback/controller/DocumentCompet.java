package com.sijo.Dossiercompback.controller;

import com.sijo.Dossiercompback.service.JasperReportService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/reports")
public class DocumentCompet {

        @Autowired
        private JasperReportService jasperReportService;

        @GetMapping("/pdf")
        public void generatePdfReport(HttpServletResponse response) throws IOException, JRException {
                byte[] pdfReport = jasperReportService.generatePdfReport();

                // Set the response headers for PDF
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=report.pdf");

                // Write the PDF report to the response stream
                response.getOutputStream().write(pdfReport);
                response.getOutputStream().flush();
        }

        @GetMapping(value = "/download")
        ResponseEntity<Void> exportDynamicTransactionReport(
                                                            HttpServletResponse response) throws IOException, JRException{
                jasperReportService.generateWordFromJasperReport( response);
    return ResponseEntity.ok().build();
}



        }
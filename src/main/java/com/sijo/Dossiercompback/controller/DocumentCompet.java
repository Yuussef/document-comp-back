package com.sijo.Dossiercompback.controller;

import com.sijo.Dossiercompback.service.JasperReportService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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

        @CrossOrigin
        @GetMapping(value = "/word")
        public void exportDynamicTransactionReport(
                                                            HttpServletResponse response) throws IOException, JRException{
                jasperReportService.generateWordFromJasperReport( response);
}



        }
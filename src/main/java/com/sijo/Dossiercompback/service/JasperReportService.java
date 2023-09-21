package com.sijo.Dossiercompback.service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportService {

    public byte[] generatePdfReport() throws JRException {
        // Load the JasperReport template
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/documentComp.jrxml"));

        // Create a data source (you can replace this with your own data source)
        JRDataSource dataSource = new JREmptyDataSource();

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        // Export the report to a PDF file
        JRPdfExporter exporter = new JRPdfExporter();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();

        return outputStream.toByteArray();
    }

    public void generateWordFromJasperReport(HttpServletResponse response) throws JRException, IOException {
        // Load the JasperReport template (.jrxml)
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/documentComp.jrxml"));

        // Create a data source (you can replace this with your data source)
        JRDataSource dataSource = createYourDataSource();

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "fileName" + ".docx");
        response.setContentType("application/octet-stream");
        exporter.exportReport();
    }

    private JRDataSource createYourDataSource() {
        // Implement your data source creation logic here
        // For example, you can use JRBeanCollectionDataSource for a list of JavaBeans
        // Replace this with your actual data source
        return new JRBeanCollectionDataSource(Collections.emptyList());
    }







}






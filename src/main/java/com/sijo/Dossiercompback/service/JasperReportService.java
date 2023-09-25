package com.sijo.Dossiercompback.service;

import com.sijo.Dossiercompback.model.SampleBean;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

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
        List<SampleBean> dataList = new ArrayList<SampleBean>();
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/documentComp.jrxml"));

        // Create a data source (you can replace this with your data source)
        JRDataSource dataSource = createYourDataSource();
        SampleBean sampleBean = new SampleBean();
        sampleBean.setName("some name");
        sampleBean.setColor("green");
        sampleBean.setJob("Fullstack developer");
        sampleBean.setExperience("5 ans experience");
        sampleBean.setAnnee("2018");
        sampleBean.setTitrediplome("Ingénieur d'état en Télécoms et Réseaux ");
        sampleBean.setEcole("– Ecole Nationale des Sciences Appliquées");
        sampleBean.setAnnee2("2013");
        sampleBean.setTitrediplome2("Baccalauréat en Sciences Mathématique");
        sampleBean.setEcole2("– Lycée XX");
        dataList.add(sampleBean);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader("Content-Disposition", "attachment;filename=jasperfile.docx");
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






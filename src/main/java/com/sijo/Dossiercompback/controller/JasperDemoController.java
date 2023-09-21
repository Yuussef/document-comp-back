package com.sijo.Dossiercompback.controller;


import com.sijo.Dossiercompback.model.SampleBean;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/document")
public class JasperDemoController {

    @GetMapping()
    public void getDocument(HttpServletResponse response) throws IOException, JRException {

        String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "Cherry.jasper")
            .getAbsolutePath();
        List<SampleBean> dataList = new ArrayList<SampleBean>();
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/documentComp.jrxml"));

        // Create a data source (you can replace this with your data source)
        JRDataSource dataSource = createYourDataSource();
        SampleBean sampleBean = new SampleBean();
        sampleBean.setName("some name");
        sampleBean.setColor("green");
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

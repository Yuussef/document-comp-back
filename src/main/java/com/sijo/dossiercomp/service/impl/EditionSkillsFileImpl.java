package com.sijo.dossiercomp.service.impl;

import com.sijo.dossiercomp.model.Resume;
import com.sijo.dossiercomp.model.TechnicalSkills;
import com.sijo.dossiercomp.service.EditionSkillsFileService;
import com.sijo.dossiercomp.utils.Constant;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EditionSkillsFileImpl implements EditionSkillsFileService {

    public byte[] generatePdfReport() throws JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/documentComp.jrxml"));
        JRDataSource dataSource = new JREmptyDataSource();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();

        return outputStream.toByteArray();
    }

    public void generateWordReport(HttpServletResponse response) throws JRException, IOException {
        List<Resume> dataList = new ArrayList<Resume>();
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/documentComp.jrxml"));
        Resume resume = new Resume();
        resume.setName("some name");
        resume.setColor("green");
        resume.setJob("Fullstack developer");
        resume.setExperience("5 ans experience");
        resume.setAnnee("2018");
        resume.setTitrediplome("Ingénieur d'état en Télécoms et Réseaux ");
        resume.setEcole("– Ecole Nationale des Sciences Appliquées");
        resume.setAnnee2("2013");
        resume.setTitrediplome2("Baccalauréat en Sciences Mathématique");
        resume.setEcole2("– Lycée XX");
        resume.setSkills(Constant.skills);
        TechnicalSkills technicalSkill = new TechnicalSkills();
        technicalSkill.setTitle("This title");
        technicalSkill.setLabel("this value");
        List<TechnicalSkills>technicalSkills = new ArrayList<>();
        technicalSkills.add(technicalSkill);
        resume.setTechnicalSkills(technicalSkills);
        dataList.add(resume);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        List<Map<String, Object>> myList = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("element", "Value 1");
        myList.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("element", "Value 2");
        myList.add(item2);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("MY_LIST", myList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader("Content-Disposition", "attachment;filename=jasperfile.docx");
        response.setContentType("application/octet-stream");
        exporter.exportReport();

    }


}






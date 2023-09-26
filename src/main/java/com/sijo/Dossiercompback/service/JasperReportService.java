package com.sijo.Dossiercompback.service;

import com.sijo.Dossiercompback.model.SampleBean;
import com.sijo.Dossiercompback.model.TechnicalSkills;
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
        sampleBean.setSkills("Compétences principales :Azure Active Directory, System Center Configuration Manager, VMware ESX Servers, Vmware, Microsoft Azure, Linux, Windows PowerShell, Dynamic Host Configuration Protocol, Red Hat Enterprise Linux, Domain Name System (DNS).\n" +
                "Gestion des Systèmes :Active Directory, Active Directory Federation Services, Nagios, Veeam, Vmware, Wsus.\n" +
                "Solution Cloud :Amazon Web Services, IBM Cloud Computing, Infrastructure As A Service (IaaS), Microsoft Azure, Platform As A Service (PAAS).\n" +
                "Cyber-sécurité :Kali Linux, Metasploit, Security Information and Event Management, Symantec.\n" +
                "Systèmes d'exploitation :CentOS, Linux, Microsoft Windows, Windows Servers.\n" +
                "Virtualisation :VMware ESX Servers, VMware VSphere, Vcenter, Xendesktop.\n" +
                "Cloud computing :Azure Active Directory, Cloud Platform System, Nutanix.\n" +
                "Design et Développement Web :Cascading Style Sheets (CSS), HTML5, Web Applications.\n" +
                "Outils de développement logiciel :Docker, Red Hat Enterprise Linux, Terraform.\n" +
                "Bureau et productivité :Microsoft Access, Microsoft Office.\n" +
                "C et C++ :C, C++.\n" +
                "Protection contre les logiciels malveillants :Antivirus Softwares, Nessus.\n" +
                "Autres :Bash Shell, Domain Name System (DNS), Dynamic Host Configuration Protocol, Grafana, Java, Kubernetes, Snort, System Center Configuration Manager, Vrealize Operations Manager (VROPS), Windows PowerShell.\n");
        TechnicalSkills technicalSkill = new TechnicalSkills();
        technicalSkill.setTitle("This title");
        technicalSkill.setLabel("this value");
        List<TechnicalSkills>technicalSkills = new ArrayList<>();
        technicalSkills.add(technicalSkill);
        sampleBean.setTechnicalSkills(technicalSkills);
        dataList.add(sampleBean);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("Item", technicalSkills);
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






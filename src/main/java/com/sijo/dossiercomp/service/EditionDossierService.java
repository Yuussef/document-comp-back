package com.sijo.dossiercomp.service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface EditionDossierService {
     byte[] generatePdfReport() throws JRException;
     void generateWordReport(HttpServletResponse response) throws JRException, IOException;
}

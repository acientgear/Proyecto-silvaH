package com.app.silvahnosbe.services.reports;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface FacturaInterface {
    byte[] exportPdf(String fechaInicio, String fechaFin) throws JRException, FileNotFoundException;
}

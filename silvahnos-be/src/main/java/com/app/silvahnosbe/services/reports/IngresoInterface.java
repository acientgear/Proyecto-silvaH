package com.app.silvahnosbe.services.reports;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface IngresoInterface {
    byte[] exportPdf(String fechaInicio, String fechaFin) throws JRException, FileNotFoundException;
}

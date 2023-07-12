package com.app.silvahnosbe.services.reports;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface EgresoInterface {
    byte[] exportPdf(int anio, int mes) throws JRException, FileNotFoundException;
}

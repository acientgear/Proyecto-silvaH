package com.app.silvahnosbe.services.reports.impl;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.repositories.IngresoRepository;
import com.app.silvahnosbe.services.reports.IngresoInterface;
import com.app.silvahnosbe.util.IngresosReportGenerator;

import net.sf.jasperreports.engine.JRException;

@Service
public class IgresoImpl implements IngresoInterface {
    
    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private IngresosReportGenerator ingresosReportGenerator;

    @Override
    public byte[] exportPdf(int anio, int mes) throws JRException, FileNotFoundException {
        return ingresosReportGenerator.exportToPdf(ingresoRepository.obtenerIngresos(anio,mes));
    }
    
}


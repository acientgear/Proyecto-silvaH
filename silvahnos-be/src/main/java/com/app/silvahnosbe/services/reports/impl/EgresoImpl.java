package com.app.silvahnosbe.services.reports.impl;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.repositories.EgresoRepository;
import com.app.silvahnosbe.services.reports.EgresoInterface;
import com.app.silvahnosbe.util.EgresosReportGenerator;

import net.sf.jasperreports.engine.JRException;

@Service
public class EgresoImpl implements EgresoInterface{

    @Autowired
    private EgresoRepository egresoRepository;

    @Autowired
    private EgresosReportGenerator egresosReportGenerator;

    @Override
    public byte[] exportPdf(int anio, int mes) throws JRException, FileNotFoundException {
        return egresosReportGenerator.exportToPdf(egresoRepository.obtenerEgresosPorAnioAndMes(anio,mes));
    }
}

package com.app.silvahnosbe.services.reports.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EgresoEntity;
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

    //constructor
    public EgresoImpl(EgresoRepository egresoRepository, EgresosReportGenerator egresosReportGenerator) {
        this.egresoRepository = egresoRepository;
        this.egresosReportGenerator = egresosReportGenerator;
    }

    @Override
    public byte[] exportPdf(String fechaInicio, String fechaFin) throws JRException, FileNotFoundException {
        List<EgresoEntity> list = egresoRepository.obtenerEgresosEntre(fechaInicio, fechaFin);
        Integer total = egresoRepository.obtenerTotalEntre(fechaInicio, fechaFin);
        return egresosReportGenerator.exportToPdf(list, total);
    }
}

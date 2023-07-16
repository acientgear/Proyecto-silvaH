package com.app.silvahnosbe.services.reports.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.IngresoEntity;
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
    public byte[] exportPdf(String fechaInicio, String fechaFin) throws JRException, FileNotFoundException {
        List<IngresoEntity> list = ingresoRepository.obtenerIngresosEntre(fechaInicio, fechaFin);
        Integer total = ingresoRepository.obtenerTotalEntre(fechaInicio, fechaFin);
        return ingresosReportGenerator.exportToPdf(list, total);
    }
    
}


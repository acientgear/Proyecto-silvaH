package com.app.silvahnosbe.services.reports.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.services.reports.FacturaInterface;
import com.app.silvahnosbe.util.FacturasReportGenerator;

import net.sf.jasperreports.engine.JRException;

@Service
public class FacturaImpl implements FacturaInterface {
    
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturasReportGenerator facturasReportGenerator;

    //constructor
    public FacturaImpl(FacturaRepository facturaRepository, FacturasReportGenerator facturasReportGenerator) {
        this.facturaRepository = facturaRepository;
        this.facturasReportGenerator = facturasReportGenerator;
    }

    @Override
    public byte[] exportPdf(String fechaInicio, String fechaFin) throws JRException, FileNotFoundException {
        List<FacturaEntity> list = facturaRepository.obtenerFacturasEntre(fechaInicio, fechaFin);
        Integer total = facturaRepository.obtenerTotalEntre(fechaInicio, fechaFin);
        return facturasReportGenerator.exportToPdf(list, total, total*0.19);
    }

    public FacturaRepository getFacturaRepository() {
        return facturaRepository;
    }

    public FacturasReportGenerator getFacturasReportGenerator() {
        return facturasReportGenerator;
    }
    
}


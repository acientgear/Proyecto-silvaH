package com.app.silvahnosbe.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.FacturaEntity;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class FacturasReportGenerator {

    private final ResourceLoader resourceLoader;

    @Autowired
    public FacturasReportGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    public byte[] exportToPdf(List<FacturaEntity> list, Integer total, Double iva_total) throws JRException, FileNotFoundException{
        return JasperExportManager.exportReportToPdf(getReport(list, total,iva_total));
    }

    private JasperPrint getReport(List<FacturaEntity> list, Integer total, Double iva_total) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("facturasData", new JRBeanCollectionDataSource(list));
        params.put("total", total);
        params.put("iva_total", iva_total);

        // Cargar el archivo utilizando ResourceLoader
        Resource resource = resourceLoader.getResource("classpath:facturas.jrxml");
        try {
            InputStream inputStream = resource.getInputStream();

            JasperPrint report = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(inputStream),
                params,
                new JREmptyDataSource()
            );

            return report;
        } catch (IOException e) {
            throw new JRException("Error al leer el archivo jrxml", e);
        }
    }
}

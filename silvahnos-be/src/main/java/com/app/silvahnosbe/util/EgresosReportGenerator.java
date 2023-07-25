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

import com.app.silvahnosbe.entities.EgresoEntity;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EgresosReportGenerator {

    private final ResourceLoader resourceLoader;

    @Autowired
    public EgresosReportGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
    *funcion que exporta a pdf una lista de egresos
    * @param List<EgresoEntity> list, Integer total
    * @return retorna una lista de bytes que representan el pdf generado
    */

    public byte[] exportToPdf(List<EgresoEntity> list, Integer total) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list, total));
    }

    /**
    *funcion que crea un jasperprint a partir de una lista de egresos
    * @param List<EgresoEntity> list, Integer total
    * @return retorna un jasperprint
    */

    private JasperPrint getReport(List<EgresoEntity> list, Integer total) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("egresosData", new JRBeanCollectionDataSource(list));
        params.put("total", total);

        // Cargar el archivo utilizando ResourceLoader
        Resource resource = resourceLoader.getResource("classpath:egresos.jrxml");
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

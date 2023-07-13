package com.app.silvahnosbe.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.app.silvahnosbe.entities.IngresoEntity;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class IngresosReportGenerator {
    public byte[] exportToPdf(List<IngresoEntity> list) throws JRException, FileNotFoundException{
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    private JasperPrint getReport(List<IngresoEntity> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ingresosData", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:ingresos.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
}

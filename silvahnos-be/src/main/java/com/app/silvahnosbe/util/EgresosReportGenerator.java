package com.app.silvahnosbe.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

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
    
    public byte[] exportToPdf(List<EgresoEntity> list, Integer total) throws JRException, FileNotFoundException{
        return JasperExportManager.exportReportToPdf(getReport(list, total));
    }

    private JasperPrint getReport(List<EgresoEntity> list, Integer total) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("egresosData", new JRBeanCollectionDataSource(list));
        params.put("total", total);

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:egresos.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
    
}

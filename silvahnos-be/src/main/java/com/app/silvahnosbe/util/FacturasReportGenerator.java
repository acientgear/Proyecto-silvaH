package com.app.silvahnosbe.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

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
    public byte[] exportToPdf(List<FacturaEntity> list, Integer total, Double iva_total) throws JRException, FileNotFoundException{
        return JasperExportManager.exportReportToPdf(getReport(list, total,iva_total));
    }

    private JasperPrint getReport(List<FacturaEntity> list, Integer total, Double iva_total) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("facturasData", new JRBeanCollectionDataSource(list));
        params.put("total", total);
        params.put("iva_total", iva_total);

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:facturas.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
}

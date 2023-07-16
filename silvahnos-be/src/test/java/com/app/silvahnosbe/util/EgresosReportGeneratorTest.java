package com.app.silvahnosbe.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRVerticalFiller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EgresosReportGeneratorTest {


    @Mock
    private EgresoRepository egresoRepository;

    @Test
    public void testGetReport() throws FileNotFoundException, JRException {
        // Mock input parameters
        List<EgresoEntity> list = new ArrayList<>();
        Timestamp fecha = new Timestamp(System.currentTimeMillis());
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(1l);
        egreso.setMonto(15000);
        egreso.setDescripcion("pintura");
        egreso.setBorrado(false);
        egreso.setFecha_creacion(fecha);
        egresoRepository.save(egreso);
        list.add(egreso);

        // Add some EgresoEntity objects to the list

        Integer total = 100;

        // Perform the test
        JasperPrint report = getReport(list, total);

        // Verify the result
        assertNotNull(report);

        // Opcional: Verificar el contenido del reporte inspeccionando sus partes
        JRPrintPage pagina = (JRPrintPage) report.getPages().get(0);
        List<JRPrintElement> partes = pagina.getElements();
        for (JRPrintElement parte : partes) {
            if (parte instanceof JRPrintText) {
                JRPrintText texto = (JRPrintText) parte;
                System.out.println(texto.getFullText());
            }
        }
    }

    private JasperPrint getReport(List<EgresoEntity> list, Integer total) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("egresosData", new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(list));
        params.put("total", total);

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:egresos.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
    
}

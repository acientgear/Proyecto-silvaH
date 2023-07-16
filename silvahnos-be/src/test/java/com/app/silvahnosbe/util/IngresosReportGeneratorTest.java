package com.app.silvahnosbe.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ExtendWith(MockitoExtension.class)
public class IngresosReportGeneratorTest {
    @Mock 
    IngresoRepository ingresoRepository;

    @Test
    public void testGetReport() throws FileNotFoundException, JRException {
        // Mock input parameters
        List<IngresoEntity> list = new ArrayList<>();
        Timestamp fecha = new Timestamp(System.currentTimeMillis());
        IngresoEntity ingreso = new IngresoEntity();
        ingreso.setId(1l);
        ingreso.setMonto(15000);
        ingreso.setDescripcion("pintura");
        ingreso.setBorrado(false);
        ingreso.setFecha_creacion(fecha);
        ingresoRepository.save(ingreso);
        list.add(ingreso);

        // Add some IngresoEntity objects to the list

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

    private JasperPrint getReport(List<IngresoEntity> list, Integer total) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ingresosData", new JRBeanCollectionDataSource(list));
        params.put("total", total);

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:ingresos.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
}

package com.app.silvahnosbe.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

    /*@InjectMocks
    private EgresosReportGenerator egresosReportGenerator;

    @Mock
    private JasperCompileManager jasperCompileManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportToPdf() throws JRException, IOException {
        // Given
        List<EgresoEntity> list = new ArrayList<>();
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(1l);
        egreso.setMonto(15000);
        egreso.setDescripcion("pintura");
        egreso.setBorrado(false);
        egreso.setFecha_creacion(new Timestamp(System.currentTimeMillis()));
        list.add(egreso);

        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setId(1l);
        egreso2.setMonto(15000);
        egreso2.setDescripcion("pintura");
        egreso2.setBorrado(false);
        egreso2.setFecha_creacion(new Timestamp(System.currentTimeMillis()));
        list.add(egreso2);

        EgresoEntity egreso3 = new EgresoEntity();
        egreso3.setId(1l);
        egreso3.setMonto(15000);
        egreso3.setDescripcion("pintura");
        egreso3.setBorrado(false);
        egreso3.setFecha_creacion(new Timestamp(System.currentTimeMillis()));
        list.add(egreso3);
        // Add some dummy data to the list

        Integer total = 1989989879; // Some dummy total value

        // You can use an actual list of EgresoEntity here or create some dummy data
        // for the report

        // When
        byte[] actualPdf = egresosReportGenerator.exportToPdf(list, total);

        // Load the expected PDF from the resources folder
        byte[] expectedPdf = ResourceUtils
                .getFile("classpath:egresos.jrxml").toURI().toURL().openStream()
                .readAllBytes();

        // Then
        assertArrayEquals(expectedPdf, actualPdf);
    }*/
    
}

package com.app.silvahnosbe.service.reports.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.services.reports.impl.FacturaImpl;
import com.app.silvahnosbe.util.FacturasReportGenerator;

import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
public class FacturaImplTest {
    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private FacturasReportGenerator facturasReportGenerator;

    @InjectMocks
    private FacturaImpl facturaImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        facturaImpl = new FacturaImpl(facturaRepository, facturasReportGenerator);
    }

    @Test
    public void testFacturaImplConstructor() {
        // Given
        FacturaRepository mockFacturaRepository = facturaRepository;
        FacturasReportGenerator mockFacturasReportGenerator = facturasReportGenerator;

        // When
        FacturaImpl facturaImpl = new FacturaImpl(mockFacturaRepository, mockFacturasReportGenerator);

        // Then
        assertEquals(mockFacturaRepository, facturaImpl.getFacturaRepository());
        assertEquals(mockFacturasReportGenerator, facturaImpl.getFacturasReportGenerator());
    }

    /*@Test
    public void testExportPdf() throws Exception {
        // Mock input parameters
        String fechaInicio = "2023-01-01";
        String fechaFin = "2023-01-31";

        // Mocked list of FacturaEntity
        List<FacturaEntity> mockedFacturasList = new ArrayList<>();
        FacturaEntity facturaEntity = new FacturaEntity();
        mockedFacturasList.add(facturaEntity);
        // Add some mocked FacturaEntity objects to the list

        // Mocked total
        Integer mockedTotal = 100;
        Double ivaTotal = 0.19;

        // Mocked PDF content
        byte[] mockedPdfBytes = "Mocked PDF Content".getBytes();

        // Stub the behavior of facturaRepository.obtenerfacturaentre()
        when(facturaRepository.obtenerFacturasEntre(fechaInicio, fechaFin))
                .thenReturn(mockedFacturasList);

        // Stub the behavior of facturaRepository.obtenerTotalEntre()
        when(facturaRepository.obtenerTotalEntre(fechaInicio, fechaFin))
                .thenReturn(mockedTotal);

        // Stub the behavior of facturasReportGenerator.exportToPdf()
        when(facturasReportGenerator.exportToPdf(mockedFacturasList, mockedTotal, ivaTotal))
                .thenReturn(mockedPdfBytes);

        // Perform the test
        byte[] result = facturaImpl.exportPdf(fechaInicio, fechaFin);

        // Verify the result
        System.out.println("result: " + result);
        System.out.println("mockedPdfByadsadsafdsklafsdfdstes: " + mockedPdfBytes);
        assertArrayEquals(mockedPdfBytes, result);
    }*/
}

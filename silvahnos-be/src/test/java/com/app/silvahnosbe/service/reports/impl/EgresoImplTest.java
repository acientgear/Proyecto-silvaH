package com.app.silvahnosbe.service.reports.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;
import com.app.silvahnosbe.services.reports.impl.EgresoImpl;
import com.app.silvahnosbe.util.EgresosReportGenerator;

@ExtendWith(MockitoExtension.class)
class EgresoImplTest {

    @Mock
    private EgresoRepository egresoRepository;

    @Mock
    private EgresosReportGenerator egresosReportGenerator;

    private EgresoImpl egresoImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        egresoImpl = new EgresoImpl(egresoRepository, egresosReportGenerator);
    }

    @Test
    void testExportPdf() throws Exception {
        // Mock input parameters
        String fechaInicio = "2023-01-01";
        String fechaFin = "2023-01-31";

        // Mocked list of EgresoEntity
        List<EgresoEntity> mockedEgresosList = new ArrayList<>();
        // Add some mocked EgresoEntity objects to the list

        // Mocked total
        Integer mockedTotal = 100;

        // Mocked PDF content
        byte[] mockedPdfBytes = "Mocked PDF Content".getBytes();

        // Stub the behavior of egresoRepository.obtenerEgresosEntre()
        when(egresoRepository.obtenerEgresosEntre(fechaInicio, fechaFin))
                .thenReturn(mockedEgresosList);

        // Stub the behavior of egresoRepository.obtenerTotalEntre()
        when(egresoRepository.obtenerTotalEntre(fechaInicio, fechaFin))
                .thenReturn(mockedTotal);

        // Stub the behavior of egresosReportGenerator.exportToPdf()
        when(egresosReportGenerator.exportToPdf(mockedEgresosList, mockedTotal))
                .thenReturn(mockedPdfBytes);

        // Perform the test
        byte[] result = egresoImpl.exportPdf(fechaInicio, fechaFin);

        // Verify the result
        assertArrayEquals(mockedPdfBytes, result);
    }
    
}

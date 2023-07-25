package com.app.silvahnosbe.service.reports.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;
import com.app.silvahnosbe.services.reports.impl.IngresoImpl;
import com.app.silvahnosbe.util.IngresosReportGenerator;

@ExtendWith(MockitoExtension.class)
class IngresoImplTest {
    @Mock
    private IngresoRepository ingresoRepository;

    @Mock
    private IngresosReportGenerator ingresosReportGenerator;

    private IngresoImpl ingresoImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ingresoImpl = new IngresoImpl(ingresoRepository, ingresosReportGenerator);
    }

    @Test
    void testExportPdf() throws Exception {
        // Mock input parameters
        String fechaInicio = "2023-01-01";
        String fechaFin = "2023-01-31";

        // Mocked list of IngresoEntity
        List<IngresoEntity> mockedIngresosList = new ArrayList<>();
        // Add some mocked IngresoEntity objects to the list

        // Mocked total
        Integer mockedTotal = 100;

        // Mocked PDF content
        byte[] mockedPdfBytes = "Mocked PDF Content".getBytes();

        // Stub the behavior of IngresosRepository.obtenerIngresossEntre()
        when(ingresoRepository.obtenerIngresosEntre(fechaInicio, fechaFin))
                .thenReturn(mockedIngresosList);

        // Stub the behavior of IngresosRepository.obtenerTotalEntre()
        when(ingresoRepository.obtenerTotalEntre(fechaInicio, fechaFin))
                .thenReturn(mockedTotal);

        // Stub the behavior of IngresossReportGenerator.exportToPdf()
        when(ingresosReportGenerator.exportToPdf(mockedIngresosList, mockedTotal))
                .thenReturn(mockedPdfBytes);

        // Perform the test
        byte[] result = ingresoImpl.exportPdf(fechaInicio, fechaFin);

        // Verify the result
        assertArrayEquals(mockedPdfBytes, result);
    }
}

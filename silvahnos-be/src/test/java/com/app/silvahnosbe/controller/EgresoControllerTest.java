package com.app.silvahnosbe.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.EgresoController;
import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.EgresoService;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.reports.EgresoInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class EgresoControllerTest {
    
    @InjectMocks
    private EgresoController egresoController;

    @Mock
    private EgresoInterface egresoInterface;

    @Mock
    private MovimientoService movimientoService;

    @Mock
    private EgresoService egresoService;

    @DisplayName("Test para obtener todos los egresos")
    @Test
    void testGetAllEgresos_ExistenEgresos_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<EgresoEntity> egresos = new ArrayList<>();
        egresos.add(new EgresoEntity());
        when(egresoService.obtenerEgresoPorAnioAndMes(anio, mes)).thenReturn(egresos);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getEgresoByAnioAndMes(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egresos, response.getBody());
    }

    @DisplayName("Test para obtener todos los egresos cuando no existen egresos")
    @Test
    void testGetAllEgresos_NoExistenEgresos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(egresoService.obtenerEgresoPorAnioAndMes(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getEgresoByAnioAndMes(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

@DisplayName("Test para crear una Egreso con tipo creación")
    @Test
    void testCreateEgreso_EgresoCreada_ReturnsEgreso_Creación() {
        // Given
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(null);
        when(egresoService.guardarEgreso(egreso)).thenReturn(egreso);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.createEgreso(egreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egreso, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Creación", capturedMovimiento.getTipo());
        assertEquals("egreso", capturedMovimiento.getNombre_tabla());
        assertEquals(egreso.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una Egreso con tipo modificación")
    @Test
    void testCreateEgreso_EgresoCreada_ReturnsEgreso_Egreso_Modificacion() {
        // Given
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(1l);
        egreso.setBorrado(false);
        when(egresoService.guardarEgreso(egreso)).thenReturn(egreso);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.createEgreso(egreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egreso, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("egreso", capturedMovimiento.getNombre_tabla());
        assertEquals(egreso.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una Egreso con tipo eliminacion")
    @Test
    void testCreateEgreso_EgresoCreada_ReturnsEgreso_Egreso_Eliminacion() {
        // Given
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(1l);
        egreso.setBorrado(true);
        when(egresoService.guardarEgreso(egreso)).thenReturn(egreso);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.createEgreso(egreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egreso, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Eliminación", capturedMovimiento.getTipo());
        assertEquals("egreso", capturedMovimiento.getNombre_tabla());
        assertEquals(egreso.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para eliminar un egreso")
    @Test
    void testDeleteEgreso_EgresoEliminado_ReturnsOk() {
        // Given
        Long id = 1L;
        EgresoEntity egreso = new EgresoEntity();
        when(egresoService.obtenerEgresoPorId(id)).thenReturn(egreso);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.deleteEgreso(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Test para eliminar un egreso que no existe")
    @Test
    void testDeleteEgreso_EgresoNoExiste_ReturnsNotFound() {
        // Given
        Long id = 1L;
        when(egresoService.obtenerEgresoPorId(id)).thenReturn(null);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.deleteEgreso(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener los ultimos egresos")
    @Test
    void testGetUltimosEgresos_ExistenEgresos_ReturnsList() {
        // Given
        List<EgresoEntity> egresos = new ArrayList<>();
        egresos.add(new EgresoEntity());
        when(egresoService.obtenerUltimosEgresos()).thenReturn(egresos);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getUltimosEgresos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egresos, response.getBody());
    }

    @DisplayName("Test para obtener los ultimos egresos cuando no existen egresos")
    @Test
    void testGetUltimosEgresos_NoExistenEgresos_ReturnsNotFound() {
        // Given
        when(egresoService.obtenerUltimosEgresos()).thenReturn(null);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getUltimosEgresos();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener el total de egresos por mes")
    @Test
    void testGetTotalEgresos_ExistenEgresos_ReturnsDouble() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        Integer total = 1000;
        when(egresoService.obtenerTotalEgresosPorMes(anio, mes)).thenReturn(total);

        // When
        ResponseEntity<Integer> response = egresoController.getTotalEgresosPorMes(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(total, response.getBody());
    }

    @DisplayName("Test para obtener el total de egresos por mes cuando no existen egresos")
    @Test
    void testGetTotalEgresos_NoExistenEgresos_ReturnsNotFound() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        when(egresoService.obtenerTotalEgresosPorMes(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<Integer> response = egresoController.getTotalEgresosPorMes(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener el monto por dia")
    @Test
    void testGetMontoPorDia_ExistenEgresos_ReturnsDouble() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        List<Integer> monto = new ArrayList<>();
        monto.add(1000);
        monto.add(2000);
        when(egresoService.getMontosPorDia(anio, mes)).thenReturn(monto);

        // When
        ResponseEntity<List<Integer>> response = egresoController.getMontoPorDia(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(monto, response.getBody());
    }

    @DisplayName("Test para obtener el monto por dia cuando no existen egresos")
    @Test
    void testGetMontoPorDia_NoExistenEgresos_ReturnsNotFound() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        when(egresoService.getMontosPorDia(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<Integer>> response = egresoController.getMontoPorDia(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @DisplayName("Test para método exportPdf")
    @Test
    public void testExportPdf() throws Exception {
        // Mock input parameters
        String fechaInicio = "2023-01-01";
        String fechaFin = "2023-01-31";

        // Mocked output
        byte[] pdfBytes = "Mocked PDF Content".getBytes();

        // Obtener hora y minutos actuales del sistema u hora local
        Timestamp fecha = new Timestamp(System.currentTimeMillis());
        String dia = fecha.toString().substring(8, 10);
        String mes = fecha.toString().substring(5, 7);
        String anio = fecha.toString().substring(0, 4);
        String horaMin = fecha.toString().substring(11, 16).replace(":", "-");

        // Stub the behavior of egresoInterface.exportPdf()
        when(egresoInterface.exportPdf("2023-01-01 00:00:00", "2023-01-31 23:59:59"))
                .thenReturn(pdfBytes);

        // Perform the test
        ResponseEntity<Resource> response = egresoController.exportPdf(fechaInicio, fechaFin);

        // Verify the response
        String parte1 = "form-data; name=\"attachment\"; filename=\"Egresos Desde=01-01-2023 Hasta=31-01-2023 Generado="+dia+"-"+mes+"-"+anio+" ";
        String parte2 = horaMin + ".pdf\"";
        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
        assertEquals(parte1 + parte2,
                response.getHeaders().getContentDisposition().toString());
        assertEquals(List.of("Content-Disposition"), response.getHeaders().getAccessControlExposeHeaders());

        ByteArrayResource resource = (ByteArrayResource) response.getBody();
        assertEquals(pdfBytes.length, resource.contentLength());
        assertEquals(new String(pdfBytes), new String(resource.getByteArray()));
    }


}

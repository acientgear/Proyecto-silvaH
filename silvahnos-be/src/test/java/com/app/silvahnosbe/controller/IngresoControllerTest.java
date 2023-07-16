package com.app.silvahnosbe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.IngresoController;
import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.services.IngresoService;
import com.app.silvahnosbe.services.reports.IngresoInterface;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngresoControllerTest {

    @InjectMocks
    private IngresoController ingresoController;
    
    @Mock
    private IngresoInterface ingresoInterface;

    @Mock
    private IngresoService ingresoService;

    @DisplayName("Test para obtener todos los ingresos")
    @Test
    void testGetAllIngresos_ExistenIngresos_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<IngresoEntity> ingresos = new ArrayList<>();
        ingresos.add(new IngresoEntity());
        when(ingresoService.obtenerIngresos(anio, mes)).thenReturn(ingresos);

        // When
        ResponseEntity<List<IngresoEntity>> response = ingresoController.getAllIngresos(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingresos, response.getBody());
    }

    @DisplayName("Test para obtener todos los ingresos cuando no existen ingresos")
    @Test
    void testGetAllIngresos_NoExistenIngresos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(ingresoService.obtenerIngresos(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<IngresoEntity>> response = ingresoController.getAllIngresos(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear uun ingreso")
    @Test
    void testCreateIngreso() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();
        when(ingresoService.guardarIngreso(ingreso)).thenReturn(ingreso);

        // When
        ResponseEntity<IngresoEntity> response = ingresoController.createIngreso(ingreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingreso, response.getBody());
    }

    @DisplayName("Test para obtener últimos ingresos")
    @Test
    void testGetUltimosIngresos_ExistenIngresos_ReturnsList() {
        // Given
        List<IngresoEntity> ingresos = new ArrayList<>();
        ingresos.add(new IngresoEntity());
        when(ingresoService.obtenerUltimosIngresos()).thenReturn(ingresos);

        // When
        ResponseEntity<List<IngresoEntity>> response = ingresoController.getUltimosIngresos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingresos, response.getBody());
    }

    @DisplayName("Test para obtener últimos ingresos cuando no existen ingresos")
    @Test
    void testGetUltimosIngresos_NoExistenIngresos_ReturnsNotFound() {
        // Given
        when(ingresoService.obtenerUltimosIngresos()).thenReturn(null);

        // When
        ResponseEntity<List<IngresoEntity>> response = ingresoController.getUltimosIngresos();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener total de ingresos")
    @Test
    void testGetTotalIngresos_ExistenIngresos_ReturnsTotal() {
        // Given
        int anio = 2023;
        int mes = 5;
        int total = 100;
        when(ingresoService.obtenerTotalIngresosPorMes(anio, mes)).thenReturn(total);

        // When
        ResponseEntity<Integer> response = ingresoController.getTotalIngresos(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(total, response.getBody());
    }

    @DisplayName("Test para obtener total de ingresos cuando no existen ingresos")
    @Test
    void testGetTotalIngresos_NoExistenIngresos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(ingresoService.obtenerTotalIngresosPorMes(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<Integer> response = ingresoController.getTotalIngresos(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener monto por día")
    @Test
    void testGetMontoPorDia_ExistenIngresos_ReturnsMonto() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        List<Integer> monto = new ArrayList<>();
        monto.add(1000);
        monto.add(2000);
        when(ingresoService.getMontosPorDia(anio, mes)).thenReturn(monto);

        // When
        ResponseEntity<List<Integer>> response = ingresoController.getMontoPorDia(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(monto, response.getBody());
    }

    @DisplayName("Test para obtener monto por día cuando no existen ingresos")
    @Test
    void testGetMontoPorDia_NoExistenIngresos_ReturnsNotFound() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        when(ingresoService.getMontosPorDia(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<Integer>> response = ingresoController.getMontoPorDia(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener saldo de cuenta")
    @Test
    void testGetSaldoCuenta_ExistenIngresos_ReturnsSaldo() {
        // Given
        int mes = 5;
        int saldo = 100;
        when(ingresoService.obtenerSaldoCuenta(mes)).thenReturn(saldo);

        // When
        ResponseEntity<Integer> response = ingresoController.obtenerSaldoCuenta(mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saldo, response.getBody());
    }

    @DisplayName("Test para obtener saldo de cuenta cuando no existen ingresos")
    @Test
    void testGetSaldoCuenta_NoExistenIngresos_ReturnsNotFound() {
        // Given
        int mes = 5;
        when(ingresoService.obtenerSaldoCuenta(mes)).thenReturn(null);

        // When
        ResponseEntity<Integer> response = ingresoController.obtenerSaldoCuenta(mes);

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
        String horaMin = fecha.toString().substring(11, 16).replace(":", "-");
        System.out.println(horaMin);

        // Stub the behavior of ingresoInterface.exportPdf()
        when(ingresoInterface.exportPdf("2023-01-01 00:00:00", "2023-01-31 23:59:59"))
                .thenReturn(pdfBytes);

        // Perform the test
        ResponseEntity<Resource> response = ingresoController.exportPdf(fechaInicio, fechaFin);

        // Verify the response
        String parte1 = "form-data; name=\"attachment\"; filename=\"Ingresos Desde=01-01-2023 Hasta=31-01-2023 Generado=16-07-2023 ";
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

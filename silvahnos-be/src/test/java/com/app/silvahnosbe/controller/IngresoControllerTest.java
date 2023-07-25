package com.app.silvahnosbe.controller;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.IngresoController;
import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.IngresoService;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.reports.IngresoInterface;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngresoControllerTest {

    @InjectMocks
    private IngresoController ingresoController;
    
    @Mock
    private IngresoInterface ingresoInterface;

    @Mock
    private MovimientoService movimientoService;

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

    @DisplayName("Test para crear una Ingreso con tipo creación")
    @Test
    void testCreateIngreso_IngresoCreada_ReturnsIngreso_Creación() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();
        ingreso.setId(null);
        when(ingresoService.guardarIngreso(ingreso)).thenReturn(ingreso);

        // When
        ResponseEntity<IngresoEntity> response = ingresoController.createIngreso(ingreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingreso, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Creación", capturedMovimiento.getTipo());
        assertEquals("ingreso", capturedMovimiento.getNombre_tabla());
        assertEquals(ingreso.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una Ingreso con tipo modificación")
    @Test
    void testCreateIngreso_IngresoCreada_ReturnsIngreso_Ingreso_Modificacion() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();
        ingreso.setId(1l);
        ingreso.setBorrado(false);
        when(ingresoService.guardarIngreso(ingreso)).thenReturn(ingreso);

        // When
        ResponseEntity<IngresoEntity> response = ingresoController.createIngreso(ingreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingreso, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("ingreso", capturedMovimiento.getNombre_tabla());
        assertEquals(ingreso.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una Ingreso con tipo eliminacion")
    @Test
    void testCreateIngreso_IngresoCreada_ReturnsIngreso_Ingreso_Eliminacion() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();
        ingreso.setId(1l);
        ingreso.setBorrado(true);
        when(ingresoService.guardarIngreso(ingreso)).thenReturn(ingreso);

        // When
        ResponseEntity<IngresoEntity> response = ingresoController.createIngreso(ingreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingreso, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Eliminación", capturedMovimiento.getTipo());
        assertEquals("ingreso", capturedMovimiento.getNombre_tabla());
        assertEquals(ingreso.getId(), capturedMovimiento.getId_objeto());
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
    void testExportPdf() throws Exception {
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
        when(ingresoInterface.exportPdf("2023-01-01 00:00:00", "2023-01-31 23:59:59"))
                .thenReturn(pdfBytes);

        // Perform the test
        ResponseEntity<Resource> response = ingresoController.exportPdf(fechaInicio, fechaFin);

        // Verify the response
        String parte1 = "form-data; name=\"attachment\"; filename=\"Ingresos Desde=01-01-2023 Hasta=31-01-2023 Generado="+dia+"-"+mes+"-"+anio+" ";
        String parte2 = horaMin + ".pdf\"";
        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
        assertEquals(parte1 + parte2,
                response.getHeaders().getContentDisposition().toString());
        assertEquals(List.of("Content-Disposition"), response.getHeaders().getAccessControlExposeHeaders());

        ByteArrayResource resource = (ByteArrayResource) response.getBody();
        assertEquals(pdfBytes.length, resource.contentLength());
        assertEquals(new String(pdfBytes), new String(resource.getByteArray()));
    }

    @DisplayName("Test para obtener los montos de los últimos 5 días")
    @Test
    void testGetMontos5dias_ExistenMontos_ReturnsList() {
        // Given
        List<IngresoEntity> ingresos = new ArrayList<>();
        ingresos.add(new IngresoEntity());
        List<Integer> montos = new ArrayList<>();
        montos.add(1000);
        montos.add(2000);
        when(ingresoService.getMontosUltimos5Dias()).thenReturn(montos);
    
        // When
        ResponseEntity<List<Integer>> response = ingresoController.getMontos5dias();
    
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(montos, response.getBody());
    }
    

    @DisplayName("Test para obtener los montos de los últimos 5 días - Montos nulos")
    @Test
    void testGetMontos5dias_MontosNull_ReturnsNotFound() {
        // Given
        when(ingresoService.getMontosUltimos5Dias()).thenReturn(null);

        // When
        ResponseEntity<List<Integer>> response = ingresoController.getMontos5dias();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }


}

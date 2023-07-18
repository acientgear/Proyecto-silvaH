package com.app.silvahnosbe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.app.silvahnosbe.controllers.FacturaController;
import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.FacturaService;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.reports.FacturaInterface;

import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    @Mock
    private MovimientoService movimientoService;

    @Mock
    private FacturaInterface facturaInterface;

    @DisplayName("Test para obtener todas las facturas")
    @Test
    void testGetAllFacturas_ExistenFacturas_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(new FacturaEntity());
        when(facturaService.obtenerFacturas(anio, mes)).thenReturn(facturas);

        // When
        ResponseEntity<List<FacturaEntity>> response = facturaController.getFacturas(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facturas, response.getBody());
    }

    @DisplayName("Test para obtener todas las facturas cuando no existen facturas")
    @Test
    void testGetAllFacturas_NoExistenFacturas_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(facturaService.obtenerFacturas(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<FacturaEntity>> response = facturaController.getFacturas(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear una factura con tipo creación")
    @Test
    void testCreateFactura_FacturaCreada_ReturnsFactura_Creación() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        factura.setId(null);
        when(facturaService.guardarFactura(factura)).thenReturn(factura);

        // When
        ResponseEntity<FacturaEntity> response = facturaController.createFactura(factura);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Creación", capturedMovimiento.getTipo());
        assertEquals("factura", capturedMovimiento.getNombre_tabla());
        assertEquals(factura.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una factura con tipo modificación")
    @Test
    void testCreateFactura_FacturaCreada_ReturnsFactura_Factura_Modificacion() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        factura.setId(1l);
        factura.setBorrado(false);
        when(facturaService.guardarFactura(factura)).thenReturn(factura);

        // When
        ResponseEntity<FacturaEntity> response = facturaController.createFactura(factura);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("factura", capturedMovimiento.getNombre_tabla());
        assertEquals(factura.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una factura con tipo eliminacion")
    @Test
    void testCreateFactura_FacturaCreada_ReturnsFactura_Factura_Eliminacion() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        factura.setId(1l);
        factura.setBorrado(true);
        when(facturaService.guardarFactura(factura)).thenReturn(factura);

        // When
        ResponseEntity<FacturaEntity> response = facturaController.createFactura(factura);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Eliminación", capturedMovimiento.getTipo());
        assertEquals("factura", capturedMovimiento.getNombre_tabla());
        assertEquals(factura.getId(), capturedMovimiento.getId_objeto());
    }


    @DisplayName("Test para obtener el iva")
    @Test
    void testGetIva_ExistenFacturas_ReturnsIva() {
        // Given
        int anio = 2023;
        int mes = 5;
        Integer iva = 100;
        when(facturaService.obtenerIva(anio, mes)).thenReturn(iva);

        // When
        ResponseEntity<Integer> response = facturaController.getIva(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(iva, response.getBody());
    }

    @DisplayName("Test para obtener el iva cuando no existen facturas")
    @Test
    void testGetIva_NoExistenFacturas_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(facturaService.obtenerIva(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<Integer> response = facturaController.getIva(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener las facturas proximas a vencer")
    @Test
    void testGetProximasVencer_ExistenFacturas_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(new FacturaEntity());
        when(facturaService.obtenerProximasVencer(anio, mes)).thenReturn(facturas);

        // When
        ResponseEntity<List<FacturaEntity>> response = facturaController.getProximasVencer(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facturas, response.getBody());
    }

    @DisplayName("Test para obtener las facturas proximas a vencer cuando no existen facturas")
    @Test
    void testGetProximasVencer_NoExistenFacturas_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(facturaService.obtenerProximasVencer(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<FacturaEntity>> response = facturaController.getProximasVencer(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener las facturasV (pedro)")
    @Test
    void testMetodoFacturasV2() {
        // Given
        int dias = 4;
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(new FacturaEntity());
        when(facturaService.facturaV(dias)).thenReturn(facturas);

        // When
        ResponseEntity<List<FacturaEntity>> response = facturaController.facturasV(dias);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facturas, response.getBody());
    }

    @DisplayName("Test para obtener las facturasV (pedro) cuando no existen facturas")
    @Test
    void testMetodoFacturasV2_NoExistenFacturas_ReturnsNotFound() {
        // Given
        int dias = 4;
        when(facturaService.facturaV(dias)).thenReturn(null);

        // When
        ResponseEntity<List<FacturaEntity>> response = facturaController.facturasV(dias);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para pagar una factura")
    @Test
    void testPagarFactura_FacturaPagada_ReturnsFactura() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        when(facturaService.pagarFactura(factura)).thenReturn(factura);

        // When
        ResponseEntity<FacturaEntity> response = facturaController.pagarFactura(factura);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());
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
        when(facturaInterface.exportPdf("2023-01-01 00:00:00", "2023-01-31 23:59:59"))
                .thenReturn(pdfBytes);

        // Perform the test
        ResponseEntity<Resource> response = facturaController.exportPdf(fechaInicio, fechaFin);

        // Verify the response
        String parte1 = "form-data; name=\"attachment\"; filename=\"Facturas Desde=01-01-2023 Hasta=31-01-2023 Generado="+dia+"-"+mes+"-"+anio+" ";
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

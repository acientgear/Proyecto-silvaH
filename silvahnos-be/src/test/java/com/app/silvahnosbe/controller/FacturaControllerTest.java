package com.app.silvahnosbe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.FacturaController;
import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.services.FacturaService;

@ExtendWith(MockitoExtension.class)
public class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

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

    @DisplayName("Test para crear una factura")
    @Test
    void testCreateFactura_FacturaCreada_ReturnsFactura() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        when(facturaService.guardarFactura(factura)).thenReturn(factura);

        // When
        ResponseEntity<FacturaEntity> response = facturaController.createFactura(factura);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());
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

    
}

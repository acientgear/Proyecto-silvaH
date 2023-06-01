package com.app.silvahnosbe.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacturaControllerTest {
    
    @InjectMocks
    private FacturaController facturaController;

    @Mock
    private FacturaService facturaService;


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

    Date fecha= new Date();
    FacturaEntity factura;

    @DisplayName("Test para crear una factura")
    @Test
    void testCreateFactura_FacturaCreada_ReturnsFactura() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        factura.setNumero_factura(131);
        factura.setFecha_emision(fecha);
        factura.setFecha_vencimiento(fecha);
        factura.setFecha_pago(fecha);
        factura.setObservaciones("pintura");
        factura.setBorrado(false);
        factura.setFecha_creacion(new Timestamp(new Date().getTime()));
        when(facturaService.guardarFactura(factura)).thenReturn(factura);

        // When
        ResponseEntity<FacturaEntity> response = facturaController.createFactura(factura);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());
    }

}

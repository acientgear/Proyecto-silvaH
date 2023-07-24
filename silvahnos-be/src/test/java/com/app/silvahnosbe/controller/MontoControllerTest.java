package com.app.silvahnosbe.controller;

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

import com.app.silvahnosbe.controllers.MontoController;
import com.app.silvahnosbe.models.Monto;
import com.app.silvahnosbe.services.MontoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MontoControllerTest {
    
    @InjectMocks
    private MontoController montoController;

    @Mock
    private MontoService montoService;

    @DisplayName("Test para obtener todos los montos de ingreso")
    @Test
    void testGetAllMontosIngreso_ExistenMontos_ReturnsList() {
        // Given
        int anio = 2023;
        List<Monto> montos = new ArrayList<>();
        montos.add(new Monto());
        when(montoService.obtenerMontoIngresoTotalMesAnual(anio)).thenReturn(montos);

        // When
        ResponseEntity<List<Monto>> response = montoController.obtenerMontosIngresoTotalMesAnual(anio);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(montos, response.getBody());
    }

    @DisplayName("Test para obtener todos los montos de ingreso cuando no existen montos")
    @Test
    void testGetAllMontosIngreso_NoExistenMontos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        when(montoService.obtenerMontoIngresoTotalMesAnual(anio)).thenReturn(null);

        // When
        ResponseEntity<List<Monto>> response = montoController.obtenerMontosIngresoTotalMesAnual(anio);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener todos los montos de egreso")
    @Test
    void testGetAllMontosEgreso_ExistenMontos_ReturnsList() {
        // Given
        int anio = 2023;
        List<Monto> montos = new ArrayList<>();
        montos.add(new Monto());
        when(montoService.obtenerMontoEgresoTotalMesAnual(anio)).thenReturn(montos);

        // When
        ResponseEntity<List<Monto>> response = montoController.obtenerMontosEgresoTotalMesAnual(anio);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(montos, response.getBody());
    }

    @DisplayName("Test para obtener todos los montos de egreso cuando no existen montos")
    @Test
    void testGetAllMontosEgreso_NoExistenMontos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        when(montoService.obtenerMontoEgresoTotalMesAnual(anio)).thenReturn(null);

        // When
        ResponseEntity<List<Monto>> response = montoController.obtenerMontosEgresoTotalMesAnual(anio);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

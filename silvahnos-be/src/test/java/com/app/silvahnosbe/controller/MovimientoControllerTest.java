package com.app.silvahnosbe.controller;

import static org.junit.Assert.assertEquals;
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

import com.app.silvahnosbe.controllers.MovimientoController;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MovimientoService;

@ExtendWith(MockitoExtension.class)
public class MovimientoControllerTest {

    @InjectMocks
    private MovimientoController movimientoController;

    @Mock
    private MovimientoService movimientoService;

    @DisplayName("Test para obtener todos los movimientos")
    @Test
    void testGetAllMovimientos_ExistenMovimientos_ReturnsList() {
        // Given
        List<MovimientoEntity> movimientos = new ArrayList<>();
        movimientos.add(new MovimientoEntity());
        when(movimientoService.obtenerMovimientos()).thenReturn(movimientos);

        // When
        ResponseEntity<List<MovimientoEntity>> response = movimientoController.getMovimientos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimientos, response.getBody());
    }

    @DisplayName("Test para obtener todos los movimientos cuando no existen movimientos")
    @Test
    void testGetAllMovimientos_NoExistenMovimientos_ReturnsNotFound() {
        // Given
        when(movimientoService.obtenerMovimientos()).thenReturn(null);

        // When
        ResponseEntity<List<MovimientoEntity>> response = movimientoController.getMovimientos();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test prara crear movimiento")
    @Test
    void testCreateMovimiento_CrearMovimiento_ReturnsMovimiento() {
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();
        when(movimientoService.guardarMovimiento(movimiento)).thenReturn(movimiento);

        // When
        ResponseEntity<MovimientoEntity> response = movimientoController.createMovimiento(movimiento);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimiento, response.getBody());
    }

    

    
}

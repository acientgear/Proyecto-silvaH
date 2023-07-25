package com.app.silvahnosbe.controller;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.EstadoController;
import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.services.EstadoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EstadoControllerTest {
    
    @InjectMocks
    private EstadoController estadoController;

    @Mock
    private EstadoService estadoService;

    @DisplayName("Test para obtener todos los estados")
    @Test
    void testGetaAllEstados_ExistenEstados_ReturnsList() {
        // Given
        List<EstadoEntity> estados = new ArrayList<>();
        estados.add(new EstadoEntity());
        when(estadoService.obtenerEstado()).thenReturn(estados);

        // When
        ResponseEntity<List<EstadoEntity>> response = estadoController.getEstados();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estados, response.getBody());
    }

    @DisplayName("Test para obtener todos los estados cuando no existen estados")
    @Test
    void testGetAllEstados_NoExistenEstados_ReturnsNotFound() {
        // Given
        when(estadoService.obtenerEstado()).thenReturn(null);

        // When
        ResponseEntity<List<EstadoEntity>> response = estadoController.getEstados();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener un estado por id")
    @Test
    void testGetEstadoById_ExistenEstado_ReturnsEstado() {
        // Given
        EstadoEntity estado = new EstadoEntity();
        when(estadoService.obtenerEstadoPorId(1L)).thenReturn(estado);

        // When
        ResponseEntity<EstadoEntity> response = estadoController.getEstado(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estado, response.getBody());
    }

    @DisplayName("Test para obtener un estado por id cuando no existe")
    @Test
    void testGetEstadoById_NoExistenEstado_ReturnsNotFound() {
        // Given
        when(estadoService.obtenerEstadoPorId(1L)).thenReturn(null);

        // When
        ResponseEntity<EstadoEntity> response = estadoController.getEstado(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

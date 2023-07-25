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

import com.app.silvahnosbe.controllers.RegistroController;
import com.app.silvahnosbe.models.Registro;
import com.app.silvahnosbe.services.RegistroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistroControllerTest {
    
    @InjectMocks
    private RegistroController registroController;

    @Mock
    private RegistroService registroService;

    @DisplayName("Test para obtener todos los registros")
    @Test
    void testGetAllRegistros_ExistenRegistros_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<Registro> registros = new ArrayList<>();
        registros.add(new Registro());
        when(registroService.obtenerRegistros(anio, mes)).thenReturn(registros);

        // When
        ResponseEntity<List<Registro>> response = registroController.getRegistroByAnioAndMes(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(registros, response.getBody());
    }

    @DisplayName("Test para obtener todos los registros cuando no existen registros")
    @Test
    void testGetAllRegistros_NoExistenRegistros_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(registroService.obtenerRegistros(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<Registro>> response = registroController.getRegistroByAnioAndMes(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

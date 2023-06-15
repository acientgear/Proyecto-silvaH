package com.app.silvahnosbe.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.ParametroController;
import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.services.ParametroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ParametroControllerTest {

    @InjectMocks
    private ParametroController parametroController;

    @Mock
    private ParametroService parametroService;

    @DisplayName("Test para obtener todos los parametros")
    @Test
    void testGetAllParametros_ExistenParametros_ReturnsList() {
        // Given
        List<ParametroEntity> parametros = new ArrayList<>();
        parametros.add(new ParametroEntity());
        when(parametroService.obtenerParametros()).thenReturn(parametros);

        // When
        ResponseEntity<List<ParametroEntity>> response = parametroController.getParametros();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parametros, response.getBody());
    }

    @DisplayName("Test para obtener todos los parametros cuando no existen parametros")

    @Test
    void testGetAllParametros_NoExistenParametros_ReturnsNotFound() {
        // Given
        when(parametroService.obtenerParametros()).thenReturn(null);

        // When
        ResponseEntity<List<ParametroEntity>> response = parametroController.getParametros();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear un parametro")
    @Test
    void testCreateParametro_CreaParametro_ReturnsParametro() {
        // Given
        ParametroEntity parametro = new ParametroEntity();
        parametro.setId(1L);
        parametro.setValor("valor1");
        when(parametroService.guardarParametro(parametro)).thenReturn(parametro);

        // When
        ResponseEntity<ParametroEntity> response = parametroController.createParametro(parametro);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parametro, response.getBody());
    }

    @DisplayName("Test para actualizar un parametro")
    @Test
    void testActualizarParametro_ActualizaParametro_ReturnsParametro() {
        // Given
        ParametroEntity parametro = new ParametroEntity();
        parametro.setId(1L);
        parametro.setValor("valor1");
        when(parametroService.actualizarParametro("valor2")).thenReturn(parametro);

        // When
        ResponseEntity<ParametroEntity> response = parametroController.actualizarParametro("valor2");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parametro, response.getBody());
    }

}

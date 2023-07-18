package com.app.silvahnosbe.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.ParametroController;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.ParametroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ParametroControllerTest {

    @InjectMocks
    private ParametroController parametroController;

    @Mock
    private ParametroService parametroService;

    @Mock
    private MovimientoService movimientoService;

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

    @DisplayName("Test para actualizar Parametro")
    @Test
    public void testActualizarParametro_ParametroActualizado_ReturnsParametroActualizado() {
        // Given
        String nuevoParametro = "nuevoParametro";
        ParametroEntity parametro = new ParametroEntity();
        parametro.setValor(nuevoParametro);
        when(parametroService.actualizarParametro(nuevoParametro)).thenReturn(parametro);

        // When
        ResponseEntity<ParametroEntity> response = parametroController.actualizarParametro(nuevoParametro);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(parametro, response.getBody());

        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("parametro", capturedMovimiento.getNombre_tabla());
        assertEquals(parametro.getId(), capturedMovimiento.getId_objeto());
    }

}

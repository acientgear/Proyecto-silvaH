package com.app.silvahnosbe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.CorreoController;
import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.CorreoService;
import com.app.silvahnosbe.services.MovimientoService;

@ExtendWith(MockitoExtension.class)
class CorreoControllerTest {

    @Mock
    private MovimientoService movimientoService;
    
    @InjectMocks
    private CorreoController correoController;

    @Mock
    private CorreoService correoService;

    @DisplayName("Test para obtener todos los correos")
    @Test
    void testGetAllCorreos_ExistenCorreos_ReturnsList() {
        // Given
        List<CorreoEntity> correos = new ArrayList<>();
        correos.add(new CorreoEntity());
        when(correoService.obtenerCorreos()).thenReturn(correos);

        // When
        ResponseEntity<List<CorreoEntity>> response = correoController.getCorreos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(correos, response.getBody());
    }

    @DisplayName("Test para obtener todos los correos cuando no existen rreos")
    @Test
    void testGetAllCorreos_NoExistenCorreos_ReturnsNotFound() {
        // Given
        when(correoService.obtenerCorreos()).thenReturn(null);

        // When
        ResponseEntity<List<CorreoEntity>> response = correoController.getCorreos();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para actualizar correo")
    @Test
    void testActualizarCorreo_CorreoActualizado_ReturnsCorreoActualizado() {
        // Given
        String nuevoCorreo = "nuevoCorreo";
        CorreoEntity correo = new CorreoEntity();
        correo.setDireccion(nuevoCorreo);
        when(correoService.actualizarCorreo(nuevoCorreo)).thenReturn(correo);

        // When
        ResponseEntity<CorreoEntity> response = correoController.actualizarCorreo(nuevoCorreo);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(correo, response.getBody());

        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("correo", capturedMovimiento.getNombre_tabla());
        assertEquals(correo.getId(), capturedMovimiento.getId_objeto());
    }

}

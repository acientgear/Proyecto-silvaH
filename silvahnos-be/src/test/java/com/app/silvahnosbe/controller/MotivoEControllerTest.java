package com.app.silvahnosbe.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.MotivoEController;
import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MotivoEService;
import com.app.silvahnosbe.services.MovimientoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MotivoEControllerTest {

    @InjectMocks
    private MotivoEController motivoEController;

    @Mock
    private MotivoEService motivoEService;

    @Mock
    private MovimientoService movimientoService;

    @DisplayName("Test para obtener todos los motivosE")
    @Test
    void testGetAllMotivosE_ExistenMotivosE_ReturnsList() {
        // Given
        List<MotivoEEntity> motivosE = new ArrayList<>();
        motivosE.add(new MotivoEEntity());
        when(motivoEService.obtenerMotivoE()).thenReturn(motivosE);

        // When
        ResponseEntity<List<MotivoEEntity>> response = motivoEController.getMotivosE();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivosE, response.getBody());
    }

    @DisplayName("Test para obtener todos los motivosE cuando no existen motivosE")
    @Test
    void testGetAllMotivosE_NoExistenMotivosE_ReturnsNotFound() {
        // Given
        when(motivoEService.obtenerMotivoE()).thenReturn(null);

        // When
        ResponseEntity<List<MotivoEEntity>> response = motivoEController.getMotivosE();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener un motivoE por id")
    @Test
    void testGetMotivoEById_ExistenMotivoE_ReturnsMotivoE() {
        // Given
        Long id = 1L;
        MotivoEEntity motivoE = new MotivoEEntity();
        when(motivoEService.obtenerMotivoEPorId(id)).thenReturn(motivoE);

        // When
        ResponseEntity<MotivoEEntity> response = motivoEController.getMotivoEById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoE, response.getBody());
    }

    @DisplayName("Test para obtener un motivoE por id cuando no existe el motivoE")
    @Test
    void testGetMotivoEById_NoExistenMotivoE_ReturnsNotFound() {
        // Given
        Long id = 1L;
        when(motivoEService.obtenerMotivoEPorId(id)).thenReturn(null);

        // When
        ResponseEntity<MotivoEEntity> response = motivoEController.getMotivoEById(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear una MotivoE con tipo creación")
    @Test
    void testCreateMotivoE_MotivoECreada_ReturnsMotivoE_Creación() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();
        motivoE.setId(null);
        when(motivoEService.guardarMotivoE(motivoE)).thenReturn(motivoE);

        // When
        ResponseEntity<MotivoEEntity> response = motivoEController.createMotivoE(motivoE);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoE, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Creación", capturedMovimiento.getTipo());
        assertEquals("motivoE", capturedMovimiento.getNombre_tabla());
        assertEquals(motivoE.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una MotivoE con tipo Modificación")
    @Test
    void testCreateMotivoE_MotivoECreada_ReturnsMotivoE_Modificación() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();
        motivoE.setId(1l);
        when(motivoEService.guardarMotivoE(motivoE)).thenReturn(motivoE);

        // When
        ResponseEntity<MotivoEEntity> response = motivoEController.createMotivoE(motivoE);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoE, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("motivoE", capturedMovimiento.getNombre_tabla());
        assertEquals(motivoE.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una MotivoE con tipo Eliminación")
    @Test
    void testCreateMotivoE_MotivoECreada_ReturnsMotivoE_Eliminación() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();
        motivoE.setId(1l);
        motivoE.setBorrado(true);
        when(motivoEService.guardarMotivoE(motivoE)).thenReturn(motivoE);

        // When
        ResponseEntity<MotivoEEntity> response = motivoEController.createMotivoE(motivoE);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoE, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Eliminación", capturedMovimiento.getTipo());
        assertEquals("motivoE", capturedMovimiento.getNombre_tabla());
        assertEquals(motivoE.getId(), capturedMovimiento.getId_objeto());
    }
    
}

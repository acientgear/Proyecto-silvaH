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

import com.app.silvahnosbe.controllers.MotivoEController;
import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.services.MotivoEService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MotivoEControllerTest {

    @InjectMocks
    private MotivoEController motivoEController;

    @Mock
    private MotivoEService motivoEService;

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

    @DisplayName("Test para crear un motivoE")
    @Test
    void testCreateMotivoE_CreaMotivoE_ReturnsMotivoE() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();
        when(motivoEService.guardarMotivoE(motivoE)).thenReturn(motivoE);

        // When
        ResponseEntity<MotivoEEntity> response = motivoEController.createMotivoE(motivoE);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoE, response.getBody());
    }
    
}

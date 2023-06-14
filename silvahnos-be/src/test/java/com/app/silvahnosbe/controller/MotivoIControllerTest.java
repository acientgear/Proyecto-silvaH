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

import com.app.silvahnosbe.controllers.MotivoIController;
import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.services.MotivoIService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MotivoIControllerTest {

    @InjectMocks
    private MotivoIController motivoIController;

    @Mock
    private MotivoIService motivoIService;

    @DisplayName("Test para obtener todos los motivosE")
    @Test
    void testGetAllMotivosE_ExistenMotivosE_ReturnsList() {
        // Given
        List<MotivoIEntity> motivosE = new ArrayList<>();
        motivosE.add(new MotivoIEntity());
        when(motivoIService.obtenerMotivoI()).thenReturn(motivosE);

        // When
        ResponseEntity<List<MotivoIEntity>> response = motivoIController.getMotivosI();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivosE, response.getBody());
    }

    @DisplayName("Test para obtener todos los motivosE cuando no existen motivosE")
    @Test
    void testGetAllMotivosE_NoExistenMotivosE_ReturnsNotFound() {
        // Given
        when(motivoIService.obtenerMotivoI()).thenReturn(null);

        // When
        ResponseEntity<List<MotivoIEntity>> response = motivoIController.getMotivosI();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener un motivoI por id")

    @Test
    void testGetMotivoIById_ExistenMotivoI_ReturnsMotivoI() {
        // Given
        Long id = 1L;
        MotivoIEntity motivoI = new MotivoIEntity();
        when(motivoIService.obtenerMotivoIPorId(id)).thenReturn(motivoI);

        // When
        ResponseEntity<MotivoIEntity> response = motivoIController.getMotivoIById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoI, response.getBody());
    }

    @DisplayName("Test para obtener un motivoI por id cuando no existe el motivoI")
    @Test
    void testGetMotivoIById_NoExistenMotivoI_ReturnsNotFound() {
        // Given
        Long id = 1L;
        when(motivoIService.obtenerMotivoIPorId(id)).thenReturn(null);

        // When
        ResponseEntity<MotivoIEntity> response = motivoIController.getMotivoIById(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear un motivoI")
    @Test
    void testCreateMotivoI_CreaMotivoI_ReturnsMotivoI() {
        // Given
        MotivoIEntity motivoI = new MotivoIEntity();
        when(motivoIService.guardarMotivoI(motivoI)).thenReturn(motivoI);

        // When
        ResponseEntity<MotivoIEntity> response = motivoIController.createMotivoI(motivoI);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(motivoI, response.getBody());
    }
    
}

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

import com.app.silvahnosbe.controllers.EgresoController;
import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.services.EgresoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EgresoControllerTest {
    
    @InjectMocks
    private EgresoController egresoController;

    @Mock
    private EgresoService egresoService;

    @DisplayName("Test para obtener todos los egresos")
    @Test
    void testGetAllEgresos_ExistenEgresos_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<EgresoEntity> egresos = new ArrayList<>();
        egresos.add(new EgresoEntity());
        when(egresoService.obtenerEgresoPorAnioAndMes(anio, mes)).thenReturn(egresos);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getEgresoByAnioAndMes(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egresos, response.getBody());
    }

    @DisplayName("Test para obtener todos los egresos cuando no existen egresos")
    @Test
    void testGetAllEgresos_NoExistenEgresos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(egresoService.obtenerEgresoPorAnioAndMes(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getEgresoByAnioAndMes(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear un egreso")
    @Test
    void testCreateEgreso_EgresoCreado_ReturnsEgreso() {
        // Given
        EgresoEntity egreso = new EgresoEntity();
        when(egresoService.guardarEgreso(egreso)).thenReturn(egreso);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.createEgreso(egreso);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egreso, response.getBody());
    }

    @DisplayName("Test para eliminar un egreso")
    @Test
    void testDeleteEgreso_EgresoEliminado_ReturnsOk() {
        // Given
        Long id = 1L;
        EgresoEntity egreso = new EgresoEntity();
        when(egresoService.obtenerEgresoPorId(id)).thenReturn(egreso);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.deleteEgreso(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Test para eliminar un egreso que no existe")
    @Test
    void testDeleteEgreso_EgresoNoExiste_ReturnsNotFound() {
        // Given
        Long id = 1L;
        when(egresoService.obtenerEgresoPorId(id)).thenReturn(null);

        // When
        ResponseEntity<EgresoEntity> response = egresoController.deleteEgreso(id);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener los ultimos egresos")
    @Test
    void testGetUltimosEgresos_ExistenEgresos_ReturnsList() {
        // Given
        List<EgresoEntity> egresos = new ArrayList<>();
        egresos.add(new EgresoEntity());
        when(egresoService.obtenerUltimosEgresos()).thenReturn(egresos);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getUltimosEgresos();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(egresos, response.getBody());
    }

    @DisplayName("Test para obtener los ultimos egresos cuando no existen egresos")
    @Test
    void testGetUltimosEgresos_NoExistenEgresos_ReturnsNotFound() {
        // Given
        when(egresoService.obtenerUltimosEgresos()).thenReturn(null);

        // When
        ResponseEntity<List<EgresoEntity>> response = egresoController.getUltimosEgresos();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener el total de egresos por mes")
    @Test
    void testGetTotalEgresos_ExistenEgresos_ReturnsDouble() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        Integer total = 1000;
        when(egresoService.obtenerTotalEgresosPorMes(anio, mes)).thenReturn(total);

        // When
        ResponseEntity<Integer> response = egresoController.getTotalEgresosPorMes(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(total, response.getBody());
    }

    @DisplayName("Test para obtener el total de egresos por mes cuando no existen egresos")
    @Test
    void testGetTotalEgresos_NoExistenEgresos_ReturnsNotFound() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        when(egresoService.obtenerTotalEgresosPorMes(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<Integer> response = egresoController.getTotalEgresosPorMes(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para obtener el monto por dia")
    @Test
    void testGetMontoPorDia_ExistenEgresos_ReturnsDouble() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        Integer dia = 10;
        Integer monto = 1000;
        when(egresoService.obtenerMontoPorDia(anio, mes, dia)).thenReturn(monto);

        // When
        ResponseEntity<Integer> response = egresoController.getMontoPorDia(anio, mes, dia);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(monto, response.getBody());
    }

    @DisplayName("Test para obtener el monto por dia cuando no existen egresos")
    @Test
    void testGetMontoPorDia_NoExistenEgresos_ReturnsNotFound() {
        // Given
        Integer anio = 2023;
        Integer mes = 5;
        Integer dia = 10;
        when(egresoService.obtenerMontoPorDia(anio, mes, dia)).thenReturn(null);

        // When
        ResponseEntity<Integer> response = egresoController.getMontoPorDia(anio, mes, dia);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



}

package com.app.silvahnosbe.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.silvahnosbe.controllers.IngresoController;
import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.services.IngresoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngresoControllerTest {

    @InjectMocks
    private IngresoController ingresoController;

    @Mock
    private IngresoService ingresoService;

    @Test
    void testGetAllIngresos_ExistenIngresos_ReturnsList() {
        // Given
        int anio = 2023;
        int mes = 5;
        List<IngresoEntity> ingresos = new ArrayList<>();
        ingresos.add(new IngresoEntity());
        when(ingresoService.obtenerIngresos(anio, mes)).thenReturn(ingresos);

        // When
        ResponseEntity<List<IngresoEntity>> response = ingresoController.getAllIngresos(anio, mes);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ingresos, response.getBody());
    }

    @Test
    void testGetAllIngresos_NoExistenIngresos_ReturnsNotFound() {
        // Given
        int anio = 2023;
        int mes = 5;
        when(ingresoService.obtenerIngresos(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<IngresoEntity>> response = ingresoController.getAllIngresos(anio, mes);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

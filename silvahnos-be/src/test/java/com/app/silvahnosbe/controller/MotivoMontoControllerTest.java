package com.app.silvahnosbe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.MotivoMontoController;
import com.app.silvahnosbe.models.MotivoMonto;
import com.app.silvahnosbe.services.MotivoMontoService;

@ExtendWith(SpringExtension.class)
public class MotivoMontoControllerTest {
    @Mock
    private MotivoMontoService motivoMontoService;

    @InjectMocks
    private MotivoMontoController motivoMontoController;

    @Test
    public void testObtenerMontosIngreso_MontosExistentes() {
        // Given
        int anio = 2023;
        int mes = 7;

        // Creamos una lista de MotivoMonto de prueba
        List<MotivoMonto> montosDePrueba = new ArrayList<>();
        montosDePrueba.add(new MotivoMonto("Motivo 1", 100));
        montosDePrueba.add(new MotivoMonto("Motivo 2", 200));
        montosDePrueba.add(new MotivoMonto("Motivo 3", 300));

        // Simulamos el comportamiento del servicio para devolver los montos de prueba
        when(motivoMontoService.obtenerMontoIngreso(anio, mes)).thenReturn(montosDePrueba);

        // When
        ResponseEntity<List<MotivoMonto>> responseEntity = motivoMontoController.obtenerMontosIngreso(anio, mes);

        // Then
        // Verificamos que el servicio se haya llamado con los parámetros correctos
        verify(motivoMontoService, times(1)).obtenerMontoIngreso(anio, mes);

        // Verificamos que la respuesta sea 200 OK y contenga los montos de prueba
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(montosDePrueba, responseEntity.getBody());
    }

    @Test
    public void testObtenerMontosIngreso_MontosNoExistentes() {
        // Given
        int anio = 2023;
        int mes = 7;

        // Simulamos el comportamiento del servicio para devolver una lista vacía (montos no encontrados)
        when(motivoMontoService.obtenerMontoIngreso(anio, mes)).thenReturn(null);

        // When
        ResponseEntity<List<MotivoMonto>> responseEntity = motivoMontoController.obtenerMontosIngreso(anio, mes);

        // Then
        // Verificamos que el servicio se haya llamado con los parámetros correctos
        verify(motivoMontoService, times(1)).obtenerMontoIngreso(anio, mes);

        // Verificamos que la respuesta sea 404 Not Found
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}

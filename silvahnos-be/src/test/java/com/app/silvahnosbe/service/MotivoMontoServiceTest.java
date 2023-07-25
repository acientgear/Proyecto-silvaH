package com.app.silvahnosbe.service;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.models.MotivoMonto;
import com.app.silvahnosbe.repositories.MotivoMontoRepository;
import com.app.silvahnosbe.services.MotivoMontoService;

@ExtendWith(SpringExtension.class)
public class MotivoMontoServiceTest {
    @Mock
    private MotivoMontoRepository motivoMontoRepository;

    @InjectMocks
    private MotivoMontoService motivoMontoService;

    @Test
    public void testObtenerMontoIngreso() {
        // Given
        int anio = 2023;
        int mes = 7;

        // Creamos una lista de MotivoMonto de prueba
        List<MotivoMonto> montosDePrueba = new ArrayList<>();
        montosDePrueba.add(new MotivoMonto("Motivo 1", 100));
        montosDePrueba.add(new MotivoMonto("Motivo 2", 200));
        montosDePrueba.add(new MotivoMonto("Motivo 3", 300));

        // Simulamos el comportamiento del repositorio para devolver los montos de prueba
        when(motivoMontoRepository.obtenerMontoIngresoPorAnioAndMes(anio, mes)).thenReturn(montosDePrueba);

        // When
        List<MotivoMonto> montosObtenidos = motivoMontoService.obtenerMontoIngreso(anio, mes);

        // Then
        // Verificamos que el método del repositorio se haya llamado con los parámetros correctos
        verify(motivoMontoRepository, times(1)).obtenerMontoIngresoPorAnioAndMes(anio, mes);

        // Verificamos que los montos obtenidos sean los mismos que los montos de prueba
        assertEquals(montosDePrueba, montosObtenidos);
    }

    @Test
    public void testObtenerMontoEgreso() {
        // Given
        int anio = 2023;
        int mes = 7;

        // Creamos una lista de MotivoMonto de prueba
        List<MotivoMonto> montosDePrueba = new ArrayList<>();
        montosDePrueba.add(new MotivoMonto("Motivo 1", 100));
        montosDePrueba.add(new MotivoMonto("Motivo 2", 200));
        montosDePrueba.add(new MotivoMonto("Motivo 3", 300));

        // Simulamos el comportamiento del repositorio para devolver los montos de prueba
        when(motivoMontoRepository.obtenerMontoEgresoPorAnioAndMes(anio, mes)).thenReturn(montosDePrueba);

        // When
        List<MotivoMonto> montosObtenidos = motivoMontoService.obtenerMontoEgreso(anio, mes);

        // Then
        // Verificamos que el método del repositorio se haya llamado con los parámetros correctos
        verify(motivoMontoRepository, times(1)).obtenerMontoEgresoPorAnioAndMes(anio, mes);

        // Verificamos que los montos obtenidos sean los mismos que los montos de prueba
        assertEquals(montosDePrueba, montosObtenidos);
    }
}

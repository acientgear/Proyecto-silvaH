package com.app.silvahnosbe.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;
import com.app.silvahnosbe.services.EgresoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class EgresoServiceTest {

    @Mock
    private EgresoRepository egresoRepository;

    @InjectMocks
    private EgresoService egresoService;

    Timestamp fecha = new Timestamp(System.currentTimeMillis());
    Timestamp fecha1 = new Timestamp(System.currentTimeMillis());
    EgresoEntity egreso;

    @BeforeEach
    void setup() {
        egreso = new EgresoEntity();
        egreso.setDescripcion("pintura");
        egreso.setId(1l);
        egreso.setMonto(15000);
        egreso.setBorrado(false);
        egreso.setFecha_creacion(fecha);
        egresoService.guardarEgreso(egreso);
    }

    @DisplayName("test para guardar un egreso")
    @Test
    void testGuardarEgreso() {

        // given
        given(egresoRepository.save(egreso)).willReturn(egreso);
        // when
        EgresoEntity egreso1 = egresoService.guardarEgreso(egreso);
        // then
        assertThat(egreso1).isNotNull();
    }

    @DisplayName("test para  buscar un egreso")
    @Test
    void TestObtenerEgreso() {
        // given
        given(egresoRepository.findById(1l)).willReturn(Optional.of(egreso));
        // when
        EgresoEntity egreso1 = egresoService.obtenerEgresoPorId(egreso.getId());
        // then
        assertThat(egreso1.getId()).isEqualTo(1l);
    }

    @DisplayName("test para eliminar un egreso")
    @Test
    void TestEliminarEgreso() {
        long egresoId = 1l;
        // given
        willDoNothing().given(egresoRepository).deleteById(egresoId);
        // when
        egresoService.eliminarEgreso(egreso);
        // then
        verify(egresoRepository, times(1)).deleteById(egresoId);
    }

    @DisplayName("test para buscar un egreso por mes año")
    @Test
    void TestMY() {

        // given
        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setDescripcion("pintura");
        egreso2.setId(2l);
        egreso2.setMonto(15000);
        egreso2.setFecha_creacion(fecha1);

        given(egresoRepository.obtenerEgresosPorAnioAndMes(2023, 3)).willReturn(List.of(egreso, egreso2));
        // when
        List<EgresoEntity> egresos = egresoService.obtenerEgresoPorAnioAndMes(2023, 3);
        // then
        assertThat(egresos.size()).isEqualTo(2);
    }

    @DisplayName("test para lista vacia de egresos")
    @Test
    void testListaVaciaEgreso() {
        // given
        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setDescripcion("pintura");
        egreso2.setId(2l);
        egreso2.setMonto(15000);
        given(egresoRepository.obtenerEgresosPorAnioAndMes(2023, 3)).willReturn(Collections.emptyList());
        // when
        List<EgresoEntity> egresos = egresoService.obtenerEgresoPorAnioAndMes(2023, 3);
        // then
        assertThat(egresos.size()).isEqualTo(0);
    }

    @DisplayName("test para obtener ultimos egresos")
    @Test
    void TestUltimoEgreso() {
        // given
        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setDescripcion("pintura");
        egreso2.setId(2l);
        egreso2.setMonto(15000);

        EgresoEntity egreso1 = new EgresoEntity();
        egreso1.setDescripcion("pintura");
        egreso1.setId(2l);
        egreso1.setMonto(15000);
        given(egresoRepository.obtenerUltimosEgresos()).willReturn(List.of(egreso, egreso1, egreso2));

        // when
        List<EgresoEntity> egresos = egresoService.obtenerUltimosEgresos();
        // then
        assertThat(egresos.size()).isLessThan(4);

    }

    @DisplayName("test para obtener el total de los egresos")
    @Test
    void TestTotalEgreso() {
        // given
        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setDescripcion("pintura");
        egreso2.setId(2l);
        egreso2.setMonto(15000);

        EgresoEntity egreso1 = new EgresoEntity();
        egreso1.setDescripcion("pintura");
        egreso1.setId(2l);
        egreso1.setMonto(15000);
        given(egresoRepository.obtenerEgresosPorAnioAndMes(2023, 3)).willReturn(List.of(egreso, egreso1, egreso2));

        // when
        int egresos = egresoService.obtenerTotalEgresosPorMes(2023, 3);
        // then
        assertThat(egresos).isEqualTo(45000);

    }

    @Test
    @DisplayName("test de integración creación de egreso con motivo y movimiento")
    void testCrearEgreso() {
        // given
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCorreo("correo1@gmail.com");
        usuario.setContrasenna("pass1");
        usuario.setUsuario("usuario1");

        MotivoEEntity motivo = new MotivoEEntity();
        motivo.setId(1l);
        motivo.setNombre("motivo1");
        motivo.setDescripcion("descripcion1");
        motivo.setBorrado(false);

        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(1l);
        egreso.setMonto(15000);
        egreso.setDescripcion("pintura");
        egreso.setBorrado(false);
        egreso.setMotivo(motivo);
        egreso.setFecha_creacion(fecha);
        egresoRepository.save(egreso);
        given(egresoRepository.save(egreso)).willReturn(egreso);

        // when
        EgresoEntity egreso1 = egresoService.guardarEgreso(egreso);

        // then
        assertThat(egreso1.getId()).isEqualTo(1l);
        assertThat(egreso1.getMotivo().getId()).isEqualTo(1l);
    }

        @Test
    void testEsBisiesto_AnioNoBisiesto_ReturnsFalse() {
        // Given
        int anio = 2023;

        // When
        boolean result = EgresoService.esBisiesto(anio);

        // Then
        assertFalse(result);
    }

    @Test
    void testEsBisiesto_AnioBisiestoDivisiblePor4_ReturnsTrue() {
        // Given
        int anio = 2024;

        // When
        boolean result = EgresoService.esBisiesto(anio);

        // Then
        assertTrue(result);
    }

    @Test
    void testEsBisiesto_AnioBisiestoDivisiblePor100_ReturnsFalse() {
        // Given
        int anio = 1900;

        // When
        boolean result = EgresoService.esBisiesto(anio);

        // Then
        assertFalse(result);
    }

    @Test
    void testEsBisiesto_AnioBisiestoDivisiblePor400_ReturnsTrue() {
        // Given
        int anio = 2000;

        // When
        boolean result = EgresoService.esBisiesto(anio);

        // Then
        assertTrue(result);
    }

        @Test
    void testObtenerDiasMes_FebreroAnioBisiesto_Returns29() {
        // Given
        int mes = 2;
        int anio = 2024;

        // When
        int result = EgresoService.obtenerDiasMes(mes, anio);

        // Then
        assertEquals(29, result);
    }

    @Test
    void testObtenerDiasMes_MesesCon31Dias_Returns31() {
        // Given
        int[] mesesCon31Dias = {1, 3, 5, 7, 8, 10, 12};

        // When
        for (int mes : mesesCon31Dias) {
            int result = EgresoService.obtenerDiasMes(mes, 2023);
            assertEquals(31, result);
        }
    }

    @Test
    void testObtenerDiasMes_MesesCon30Dias_Returns30() {
        // Given
        int[] mesesCon30Dias = {4, 6, 9, 11};

        // When
        for (int mes : mesesCon30Dias) {
            int result = EgresoService.obtenerDiasMes(mes, 2023);
            assertEquals(30, result);
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMontosPorDia() {
        // Given
        int anio = 2023;
        int mes = 7;
        int numeroDias = 31;
        List<Integer> expectedMontos = new ArrayList<>();
        for (int i = 1; i <= numeroDias; i++) {
            // Add some dummy data for the montos list
            expectedMontos.add(i * 100);
            // Stub the ingresoRepository.obtenerMontoPorDia method
            when(egresoRepository.obtenerMontoPorDia(anio, mes, i)).thenReturn(i * 100);
        }

        // When
        List<Integer> result = egresoService.getMontosPorDia(anio, mes);

        // Then
        assertEquals(expectedMontos, result);
    }

    @Test
    void testGetMontosUltimos5Dias() {
        // Given
        List<Integer> expectedMontos = new ArrayList<>();
        expectedMontos.add(100); // Dummy data for the first day
        expectedMontos.add(200); // Dummy data for the second day
        expectedMontos.add(300); // Dummy data for the third day
        expectedMontos.add(400); // Dummy data for the fourth day
        expectedMontos.add(500); // Dummy data for the fifth day

        // Mock the behavior of egresoRepository.obtenerMontoPorDia
        when(egresoRepository.obtenerMontoPorDia(anyInt(), anyInt(), anyInt()))
                .thenReturn(100, 200, 300, 400, 500);

        // When
        List<Integer> actualMontos = egresoService.getMontosUltimos5Dias();

        // Then
        assertEquals(expectedMontos, actualMontos);
        // You can add more specific assertions as per your requirements
    }

}

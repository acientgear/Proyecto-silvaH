package com.app.silvahnosbe.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;
import com.app.silvahnosbe.services.EgresoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EgresoServiceTest {

    @Mock
    private EgresoRepository egresoRepository;

    @InjectMocks
    private EgresoService egresoService;

    Date fecha = new Date();
    Date fecha1 = new Date();
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

    @DisplayName("test para  buscar un ingreso")
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
        assertThat(egresos).isNotNull();
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
        assertThat(egresos).isEmpty();
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
        assertThat(egresos).isNotNull();
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
        assertThat(egresos).isNotNull();
        assertThat(egresos).isEqualTo(45000);

    }

    @DisplayName("test para obtener el total de los egresos del dia")
    @Test
    void TestTotalDiaEgreso() {
        // given
        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setDescripcion("pintura");
        egreso2.setId(2l);
        egreso2.setMonto(15000);

        EgresoEntity egreso1 = new EgresoEntity();
        egreso1.setDescripcion("pintura");
        egreso1.setId(3l);
        egreso1.setMonto(15000);

        given(egresoRepository.obtenerMontoPorDia(2023, 3, 1)).willReturn(30000);

        // when
        int egresos = egresoService.obtenerMontoPorDia(2023, 3, 1);
        // then
        assertThat(egresos).isNotNull();
        assertThat(egresos).isEqualTo(30000);

    }

}

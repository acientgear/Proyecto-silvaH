package com.app.silvahnosbe.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.repositories.EstadoRepository;
import com.app.silvahnosbe.services.EstadoService;

@ExtendWith(SpringExtension.class)
public class EstadoServiceTest {

    @Mock
    private EstadoRepository estadoRepository;

    @InjectMocks
    private EstadoService estadoService;

    @DisplayName("Test para obtener todos los estados cuando existen estados")
    @Test
    void testObtenerEstados_ExistenEstados() {
        // Given
        EstadoEntity estado = new EstadoEntity();
        estado.setNombre("Estado 1");
        ArrayList<EstadoEntity> estados = new ArrayList<>();
        estados.add(estado);

        given(estadoRepository.findAll()).willReturn(estados);

        // When
        List<EstadoEntity> resultado = estadoService.obtenerEstado();

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Estado 1");
    }

    @DisplayName("test para obtener todos los estados cuando no existen estados")
    @Test
    void testObtenerEstados_NoExistenEstados() {
        given(estadoRepository.findAll()).willReturn(null);
        assertThat(estadoService.obtenerEstado()).isNull();
    }

    @DisplayName("test para obtener un estado por id")
    @Test
    void testObtenerEstadoPorId() {
        EstadoEntity estado = new EstadoEntity();
        estado.setNombre("Estado 1");
        estado.setId(1L);

        given(estadoRepository.findById(1L)).willReturn(java.util.Optional.of(estado));
        assertThat(estadoService.obtenerEstadoPorId(1L)).isNotNull();
    }

    @DisplayName("test para obtener un estado por id cuando no existe")
    @Test
    void testObtenerEstadoPorId_NoExiste() {
        given(estadoRepository.findById(1L)).willReturn(null);
        assertThat(estadoService.obtenerEstadoPorId(1L)).isNull();
    }

}

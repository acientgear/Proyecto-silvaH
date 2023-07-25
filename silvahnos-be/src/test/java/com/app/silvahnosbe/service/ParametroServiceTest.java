package com.app.silvahnosbe.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.repositories.ParametroRepository;
import com.app.silvahnosbe.services.ParametroService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ParametroServiceTest {
    @Mock
    private ParametroRepository parametroRepository;

    @InjectMocks
    private ParametroService parametroService;

    ParametroEntity parametro = new ParametroEntity();

    @DisplayName("test para obtener todos los parametros cuando existen")
    @Test
    void testObtenerParametros() {
        given(parametroRepository.findAll()).willReturn(java.util.Arrays.asList(parametro));
        assertThat(parametroService.obtenerParametros()).isNotNull();
    }

    @DisplayName("test para obtener todos los parametros cuando no existen")
    @Test
    void testObtenerParametrosNoExisten() {
        given(parametroRepository.findAll()).willReturn(null);
        assertThat(parametroService.obtenerParametros()).isNull();
    }

    @DisplayName("test para obtener un parametro por id")
    @Test
    void testObtenerParametroPorId() {
        given(parametroRepository.findById(1L)).willReturn(java.util.Optional.of(parametro));
        assertThat(parametroService.obtenerParametroPorId(1L)).isNotNull();
    }

    @DisplayName("test para guardar un parametro")
    @Test
    void testGuardarParametro() {
        given(parametroRepository.save(parametro)).willReturn(parametro);
        assertThat(parametroService.guardarParametro(parametro)).isNotNull();
    }

    @DisplayName("test para actualizar un parametro")
    @Test
    void testActualizarParametro() {
        given(parametroRepository.findById(1L)).willReturn(java.util.Optional.of(parametro));
        given(parametroRepository.save(parametro)).willReturn(parametro);
        assertThat(parametroService.actualizarParametro("nuevoValor")).isNotNull();
    }
    
}

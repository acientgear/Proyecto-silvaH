package com.app.silvahnosbe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.repositories.CorreoRepository;
import com.app.silvahnosbe.services.CorreoService;

@ExtendWith(MockitoExtension.class)
class CorreoServiceTest {

    @InjectMocks
    private CorreoService correoService;

    @Mock
    private CorreoRepository correoRepository;

    CorreoEntity correo;

    @BeforeEach
    void setup() {
        correo = new CorreoEntity();
        correo.setId(1l);
        correo.setDireccion("luis@gmail.com");
    }

    @DisplayName("test para obtener un correo por id")
    @Test
    void testObtenerCorreoPorId() {
    
        given(correoRepository.findById(1L)).willReturn(java.util.Optional.of(correo));
        assertThat(correoService.obtenerCorreoPorId(1L)).isNotNull();
    }

    @DisplayName("test para actualizar correo")
    @Test
    void testActualizarCorreo() {
        given(correoRepository.findById(1L)).willReturn(java.util.Optional.of(correo));
        CorreoEntity correo1 = correoService.actualizarCorreo("leo@usach.cl");
        System.out.println(correo1);
        assertThat(correo1).isNotNull();
    }

    @DisplayName("test para obtener todos los correos cuando existen")
    @Test
    void testObtenerCorreos() {
        given(correoRepository.findAll()).willReturn(java.util.Arrays.asList(correo));
        assertThat(correoService.obtenerCorreos()).isNotNull();
    }

    @DisplayName("test para obtener todos los correos cuando no existen")
    @Test
    void testObtenerCorreosVacio() {
        given(correoRepository.findAll()).willReturn(java.util.Arrays.asList());
        assertThat(correoService.obtenerCorreos()).isEmpty();
    }

}

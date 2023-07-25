package com.app.silvahnosbe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.repositories.MotivoERepository;
import com.app.silvahnosbe.services.MotivoEService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MotivoEServiceTest {

    @Mock
    private MotivoERepository motivoERepository;

    @InjectMocks
    private MotivoEService motivoEService;
    
    MotivoEEntity motivoE = new MotivoEEntity();

    @BeforeEach
    void setup() {
        motivoE.setId(1l);
        motivoE.setNombre("Sueldos");
        motivoE.setBorrado(false);
        motivoE.setDescripcion("Pago de sueldos");
        motivoEService.guardarMotivoE(motivoE);
    }

    @DisplayName("test para guardar un motivoE")
    @Test
    void testGuardarMotivoE() {
        // given
        given(motivoERepository.save(motivoE)).willReturn(motivoE);
        // when
        MotivoEEntity motivoE1 = motivoEService.guardarMotivoE(motivoE);
        // then
        assertThat(motivoE1).isNotNull();
    }

    @DisplayName("test para obtener un motivoE por id")
    @Test
    void testObtenerMotivoEPorId() {
        // given
        given(motivoERepository.findById(1l)).willReturn(java.util.Optional.of(motivoE));
        // when
        MotivoEEntity motivoE1 = motivoEService.obtenerMotivoEPorId(1l);
        // then
        assertThat(motivoE1).isNotNull();
    }

    @DisplayName("test para obtener todos los motivosE cuando existen")
    @Test
    void testObtenerMotivoE() {
        // given
        List<MotivoEEntity> motivosE = List.of(motivoE);
        given(motivoERepository.obtenerMotivosE()).willReturn(motivosE);
    
        // when
        List<MotivoEEntity> motivoE1 = motivoEService.obtenerMotivoE();
    
        // then
        assertThat(motivoE1).isNotNull();
        assertThat(motivoE1).isEqualTo(motivosE);
    }

    @DisplayName("test para obtener todos los motivosE cuando no existen")
    @Test
    void testObtenerMotivoE_NoExistenMotivosE_ReturnsNull() {
        // when
        MotivoEEntity motivoE1 = motivoEService.obtenerMotivoEPorId(1l);
        // then
        assertThat(motivoE1).isNull();
    }
    
}

package com.app.silvahnosbe.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.repositories.MotivoIRepository;
import com.app.silvahnosbe.services.MotivoIService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MotivoIServiceTest {
    
    @Mock
    private MotivoIRepository motivoIRepository;

    @InjectMocks
    private MotivoIService motivoIService;

    MotivoIEntity motivoI = new MotivoIEntity();

    @BeforeEach
    void setup() {
        motivoI.setId(1l);
        motivoI.setNombre("Astara");
        motivoI.setBorrado(false);
        motivoI.setDescripcion("Automotora 1");
        motivoIService.guardarMotivoI(motivoI);
    }

    @DisplayName("test para guardar un motivoI")
    @Test
    void testGuardarMotivoI() {
        // given
        given(motivoIRepository.save(motivoI)).willReturn(motivoI);
        // when
        MotivoIEntity motivoI1 = motivoIService.guardarMotivoI(motivoI);
        // then
        assertThat(motivoI1).isNotNull();
    }

    @DisplayName("test para obtener un motivoI por id")
    @Test
    void testObtenerMotivoIPorId() {
        // given
        given(motivoIRepository.findById(1l)).willReturn(java.util.Optional.of(motivoI));
        // when
        MotivoIEntity motivoI1 = motivoIService.obtenerMotivoIPorId(1l);
        // then
        assertThat(motivoI1).isNotNull();
    }

    @DisplayName("test para obtener un motivoI por id cuando no existe")
    @Test
    void testObtenerMotivoIPorIdCuandoNoExiste() {
        // given
        given(motivoIRepository.findById(1l)).willReturn(java.util.Optional.empty());
        // when
        MotivoIEntity motivoI1 = motivoIService.obtenerMotivoIPorId(1l);
        // then
        assertThat(motivoI1).isNull();
    }

    @DisplayName("test para obtener todos los motivosI cuando existen")
    @Test
    void testObtenerMotivoI() {
        // given
        List<MotivoIEntity> motivosI = List.of(motivoI);
        given(motivoIRepository.obtenerMotivosI()).willReturn(motivosI);
    
        // when
        List<MotivoIEntity> motivoI1 = motivoIService.obtenerMotivoI();
    
        // then
        assertThat(motivoI1).isNotNull();
        assertThat(motivoI1).isEqualTo(motivosI);
    }

    @DisplayName("test para obtener todos los motivosI cuando no existen")
    @Test
    void testObtenerTodosLosMotivosICuandoNoExisten() {
        // when
        List<MotivoIEntity> motivosI1 = motivoIService.obtenerMotivoI();
        // then
        assertThat(motivosI1).isEmpty();
    }






}

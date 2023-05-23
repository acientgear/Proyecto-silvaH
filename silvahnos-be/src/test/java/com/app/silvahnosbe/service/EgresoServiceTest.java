package com.app.silvahnosbe.service;


import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;
import com.app.silvahnosbe.services.EgresoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class EgresoServiceTest {

   @Mock
    private EgresoRepository egresoRepository;

   @InjectMocks
    private EgresoService egresoService;

    EgresoEntity egreso;
   @BeforeEach
   void setup(){
        egreso= new EgresoEntity();
        egreso.setDescripcion("pintura");
        egreso.setId(1l);
        egreso.setMonto(15000);
        egreso.setBorrado(false);
        egresoService.guardarEgreso(egreso);
   }
    @DisplayName("test para guardar un egreso")
    @Test
    void testGuardarEgreso (){

        //given
        given(egresoRepository.save(egreso)).willReturn(egreso);
        //when
        EgresoEntity egreso1=egresoService.guardarEgreso(egreso);
        //then
        assertThat(egreso1).isNotNull();
    }


    @DisplayName("test para listar egresos")
    @Test
    void testListarEgreso(){
       //given
        EgresoEntity egreso2 = new EgresoEntity();
        egreso2.setDescripcion("pintura");
        egreso2.setId(2l);
        egreso2.setMonto(15000);
        given(egresoRepository.obtenerEgresos()).willReturn(List.of(egreso,egreso2));
        //when
        List<EgresoEntity> egresos=egresoService.obtenerEgresos();
        // then
        assertThat(egresos).isNotNull();
        assertThat(egresos.size()).isEqualTo(2);
    }

    @DisplayName("test para  buscar un ingreso")
    @Test
    void TestObtenerEgreso(){
       //given
        given(egresoRepository.findById(1l)).willReturn(Optional.of(egreso));
        //when
        EgresoEntity egreso1=egresoService.obtenerEgresoPorId(egreso.getId());
        //then
        assertThat(egreso1.getId()).isEqualTo(1l);
    }


    @DisplayName("test para eliminar un egreso")
    @Test
    void TestEliminarEgreso(){
       long egresoId=1l;
       //given
        willDoNothing().given(egresoRepository).deleteById(egresoId);
        // when
        egresoService.eliminarEgreso(egreso);
        //then
        verify(egresoRepository,times(1)).deleteById(egresoId);
    }


    @DisplayName("test para buscar por mes/año")
    @Test
    void TestMesAnio(){

    }

    @Test
    void TestUltimosEgresos(){

    }

    @Test
    void TestMontoPorDia(){

    }





}

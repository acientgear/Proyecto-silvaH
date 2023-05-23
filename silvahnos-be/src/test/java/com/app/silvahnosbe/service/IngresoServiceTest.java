package com.app.silvahnosbe.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;
import com.app.silvahnosbe.services.IngresoService;

import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
public class IngresoServiceTest {

    @Mock
    private IngresoRepository ingresoRepository;

    @InjectMocks
    private IngresoService ingresoService;

    IngresoEntity ingreso;
    @BeforeEach
    void setup(){
        ingreso= new IngresoEntity();
        ingreso.setId(1l);
        ingreso.setMonto(150000);
        ingreso.setOrigen("ASTARA");
        ingreso.setDescripcion("Cuadratura BMW");
        ingreso.setBorrado(false);
        ingresoService.guardarIngreso(ingreso);
    }

    @DisplayName("test para guardar un ingreso")
    @Test
    void testGuardarIngreso (){

        //given
        given(ingresoRepository.save(ingreso)).willReturn(ingreso);
        //when
        IngresoEntity ingreso1=ingresoService.guardarIngreso(ingreso);
        //then
        assertThat(ingreso1).isNotNull();
    }
    
}

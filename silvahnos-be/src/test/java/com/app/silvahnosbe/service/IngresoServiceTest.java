package com.app.silvahnosbe.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        ingreso.setFecha_creacion(new Timestamp(new Date().getTime()));
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

    @DisplayName("test para listar ingresos null")
    @Test
    void testListarIngresosNull(){
        //given
        given(ingresoRepository.obtenerIngresos(2021, 8)).willReturn(null);
        //when
        ingresoService.obtenerIngresos(2021, 8);
        //then
        verify(ingresoRepository, times(1)).obtenerIngresos(2021, 8);
    }

    @DisplayName("test para listar ingresos")
    @Test
    void testListarIngreso(){
       //given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);
        ingreso2.setOrigen("ASTARA");
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));
        //System.out.println(ingreso2.getFecha_creacion());
        given(ingresoRepository.obtenerIngresos(2023,5)).willReturn(new ArrayList<>(List.of(ingreso, ingreso2)));
        //when
        List<IngresoEntity> ingresos=ingresoService.obtenerIngresos(2023,5);
        // then
        assertThat(ingresos).isNotNull();
        assertThat(ingresos.size()).isEqualTo(2);
    }

    
}

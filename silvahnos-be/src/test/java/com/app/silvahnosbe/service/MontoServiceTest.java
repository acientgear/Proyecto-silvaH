package com.app.silvahnosbe.service;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.models.Monto;
import com.app.silvahnosbe.repositories.MontoRepository;
import com.app.silvahnosbe.services.MontoService;

@ExtendWith(MockitoExtension.class)
public class MontoServiceTest {

    @Mock
    private MontoRepository montoRepository;

    @InjectMocks
    private MontoService montoService; 

    Monto monto;
    @BeforeEach
    void setup(){
        monto= new Monto();
        monto.setOrigen("ASTARA");
        monto.setMonto_total(150000);
    }

    @DisplayName("test para obtener montos de ingreso")
    @Test
    void testObtenerMontoIngreso (){
        Monto monto= new Monto();
        monto.setOrigen("ASTARA");
        monto.setMonto_total(150000);

        Monto monto2= new Monto();
        monto2.setOrigen("TALLER");
        monto2.setMonto_total(150000);

        //given
        given(montoRepository.obtenerMontoIngresoPorAnioAndMes(2021, 1)).willReturn(new ArrayList<>(List.of(monto, monto2)));
        //when
        List<Monto> montos=montoService.obtenerMontoIngreso(2021, 1);
        //then
        assertThat(montos).isNotNull();
        assertThat(montos.size()).isEqualTo(2);
    }

    @DisplayName("test para obtener montos de egreso")
    @Test
    void testObtenerMontoEgreso (){
        Monto monto= new Monto();
        monto.setOrigen("ARRIENDO");
        monto.setMonto_total(150000);

        Monto monto2= new Monto();
        monto2.setOrigen("SUELDOS");
        monto2.setMonto_total(150000);

        //given
        given(montoRepository.obtenerMontoEgresoPorAnioAndMes(2023, 5)).willReturn(new ArrayList<>(List.of(monto, monto2)));
        //when
        List<Monto> montos=montoService.obtenerMontoEgreso(2023, 5);
        //then
        assertThat(montos).isNotNull();
        assertThat(montos.size()).isEqualTo(2);
    }
    
}

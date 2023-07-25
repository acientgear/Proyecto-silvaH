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
class MontoServiceTest {

    @Mock
    private MontoRepository montoRepository;

    @InjectMocks
    private MontoService montoService; 

    Monto monto;
    @BeforeEach
    void setup(){
        monto= new Monto();
        monto.setMonto_total(150000);
    }

    @DisplayName("test para obtener montos de ingreso")
    @Test
    void testObtenerMontoIngreso (){
        Monto monto= new Monto();
        monto.setMonto_total(150000);

        Monto monto2= new Monto();
        monto2.setMonto_total(150000);

        //given
        given(montoRepository.obtenerMontoIngresoTotalMesAnual(2021)).willReturn(new ArrayList<>(List.of(monto, monto2)));
        //when
        List<Monto> montos=montoService.obtenerMontoIngresoTotalMesAnual(2021);
        //then
        assertThat(montos).isNotNull();
        assertThat(montos).hasSize(2);
    }

    @DisplayName("test para obtener montos de egreso")
    @Test
    void testObtenerMontoEgreso (){
        Monto monto= new Monto();
        monto.setMonto_total(150000);

        Monto monto2= new Monto();
        monto2.setMonto_total(150000);

        //given
        given(montoRepository.obtenerMontoEgresoTotalMesAnual(2023)).willReturn(new ArrayList<>(List.of(monto, monto2)));
        //when
        List<Monto> montos=montoService.obtenerMontoEgresoTotalMesAnual(2023);
        //then
        assertThat(montos)
            .isNotNull()
            .hasSize(2);
    }
    
}

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
import java.util.Optional;

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

    @DisplayName("test para obtener ingreso por id")
    @Test
    void testObtenerIngresoPorId(){
        //given
        given(ingresoRepository.findById(1l)).willReturn(Optional.of(ingreso));
        //when
        IngresoEntity ingreso1=ingresoService.obtenerIngresoPorId(1l);
        //then
        assertThat(ingreso1).isNotNull();
    }

    @DisplayName("test para obtenerlos últimos ingresos - caso ingresos > 3")
    @Test
    void testObtenerUltimosIngresos(){
        //given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));

        IngresoEntity ingreso3 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(3l);
        ingreso2.setMonto(15000);
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));

        IngresoEntity ingreso4 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(4l);
        ingreso2.setMonto(15000);
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));
        given(ingresoRepository.obtenerUltimosIngresos()).willReturn(new ArrayList<>(List.of(ingreso2,ingreso3,ingreso4)));
        //when
        List<IngresoEntity> ingresos=ingresoService.obtenerUltimosIngresos();
        // then
        assertThat(ingresos).isNotNull();
        assertThat(ingresos.size()).isEqualTo(3);
    }

    @DisplayName("test para obtenerlos últimos ingresos - caso ingresos < 3")
    @Test
    void testObtenerUltimosIngresos2(){
        //given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));

        given(ingresoRepository.obtenerUltimosIngresos()).willReturn(new ArrayList<>(List.of(ingreso,ingreso2)));
        //when
        List<IngresoEntity> ingresos=ingresoService.obtenerUltimosIngresos();
        // then
        assertThat(ingresos).isNotNull();
        assertThat(ingresos.size()).isEqualTo(2);
    }

    @DisplayName("test para obtener el total de los ingresos")
    @Test
    void testObtenerMontoPorMes() {
        //given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);

        IngresoEntity ingreso3 = new IngresoEntity();
        ingreso3.setDescripcion("pintura");
        ingreso3.setId(2l);
        ingreso3.setMonto(15000);
        given(ingresoRepository.obtenerIngresos(2023,3)).willReturn(List.of(ingreso,ingreso2,ingreso3));

        //when
        int Ingresos=ingresoService.obtenerTotalIngresosPorMes(2023,3);
        //then
        assertThat(Ingresos).isNotNull();
        assertThat(Ingresos).isEqualTo(180000);

    }

    @DisplayName("test para obtener el total de los ingresos por dia")
    @Test
    void testObtenerMontoPorDia() {
        //given
        given(ingresoRepository.obtenerMontoPorDia(2023,5,23)).willReturn(180000);
        //when
        int Ingresos=ingresoService.obtenerMontoPorDia(2023,5,23);
        //then
        assertThat(Ingresos).isNotNull();
        assertThat(Ingresos).isEqualTo(180000);

    }

    @DisplayName("test para obtener el saldo de la cuenta")
    @Test
    void testObtenerSaldoCuenta() {
        //given
        given(ingresoRepository.obtenerSaldoCuenta(5)).willReturn(180000);
        //when
        int Ingresos=ingresoService.obtenerSaldoCuenta(5);
        //then
        assertThat(Ingresos).isNotNull();
        assertThat(Ingresos).isEqualTo(180000);

    }

    
}

package com.app.silvahnosbe.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;
import com.app.silvahnosbe.services.IngresoService;

@ExtendWith(MockitoExtension.class)
class IngresoServiceTest {

    @Mock
    private IngresoRepository ingresoRepository;

    @InjectMocks
    private IngresoService ingresoService;

    IngresoEntity ingreso;

    Timestamp fecha = new Timestamp(System.currentTimeMillis());

    @BeforeEach
    public void setup() {
        ingreso = new IngresoEntity();
        ingreso.setId(1l);
        ingreso.setMonto(150000);
        ingreso.setDescripcion("Cuadratura BMW");
        ingreso.setBorrado(false);
        ingreso.setFecha_creacion(new Timestamp(new Date().getTime()));
        ingresoService.guardarIngreso(ingreso);
    }

    @DisplayName("test para guardar un ingreso")
    @Test
    void testGuardarIngreso() {

        // given
        given(ingresoRepository.save(ingreso)).willReturn(ingreso);
        // when
        IngresoEntity ingreso1 = ingresoService.guardarIngreso(ingreso);
        // then
        assertThat(ingreso1).isNotNull();
    }

    @DisplayName("test para listar ingresos null")
    @Test
    void testListarIngresosNull() {
        // given
        given(ingresoRepository.obtenerIngresos(2021, 8)).willReturn(null);
        // when
        ingresoService.obtenerIngresos(2021, 8);
        // then
        verify(ingresoRepository, times(1)).obtenerIngresos(2021, 8);
    }

    @DisplayName("test para listar ingresos")
    @Test
    void testListarIngreso() {
        // given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));
        // System.out.println(ingreso2.getFecha_creacion());
        given(ingresoRepository.obtenerIngresos(2023, 5)).willReturn(new ArrayList<>(List.of(ingreso, ingreso2)));
        // when
        List<IngresoEntity> ingresos = ingresoService.obtenerIngresos(2023, 5);
        // then
        assertThat(ingresos).hasSize(2);
    }

    @DisplayName("test para obtener ingreso por id")
    @Test
    void testObtenerIngresoPorId() {
        // given
        given(ingresoRepository.findById(1L)).willReturn(Optional.of(ingreso));
        // when
        IngresoEntity ingreso1 = ingresoService.obtenerIngresoPorId(1L);
        // then
        assertThat(ingreso1.getId()).isEqualTo(1L);
    }

    @DisplayName("test para obtenerlos últimos ingresos - caso ingresos > 3")
    @Test
    void testObtenerUltimosIngresos() {
        // given
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
        given(ingresoRepository.obtenerUltimosIngresos())
                .willReturn(new ArrayList<>(List.of(ingreso2, ingreso3, ingreso4)));
        // when
        List<IngresoEntity> ingresos = ingresoService.obtenerUltimosIngresos();
        // then
        assertThat(ingresos).hasSize(3);
    }

    @DisplayName("test para obtenerlos últimos ingresos - caso ingresos < 3")
    @Test
    void testObtenerUltimosIngresos2() {
        // given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("Cambio pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);
        ingreso2.setBorrado(false);
        ingreso2.setFecha_creacion(new Timestamp(new Date().getTime()));

        given(ingresoRepository.obtenerUltimosIngresos()).willReturn(new ArrayList<>(List.of(ingreso, ingreso2)));
        // when
        List<IngresoEntity> ingresos = ingresoService.obtenerUltimosIngresos();
        // then
        assertThat(ingresos).hasSize(2);
    }

    @DisplayName("test para obtener el total de los ingresos")
    @Test
    void testObtenerMontoPorMes() {
        // given
        IngresoEntity ingreso2 = new IngresoEntity();
        ingreso2.setDescripcion("pintura");
        ingreso2.setId(2l);
        ingreso2.setMonto(15000);

        IngresoEntity ingreso3 = new IngresoEntity();
        ingreso3.setDescripcion("pintura");
        ingreso3.setId(2l);
        ingreso3.setMonto(15000);
        given(ingresoRepository.obtenerIngresos(2023, 3)).willReturn(List.of(ingreso, ingreso2, ingreso3));

        // when
        int Ingresos = ingresoService.obtenerTotalIngresosPorMes(2023, 3);
        // then
        assertThat(Ingresos).isEqualTo(180000);

    }

    @DisplayName("test para obtener el total de los ingresos por mes cuando es nulo o 0")
    @Test
    void testObtenerMontoPorMesNull() {
        // given
        given(ingresoRepository.obtenerIngresos(2023, 5)).willReturn(Collections.emptyList());
        // when
        int ingresos = ingresoService.obtenerTotalIngresosPorMes(2023, 5);
        // then
        assertThat(ingresos).isZero();
    }

    @DisplayName("test para obtener el saldo de la cuenta")
    @Test
    void testObtenerSaldoCuenta() {
        // given
        given(ingresoRepository.obtenerSaldoCuenta(5)).willReturn(180000);
        // when
        int Ingresos = ingresoService.obtenerSaldoCuenta(5);
        // then
        assertThat(Ingresos).isEqualTo(180000);

    }

    @DisplayName("test para obtener el saldo de la cuenta por mes cuando es nulo")
    @Test
    void testObtenerSaldoCuentaPorMesNull() {
        // given
        given(ingresoRepository.obtenerSaldoCuenta(5)).willReturn(null);
        // when
        int saldo = ingresoService.obtenerSaldoCuenta(5);
        // then
        assertThat(saldo).isZero();
    }

    @Test
    @DisplayName("test de integración creación de ingreso con motivo y movimiento")
    void testCrearIngreso() {
        // given
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCorreo("correo1@gmail.com");
        usuario.setContrasenna("pass1");
        usuario.setUsuario("usuario1");
        
        MovimientoEntity movimiento = new MovimientoEntity();
        movimiento.setId(1l);
        movimiento.setUsuario(usuario);

        MotivoIEntity motivo = new MotivoIEntity();
        motivo.setId(1l);
        motivo.setNombre("motivo1");
        motivo.setDescripcion("descripcion1");
        motivo.setBorrado(false);

        IngresoEntity ingreso = new IngresoEntity();
        ingreso.setId(1l);
        ingreso.setMonto(15000);
        ingreso.setPatente("patente1");
        ingreso.setDescripcion("pintura");
        ingreso.setBorrado(false);
        ingreso.setMotivo(motivo);
        ingreso.setFecha_creacion(fecha);
        ingresoRepository.save(ingreso);
        given(ingresoRepository.save(ingreso)).willReturn(ingreso);

        // when
        IngresoEntity ingreso1 = ingresoService.guardarIngreso(ingreso);

        // then
        assertThat(ingreso1.getId()).isEqualTo(1l);
        assertThat(ingreso1.getMotivo().getId()).isEqualTo(1l);
    }

    @Test
    void testEsBisiesto_AnioNoBisiesto_ReturnsFalse() {
        // Given
        int anio = 2023;

        // When
        boolean result = IngresoService.esBisiesto(anio);

        // Then
        assertFalse(result);
    }

    @Test
    void testEsBisiesto_AnioBisiestoDivisiblePor4_ReturnsTrue() {
        // Given
        int anio = 2024;

        // When
        boolean result = IngresoService.esBisiesto(anio);

        // Then
        assertTrue(result);
    }

    @Test
    void testEsBisiesto_AnioBisiestoDivisiblePor100_ReturnsFalse() {
        // Given
        int anio = 1900;

        // When
        boolean result = IngresoService.esBisiesto(anio);

        // Then
        assertFalse(result);
    }

    @Test
    void testEsBisiesto_AnioBisiestoDivisiblePor400_ReturnsTrue() {
        // Given
        int anio = 2000;

        // When
        boolean result = IngresoService.esBisiesto(anio);

        // Then
        assertTrue(result);
    }

        @Test
    void testObtenerDiasMes_FebreroAnioBisiesto_Returns29() {
        // Given
        int mes = 2;
        int anio = 2024;

        // When
        int result = IngresoService.obtenerDiasMes(mes, anio);

        // Then
        assertEquals(29, result);
    }

    @Test
    void testObtenerDiasMes_FebreroAnioBisiesto_Returns292() {
        // Given
        int mes = 2;
        int anio = 400;

        // When
        int result = IngresoService.obtenerDiasMes(mes, anio);

        // Then
        assertEquals(29, result);
    }

    @Test
    void testObtenerDiasMes_MesesCon31Dias_Returns31() {
        // Given
        int[] mesesCon31Dias = {1, 3, 5, 7, 8, 10, 12};

        // When
        for (int mes : mesesCon31Dias) {
            int result = IngresoService.obtenerDiasMes(mes, 2023);
            assertEquals(31, result);
        }
    }

    @Test
    void testObtenerDiasMes_MesesCon30Dias_Returns30() {
        // Given
        int[] mesesCon30Dias = {4, 6, 9, 11};

        // When
        for (int mes : mesesCon30Dias) {
            int result = IngresoService.obtenerDiasMes(mes, 2023);
            assertEquals(30, result);
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMontosPorDia() {
        // Given
        int anio = 2023;
        int mes = 7;
        int numeroDias = 31;
        List<Integer> expectedMontos = new ArrayList<>();
        for (int i = 1; i <= numeroDias; i++) {
            // Add some dummy data for the montos list
            expectedMontos.add(i * 100);
            // Stub the ingresoRepository.obtenerMontoPorDia method
            when(ingresoRepository.obtenerMontoPorDia(anio, mes, i)).thenReturn(i * 100);
        }

        // When
        List<Integer> result = ingresoService.getMontosPorDia(anio, mes);

        // Then
        assertEquals(expectedMontos, result);
    }

    @Test
    void testGetMontosUltimos5Dias() {
        // Given
        List<Integer> expectedMontos = new ArrayList<>();
        expectedMontos.add(100); // Dummy data for the first day
        expectedMontos.add(200); // Dummy data for the second day
        expectedMontos.add(300); // Dummy data for the third day
        expectedMontos.add(400); // Dummy data for the fourth day
        expectedMontos.add(500); // Dummy data for the fifth day

        // Mock the behavior of ingresoRepository.obtenerMontoPorDia
        when(ingresoRepository.obtenerMontoPorDia(anyInt(), anyInt(), anyInt()))
                .thenReturn(100, 200, 300, 400, 500);

        // When
        List<Integer> actualMontos = ingresoService.getMontosUltimos5Dias();

        // Then
        assertEquals(expectedMontos, actualMontos);
    }

}

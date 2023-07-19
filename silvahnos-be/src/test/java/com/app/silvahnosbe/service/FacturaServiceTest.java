package com.app.silvahnosbe.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.services.FacturaService;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTest {
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    FacturaEntity factura;
    EstadoEntity estado;

    @BeforeEach
    void setUp() {
        estado = new EstadoEntity();
        estado.setId(1l);
        estado.setNombre("no pagada");

        factura = new FacturaEntity();
        factura.setId(1l);
        factura.setNumero_factura(1);
        factura.setFecha_emision(new Date(System.currentTimeMillis()));
        factura.setFecha_vencimiento(new Date(System.currentTimeMillis()));
        factura.setFecha_pago(new Date(System.currentTimeMillis()));
        factura.setMonto(14000);
        factura.setObservaciones("pintura");
        factura.setBorrado(false);
        factura.setFecha_creacion(new Timestamp(System.currentTimeMillis()));
        facturaService.guardarFactura(factura);
    }

    @DisplayName("test para guardar una factura")
    @Test
    void testGuardarFactura() {

        // given
        given(facturaRepository.save(factura)).willReturn(factura);
        // when
        FacturaEntity factura1 = facturaService.guardarFactura(factura);
        // then
        assertThat(factura1).isNotNull();
    }

    @DisplayName("test para obtener facturas")
    @Test
    void testObtenerFacturas() {
        // given
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(factura);
        given(facturaRepository.obteberFacturas(2021, 10)).willReturn(facturas);
        // when
        List<FacturaEntity> facturas1 = facturaService.obtenerFacturas(2021, 10);
        // then
        assertThat(facturas1).isNotNull();
    }

    @DisplayName("test para obtener facturas cuando no existen")
    @Test
    void testObtenerFacturasCuandoNoExisten() {
        // given
        List<FacturaEntity> facturas = new ArrayList<>();
        given(facturaRepository.obteberFacturas(2021, 10)).willReturn(facturas);
        // when
        List<FacturaEntity> facturas1 = facturaService.obtenerFacturas(2021, 10);
        // then
        assertThat(facturas1).isEmpty();
    }

    @DisplayName("test para obtener iva")
    @Test
    void testObtenerIva() {
        // given
        given(facturaRepository.obtenerIva(2021, 10)).willReturn(1400);
        // when
        Integer iva = facturaService.obtenerIva(2021, 10);
        // then
        assertThat(iva).isNotNull();
    }

    @DisplayName("test para obtener proximas a vencer")
    @Test
    void testObtenerProximasVencer() {
        // given
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(factura);
        given(facturaRepository.obtenerProximasVencer(2021, 10)).willReturn(facturas);
        // when
        List<FacturaEntity> facturas1 = facturaService.obtenerProximasVencer(2021, 10);
        // then
        assertThat(facturas1).isNotNull();
    }

    @DisplayName("test para obtener proximas a vencer cuando no existen")
    @Test
    void testObtenerProximasVencerCuandoNoExisten() {
        // given
        List<FacturaEntity> facturas = new ArrayList<>();
        given(facturaRepository.obtenerProximasVencer(2021, 10)).willReturn(facturas);
        // when
        List<FacturaEntity> facturas1 = facturaService.obtenerProximasVencer(2021, 10);
        // then
        assertThat(facturas1).isEmpty();
    }

    @DisplayName("test para obtener proximas a vencer (pedro)")
    @Test
    void testObtenerProximasVencerP() {
        // given
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(factura);
        given(facturaRepository.facturaV(4)).willReturn(facturas);
        // when
        List<FacturaEntity> facturas1 = facturaService.facturaV(4);
    
        // then
        assertThat(facturas1).isNotNull();
    }
    
    @DisplayName("test para pagar factura")
    @Test
    void testPagarFactura() {
        // given
        FacturaEntity facturaPagada = new FacturaEntity();
        facturaPagada.setId(1l);
        given(facturaRepository.save(facturaPagada)).willReturn(facturaPagada);
        // when
        FacturaEntity factura1 = facturaService.pagarFactura(facturaPagada);
        // then
        assertThat(factura1.getId()).isEqualTo(1l);
    } 

    @DisplayName("test de integración para creación de factura")
    @Test
    void testCrearFactura(){
        // given
        LocalEntity local = new LocalEntity();
        local.setId(1l);
        local.setNombre("local1");
        local.setDireccion("direccion1");
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCorreo("correo1@gmail.com");
        usuario.setContrasenna("pass1");
        usuario.setUsuario("usuario1");
        
        MovimientoEntity movimiento = new MovimientoEntity();
        movimiento.setId(1l);
        movimiento.setUsuario(usuario);

        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(1l);
        empresa.setNombre("empresa1");
        empresa.setRut("rut1");
        empresa.setDireccion("direccion1");

        EstadoEntity estado = new EstadoEntity();
        estado.setId(1l);
        estado.setNombre("no pagada");

        FacturaEntity factura = new FacturaEntity();    
        factura.setId(1l);
        factura.setNumero_factura(1);
        factura.setFecha_emision(new Date(System.currentTimeMillis()));
        factura.setFecha_vencimiento(new Date(System.currentTimeMillis()));
        factura.setFecha_pago(new Date(System.currentTimeMillis()));
        factura.setMonto(14000);
        factura.setObservaciones("pintura");
        factura.setBorrado(false);
        factura.setEstado(estado);
        factura.setEmpresa(empresa);
        factura.setFecha_creacion(new Timestamp(System.currentTimeMillis()));
        given(facturaRepository.save(factura)).willReturn(factura);

        // when
        FacturaEntity factura1 = facturaService.guardarFactura(factura);

        // then
        assertThat(factura1.getId()).isEqualTo(1l);
        assertThat(factura1.getEmpresa().getId()).isEqualTo(1l);
     

    }

    /*@Test
    public void testPagarFacturaa() {
        // Crear una factura de ejemplo con una fecha de pago
        FacturaEntity factura = new FacturaEntity();
        factura.setFecha_pago(new Date(System.currentTimeMillis()));

        // Mock del repositorio
        FacturaRepository facturaRepository = mock(FacturaRepository.class);

        // Crear una instancia del servicio y establecer el repositorio mock
        //FacturaService facturaService = new FacturaService();
        //facturaService.setFacturaRepository(facturaRepository);

        // Llamar al método que se está probando
        FacturaEntity result = facturaService.pagarFactura(factura);

        // Verificar que la fecha de pago se haya restado correctamente
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(factura.getFecha_pago());
        expectedCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date expectedFecha = expectedCalendar.getTime();

        // Convertir java.util.Date a java.sql.Date
        java.sql.Date expectedSqlFecha = new java.sql.Date(expectedFecha.getTime());

        // Convertir java.util.Date a java.sql.Date para el resultado
        java.sql.Date resultSqlFecha = new java.sql.Date(result.getFecha_pago().getTime());

        assertEquals(expectedSqlFecha, resultSqlFecha);

        // Verificar que el método save del repositorio fue llamado
        verify(facturaRepository).save(factura);
    }*/


    @Test
    public void testPagarFactura_FechaPagoNotNull_ReturnsUpdatedFacturaEntity() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        java.util.Date fechaPago = new java.util.Date();
        factura.setFecha_pago(fechaPago);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPago);
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Restar un día
        java.util.Date expectedFecha = calendar.getTime();

        when(facturaRepository.save(factura)).thenReturn(factura);

        // When
        FacturaEntity result = facturaService.pagarFactura(factura);

        // Then
        assertNotNull(result);
        assertEquals(expectedFecha, result.getFecha_pago());
        verify(facturaRepository).save(factura);
    }

    @Test
    public void testPagarFactura_FechaPagoNull_ReturnsSameFacturaEntity() {
        // Given
        FacturaEntity factura = new FacturaEntity();

        when(facturaRepository.save(factura)).thenReturn(factura);

        // When
        FacturaEntity result = facturaService.pagarFactura(factura);

        // Then
        assertNotNull(result);
        assertNull(result.getFecha_pago());
        verify(facturaRepository).save(factura);
    }

       



}

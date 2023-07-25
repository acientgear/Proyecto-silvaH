package com.app.silvahnosbe.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

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
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.services.FacturaService;

@ExtendWith(MockitoExtension.class)
class FacturaServiceTest {
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
        factura.setFecha_emision(new Timestamp(System.currentTimeMillis()));
        factura.setFecha_vencimiento(new Timestamp(System.currentTimeMillis()));
        factura.setFecha_pago(new Timestamp(System.currentTimeMillis()));
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
        factura.setFecha_emision(new Timestamp(System.currentTimeMillis()));
        factura.setFecha_vencimiento(new Timestamp(System.currentTimeMillis()));
        factura.setFecha_pago(new Timestamp(System.currentTimeMillis()));
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
}
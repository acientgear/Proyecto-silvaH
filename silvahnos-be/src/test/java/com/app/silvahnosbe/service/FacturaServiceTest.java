package com.app.silvahnosbe.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.services.FacturaService;

/*@ExtendWith(MockitoExtension.class)
public class FacturaServiceTest {
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;


    Date fecha= new Date();
    FacturaEntity factura;
    @BeforeEach

    void setup(){
        factura= new FacturaEntity();
        factura.setNumero_factura(131);
        factura.setFecha_emision(fecha);
        factura.setFecha_vencimiento(fecha);
        factura.setFecha_pago(fecha);
        factura.setObservaciones("pintura");
        factura.setBorrado(false);
        factura.setFecha_creacion(new Timestamp(new Date().getTime()));
        facturaService.guardarFactura(factura);
    }

    @DisplayName("test para guardar una factura")
    @Test
    void testGuardarFactura(){
        //given
        given(facturaRepository.save(factura)).willReturn(factura);
        //when
        FacturaEntity factura1=facturaService.guardarFactura(factura);
        //then
        assertThat(factura1).isNotNull();
    }


    @DisplayName("test para obtener facturas")
    @Test
    void obtenerFacturasTest(){
        facturaService.obtenerFacturas(2021, 5);
        verify(facturaRepository).obteberFacturas(2021, 5);
    }
}*/

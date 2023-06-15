package com.app.silvahnosbe.entity;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.FacturaEntity;

public class FacturaEntityTest {
    
    @Test
    void testNumeroFactura() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setNumero_factura(12345);
        int numeroFactura = factura.getNumero_factura();
        
        // Then
        assertEquals(12345, numeroFactura);
    }

    @Test
    void testFechaEmision() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setFecha_emision(null);
        Object fechaEmision = factura.getFecha_emision();
        
        // Then
        assertEquals(null, fechaEmision);
    }

    @Test

    void testFechaVencimiento() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setFecha_vencimiento(null);
        Object fechaVencimiento = factura.getFecha_vencimiento();
        
        // Then
        assertEquals(null, fechaVencimiento);
    }

    @Test
    void testFechaPago() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setFecha_pago(null);
        Object fechaPago = factura.getFecha_pago();
        
        // Then
        assertEquals(null, fechaPago);
    }

    @Test
    void testMonto() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setMonto(12345);
        int monto = factura.getMonto();
        
        // Then
        assertEquals(12345, monto);
    }

    @Test
    void testObservaciones() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setObservaciones("observaciones");
        String observaciones = factura.getObservaciones();
        
        // Then
        assertEquals("observaciones", observaciones);
    }

    @Test
    void testBorrado() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setBorrado(true);
        boolean borrado = factura.isBorrado();
        
        // Then
        assertEquals(true, borrado);
    }

    @Test
    void testEstado() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setEstado(null);
        Object estado = factura.getEstado();
        
        // Then
        assertEquals(null, estado);
    }

    @Test
    void testEmpresa() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setEmpresa(null);
        Object empresa = factura.getEmpresa();
        
        // Then
        assertEquals(null, empresa);
    }

    @Test
    void testMovimiento() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setMovimiento(null);
        Object movimiento = factura.getMovimiento();
        
        // Then
        assertEquals(null, movimiento);
    }

    @Test
    void testFechaCreacion() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setFecha_creacion(null);
        Object fechaCreacion = factura.getFecha_creacion();
        
        // Then
        assertEquals(null, fechaCreacion);
    }

    @Test
    void testFechaModificacion() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setFecha_modificacion(null);
        Object fechaModificacion = factura.getFecha_modificacion();
        
        // Then
        assertEquals(null, fechaModificacion);
    }

    @Test
    void testFechaBorrado() {
        // Given
        FacturaEntity factura = new FacturaEntity();
        
        // When
        factura.setFecha_borrado(null);
        Object fechaBorrado = factura.getFecha_borrado();
        
        // Then
        assertEquals(null, fechaBorrado);
    }

}

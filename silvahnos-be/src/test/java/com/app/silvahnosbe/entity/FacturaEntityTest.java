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
}

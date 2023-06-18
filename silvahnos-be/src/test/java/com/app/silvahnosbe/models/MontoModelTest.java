package com.app.silvahnosbe.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MontoModelTest {
    
    @Test
    void testMotivo(){
        // Given
        Monto monto = new Monto();

        // When
        monto.setMotivo(1);
        int motivo = monto.getMotivo();

        // Then
        assertEquals(1, motivo);   
    }

    @Test
    void testMontoTotal(){
        // Given
        Monto monto = new Monto();

        // When
        monto.setMonto_total(1000);
        int monto_total = monto.getMonto_total();

        // Then
        assertEquals(1000, monto_total);   
    }

}

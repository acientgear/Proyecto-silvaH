package com.app.silvahnosbe.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MontoModelTest {

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

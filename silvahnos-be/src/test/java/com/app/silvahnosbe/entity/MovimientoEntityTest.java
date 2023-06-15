package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.MovimientoEntity;

public class MovimientoEntityTest {
    
    @Test
    void testId() {
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setId(1l);
        Long id = movimiento.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testLocal(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setLocal(null);
        Object local = movimiento.getLocal();

        // Then
        assertEquals(null, local);
    }

    @Test
    void testUsuario(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setUsuario(null);
        Object usuario = movimiento.getUsuario();

        // Then
        assertEquals(null, usuario);
    }
}

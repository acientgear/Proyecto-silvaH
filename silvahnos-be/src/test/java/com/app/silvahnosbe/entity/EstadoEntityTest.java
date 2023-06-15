package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.EstadoEntity;

public class EstadoEntityTest {
    
    @Test
    void testId(){
        // Given
        EstadoEntity estado = new EstadoEntity();

        // When
        estado.setId(1l);
        Long id = estado.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testNombre(){
        // Given
        EstadoEntity estado = new EstadoEntity();

        // When
        estado.setNombre("nombre");
        String nombre = estado.getNombre();

        // Then
        assertEquals("nombre", nombre);
    }
}

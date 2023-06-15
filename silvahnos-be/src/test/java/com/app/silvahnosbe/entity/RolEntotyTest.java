package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.RolEntity;

public class RolEntotyTest {

    @Test
    void testId(){
        // Given
        RolEntity rol = new RolEntity();

        // When
        rol.setId(1l);
        Long id = rol.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testNombre(){
        // Given
        RolEntity rol = new RolEntity();

        // When
        rol.setNombre("nombre");
        String nombre = rol.getNombre();

        // Then
        assertEquals("nombre", nombre);
    }
    
}

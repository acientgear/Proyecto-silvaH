package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.models.ERole;

public class RolEntityTest {

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
        //Given
        RolEntity RolEntity = new RolEntity();

        //When
        ERole nombre = ERole.ADMIN;
        RolEntity.setNombre(nombre);
        ERole nombreRol = RolEntity.getNombre();

        //Then
        assertEquals(nombreRol, nombre);
    }
    
}

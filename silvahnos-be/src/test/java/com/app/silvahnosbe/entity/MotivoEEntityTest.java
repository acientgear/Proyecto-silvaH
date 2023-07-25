package com.app.silvahnosbe.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.app.silvahnosbe.entities.MotivoEEntity;

class MotivoEEntityTest {

    @Test
    void testId(){
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();

        // When
        motivoE.setId(1l);
        Long id = motivoE.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testNombre() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();

        // When
        motivoE.setNombre("nombre");
        String nombre = motivoE.getNombre();

        // Then
        assertEquals("nombre", nombre);
    }

    @Test
    void testDescripcion() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();

        // When
        motivoE.setDescripcion("descripcion");
        String descripcion = motivoE.getDescripcion();

        // Then
        assertEquals("descripcion", descripcion);
    }

    @Test
    void testBorrado() {
        // Given
        MotivoEEntity motivoE = new MotivoEEntity();

        // When
        motivoE.setBorrado(false);
        boolean borrado = motivoE.isBorrado();

        // Then
        assertEquals(false, borrado);
    }


}

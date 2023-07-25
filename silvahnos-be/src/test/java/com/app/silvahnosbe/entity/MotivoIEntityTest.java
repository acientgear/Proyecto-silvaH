package com.app.silvahnosbe.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.app.silvahnosbe.entities.MotivoIEntity;

class MotivoIEntityTest {

    @Test
    void testId(){
        // Given
        MotivoIEntity motivoI = new MotivoIEntity();

        // When
        motivoI.setId(1l);
        Long id = motivoI.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testNombre() {
        // Given
        MotivoIEntity motivoI = new MotivoIEntity();

        // When
        motivoI.setNombre("nombre");
        String nombre = motivoI.getNombre();

        // Then
        assertEquals("nombre", nombre);
    }

    @Test
    void testDescripcion() {
        // Given
        MotivoIEntity motivoI = new MotivoIEntity();

        // When
        motivoI.setDescripcion("descripcion");
        String descripcion = motivoI.getDescripcion();

        // Then
        assertEquals("descripcion", descripcion);
    }

    @Test
    void testBorrado() {
        // Given
        MotivoIEntity motivoI = new MotivoIEntity();

        // When
        motivoI.setBorrado(false);
        boolean borrado = motivoI.isBorrado();

        // Then
        assertEquals(false, borrado);
    }


}

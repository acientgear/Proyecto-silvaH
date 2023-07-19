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
    void testUsuario(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setUsuario(null);
        Object usuario = movimiento.getUsuario();

        // Then
        assertEquals(null, usuario);
    }

    @Test
    void testTipo(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setTipo("tipo");
        String tipo = movimiento.getTipo();

        // Then
        assertEquals("tipo", tipo);
    }

    @Test
    void testNombreTabla(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setNombre_tabla("nombre_tabla");
        String nombre_tabla = movimiento.getNombre_tabla();

        // Then
        assertEquals("nombre_tabla", nombre_tabla);
    }

    @Test
    void testIdObjeto(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setId_objeto(1l);
        Long id_objeto = movimiento.getId_objeto();

        // Then
        assertEquals(1, id_objeto);
    }

    @Test
    void testFechaCreacion(){
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();

        // When
        movimiento.setFecha_creacion(null);
        Object fecha_creacion = movimiento.getFecha_creacion();

        // Then
        assertEquals(null, fecha_creacion);
    }
}

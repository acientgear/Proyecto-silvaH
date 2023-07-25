package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.IngresoEntity;

class IngresoEntityTest {

    @Test
    void testId() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setId(1l);
        Long id = ingreso.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testDescripcion() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setDescripcion("descripcion");
        String descripcion = ingreso.getDescripcion();

        // Then
        assertEquals("descripcion", descripcion);
    }

    @Test
    void testMonto() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setMonto(12345);
        int monto = ingreso.getMonto();

        // Then
        assertEquals(12345, monto);
    }

    @Test
    void testPatente(){
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setPatente(null);
        Object patente = ingreso.getPatente();

        // Then
        assertEquals(null, patente);
    }

    @Test
    void testBorrado() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setBorrado(false);
        boolean borrado = ingreso.isBorrado();

        // Then
        assertEquals(false, borrado);
    }

    @Test
    void testMotivoE() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setMotivo(null);
        Object motivoE = ingreso.getMotivo();

        // Then
        assertEquals(null, motivoE);
    }

    @Test
    void testFechaCreacion() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setFecha_creacion(null);
        Object fechaCreacion = ingreso.getFecha_creacion();

        // Then
        assertEquals(null, fechaCreacion);
    }

    @Test
    void testFechaModificacion() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setFecha_modificacion(null);
        Object fechaModificacion = ingreso.getFecha_modificacion();

        // Then
        assertEquals(null, fechaModificacion);
    }

    @Test
    void testFechaBorrado() {
        // Given
        IngresoEntity ingreso = new IngresoEntity();

        // When
        ingreso.setFecha_borrado(null);
        Object fechaBorrado = ingreso.getFecha_borrado();

        // Then
        assertEquals(null, fechaBorrado);
    }

}

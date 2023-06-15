package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.EgresoEntity;

public class EgresoEntityTets {

    @Test
    void testDescripcion() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setDescripcion("descripcion");
        String descripcion = egreso.getDescripcion();

        // Then
        assertEquals("descripcion", descripcion);
    }

    @Test
    void testMonto() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setMonto(12345);
        int monto = egreso.getMonto();

        // Then
        assertEquals(12345, monto);
    }

    @Test
    void testBorrado() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setBorrado(false);
        boolean borrado = egreso.isBorrado();

        // Then
        assertEquals(false, borrado);
    }

    @Test
    void testMotivoE() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setMotivo(null);
        Object motivoE = egreso.getMotivo();

        // Then
        assertEquals(null, motivoE);
    }

    @Test
    void testMovimiento(){
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setMovimiento(null);
        Object movimiento = egreso.getMovimiento();

        // Then
        assertEquals(null, movimiento);
    }

    @Test
    void testFechaCreacion() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setFecha_creacion(null);
        Object fechaCreacion = egreso.getFecha_creacion();

        // Then
        assertEquals(null, fechaCreacion);
    }

    @Test
    void testFechaModificacion() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setFecha_modificacion(null);
        Object fechaModificacion = egreso.getFecha_modificacion();

        // Then
        assertEquals(null, fechaModificacion);
    }

    @Test
    void testFechaBorrado() {
        // Given
        EgresoEntity egreso = new EgresoEntity();

        // When
        egreso.setFecha_borrado(null);
        Object fechaBorrado = egreso.getFecha_borrado();

        // Then
        assertEquals(null, fechaBorrado);
    }

}

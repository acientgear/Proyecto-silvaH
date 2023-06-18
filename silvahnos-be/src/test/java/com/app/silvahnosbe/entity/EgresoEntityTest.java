package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;

public class EgresoEntityTest {

    @Test
    void testAllArgsConsstructor(){
        // Given
        MotivoEEntity motivoe = new MotivoEEntity();
        MovimientoEntity movimiento = new MovimientoEntity();
        Timestamp fecha1 = new Timestamp(System.currentTimeMillis());
        Timestamp fecha2 = new Timestamp(System.currentTimeMillis());
        Timestamp fecha3 = new Timestamp(System.currentTimeMillis());
        EgresoEntity egreso = new EgresoEntity(1,12345,"desc",true,motivoe,movimiento,fecha1,fecha2,fecha3);

        // When
        Long id = egreso.getId();
        String descripcion = egreso.getDescripcion();
        int monto = egreso.getMonto();
        boolean borrado = egreso.isBorrado();
        Object motivoE = egreso.getMotivo();
        Object mov = egreso.getMovimiento();
        Object fechaCreacion = egreso.getFecha_creacion();
        Object fechaModificacion = egreso.getFecha_modificacion();
        Object fechaBorrado = egreso.getFecha_borrado();

        // Then
        assertEquals(1, id);
        assertEquals("desc", descripcion);
        assertEquals(12345, monto);
        assertEquals(true, borrado);
        assertEquals(motivoe, motivoE);
        assertEquals(mov, movimiento);
        assertEquals(fecha1, fechaCreacion);
        assertEquals(fecha2, fechaModificacion);
        assertEquals(fecha3, fechaBorrado);
    }

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

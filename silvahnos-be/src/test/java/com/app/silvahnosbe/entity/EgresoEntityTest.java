package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;

public class EgresoEntityTest {


    @Test
    void testDataMethods() {
        // Given
        EgresoEntity egreso1 = new EgresoEntity();
        EgresoEntity egreso2 = new EgresoEntity();

        // When
        egreso1.setId(1L);
        egreso1.setMonto(12345);
        egreso1.setDescripcion("descripcion");
        egreso1.setBorrado(false);
        egreso2.setId(1L);
        egreso2.setMonto(12345);
        egreso2.setDescripcion("descripcion");
        egreso2.setBorrado(false);

        // Then
        assertEquals(egreso1, egreso2);
        assertEquals(egreso1.hashCode(), egreso2.hashCode());
        assertEquals("EgresoEntity(id=1, monto=12345, descripcion=descripcion, borrado=false, motivo=null, movimiento=null, fecha_creacion=null, fecha_modificacion=null, fecha_borrado=null)", egreso1.toString());
    }

    @Test
    void testGetters() {
        // Given
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(1L);
        egreso.setMonto(12345);
        egreso.setDescripcion("descripcion");
        egreso.setBorrado(false);

        // When
        Long id = egreso.getId();
        int monto = egreso.getMonto();
        String descripcion = egreso.getDescripcion();
        boolean borrado = egreso.isBorrado();

        // Then
        assertEquals(1L, id);
        assertEquals(12345, monto);
        assertEquals("descripcion", descripcion);
        assertEquals(false, borrado);
    }

    @Test
    void testAllArgsConsstructor(){
        // Given
        EgresoEntity egreso = new EgresoEntity(1L, 12345, "descripcion", false, new MotivoEEntity(), new MovimientoEntity(), new Timestamp(0), new Timestamp(0), new Timestamp(0));

        // When
        Long id = egreso.getId();
        int monto = egreso.getMonto();
        String descripcion = egreso.getDescripcion();
        boolean borrado = egreso.isBorrado();
        MotivoEEntity motivo = egreso.getMotivo();
        MovimientoEntity movimiento = egreso.getMovimiento();
        Timestamp fechaCreacion = egreso.getFecha_creacion();
        Timestamp fechaModificacion = egreso.getFecha_modificacion();
        Timestamp fechaBorrado = egreso.getFecha_borrado();

        // Then
        assertEquals(1L, id);
        assertEquals(12345, monto);
        assertEquals("descripcion", descripcion);
        assertEquals(false, borrado);
        assertEquals(new MotivoEEntity(), motivo);
        assertEquals(new MovimientoEntity(), movimiento);
        assertEquals(new Timestamp(0), fechaCreacion);
        assertEquals(new Timestamp(0), fechaModificacion);
        assertEquals(new Timestamp(0), fechaBorrado);
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

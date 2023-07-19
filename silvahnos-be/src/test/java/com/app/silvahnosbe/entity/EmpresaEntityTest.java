package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.EmpresaEntity;

public class EmpresaEntityTest {
    
    @Test
    public void testId() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();

        // When
        empresa.setId(1l);
        Long id = empresa.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    public void testNombre() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();

        // When
        empresa.setNombre("nombre");
        String nombre = empresa.getNombre();

        // Then
        assertEquals("nombre", nombre);
    }

    @Test
    public void testRut() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();

        // When
        empresa.setRut("rut");
        String rut = empresa.getRut();

        // Then
        assertEquals("rut", rut);
    }

    @Test
    public void testDireccion() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();

        // When
        empresa.setDireccion("direccion");
        String direccion = empresa.getDireccion();

        // Then
        assertEquals("direccion", direccion);
    }

    @Test
    public void testBorrado() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();

        // When
        empresa.setBorrado(true);
        boolean borrado = empresa.isBorrado();

        // Then
        assertEquals(true, borrado);
    }
    
}

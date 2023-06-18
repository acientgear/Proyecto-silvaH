package com.app.silvahnosbe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class RegistroModelTest {
    
    @Test
    void testId(){
        // Given
        Registro registro = new Registro();

        // When
        registro.setId(1l);
        Long id = registro.getId();

        // Then
        assertEquals(1, id);   
    }

    @Test
    void testTipo(){
        // Given
        Registro registro = new Registro();

        // When
        registro.setTipo("Ingreso");
        String tipo = registro.getTipo();

        // Then
        assertEquals("Ingreso", tipo);   
    }

    @Test
    void testFecha(){
        // Given
        Registro registro = new Registro();

        // When
        Date fecha = new Date();
        registro.setFecha(fecha);
        Date fecha1 = registro.getFecha();

        // Then
        assertEquals(fecha1, fecha);   
    }

    @Test
    void testDescripcion(){
        // Given
        Registro registro = new Registro();

        // When
        registro.setDescripcion("Pago de sueldo");
        String descripcion = registro.getDescripcion();

        // Then
        assertEquals("Pago de sueldo", descripcion);   
    }

    @Test
    void testMonto(){
        // Given
        Registro registro = new Registro();

        // When
        registro.setMonto(10000);
        int monto = registro.getMonto();

        // Then
        assertEquals(10000, monto);   
    }
}

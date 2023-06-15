package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.ParametroEntity;

public class ParametroEntityTest {
    
    @Test
    void testId(){
        // Given
        ParametroEntity parametro = new ParametroEntity();

        // When
        parametro.setId(1l);
        Long id = parametro.getId();

        // Then
        assertEquals(1, id);
    }

    @Test
    void testValor(){
        // Given
        ParametroEntity parametro = new ParametroEntity();

        // When
        parametro.setValor("valor");
        String valor = parametro.getValor();

        // Then
        assertEquals("valor", valor);
    }
}

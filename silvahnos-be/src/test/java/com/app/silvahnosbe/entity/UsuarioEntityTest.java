package com.app.silvahnosbe.entity;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.UsuarioEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioEntityTest {

    @Test
    void testCorreo(){
        // Given
        UsuarioEntity usuario = new UsuarioEntity();

        // When
        usuario.setCorreo("correo");
        String correo = usuario.getCorreo();

        // Then
        assertEquals("correo", correo);
    }

    @Test
    void testContrasenia(){
        // Given
        UsuarioEntity usuario = new UsuarioEntity();

        // When
        usuario.setContrasenna("contrasenia");
        String contrasenia = usuario.getContrasenna();

        // Then
        assertEquals("contrasenia", contrasenia);
    }

    @Test
    void testNombre(){
        // Given
        UsuarioEntity usuario = new UsuarioEntity();

        // When
        usuario.setNombre("nombre");
        String nombre = usuario.getNombre();

        // Then
        assertEquals("nombre", nombre);
    }
}

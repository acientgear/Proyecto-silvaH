package com.app.silvahnosbe.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.UsuarioController;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;

@ExtendWith(SpringExtension.class)
class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        passwordEncoder = mock(PasswordEncoder.class);
        usuarioService = mock(UsuarioService.class);

        //construir usuario controller con constructor injection
        usuarioController = new UsuarioController(passwordEncoder, usuarioService);
        usuarioController.setPasswordEncoder(passwordEncoder);
        usuarioController.setUsuarioService(usuarioService);
    }

    @DisplayName("Test para obtener todos los usuarios")
    @Test
    void testGetAllUsuarios_ExistenUsuarios_ReturnsList() {
        // Given
        List<UsuarioEntity> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioEntity());
        when(usuarioService.obtenerUsuarios()).thenReturn(usuarios);

        // When
        ResponseEntity<List<UsuarioEntity>> response = usuarioController.getUsuarios();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    }

    @DisplayName("Test para obtener todos los usuarios cuando no existen usuarios")

    @Test
    void testGetAllUsuarios_NoExistenUsuarios_ReturnsNotFound() {
        // Given
        when(usuarioService.obtenerUsuarios()).thenReturn(null);

        // When
        ResponseEntity<List<UsuarioEntity>> response = usuarioController.getUsuarios();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @DisplayName("Test para crear usuario")
    @Test
    void testCreateUsuario_CreaUsuario_ReturnsUsuario() {
        // Given
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuario("Nombre");
        usuario.setCorreo("Correo");
        usuario.setContrasenna("Contrasenna");

        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.encode(usuario.getContrasenna())).thenReturn("EncodedPassword");

        UsuarioService usuarioService = mock(UsuarioService.class);
        when(usuarioService.guardarUsuario(usuario)).thenReturn(usuario);

        // Create the controller instance with constructor injection
        UsuarioController usuarioController = new UsuarioController(passwordEncoder, usuarioService);

        // When
        ResponseEntity<UsuarioEntity> response = usuarioController.createUsuario(usuario);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }
}

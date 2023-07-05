package com.app.silvahnosbe.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.UsuarioRepository;
import com.app.silvahnosbe.services.UsuarioService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    UsuarioEntity usuario;

    @BeforeEach
    void setup() {
        usuario = new UsuarioEntity();
        usuario.setUsuario("Juan");
        usuario.setCorreo("luis@gmail.com");
        usuario.setContrasenna("1234");
        usuarioService.guardarUsuario(usuario);
    }

    @DisplayName("test para obtener todos los usuarios cuando existen")
    @Test
    void testObtenerUsuarios() {
        given(usuarioRepository.findAll()).willReturn(java.util.Arrays.asList(usuario));
        assertThat(usuarioService.obtenerUsuarios()).isNotNull();
    }

    @DisplayName("test para obtener todos los usuarios cuando no existen")
    @Test
    void testObtenerUsuariosVacio() {
        given(usuarioRepository.findAll()).willReturn(java.util.Arrays.asList());
        assertThat(usuarioService.obtenerUsuarios()).isEmpty();
    }

    @DisplayName("test para guardar un usuario")
    @Test
    void testGuardarUsuario() {
        given(usuarioRepository.save(usuario)).willReturn(usuario);
        assertThat(usuarioService.guardarUsuario(usuario)).isNotNull();
    }
}

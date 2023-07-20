/*package com.app.silvahnosbe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.models.ERole;
import com.app.silvahnosbe.repositories.UsuarioRepository;
import com.app.silvahnosbe.services.UserDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceImpl(usuarioRepository);
    }

    @Test
    public void testLoadUserByUsername_UsuarioExistente_ReturnsUserDetails() {
        // Given
        String username = "testuser";
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsuario(username);
        usuarioEntity.setContrasenna("testpassword");
        RolEntity rolEntity = new RolEntity();
        ERole nombre = ERole.ADMIN;
        rolEntity.setNombre(nombre);

        UsuarioRepository usuarioRepositoryMock = mock(UsuarioRepository.class);
        when(usuarioRepositoryMock.findByUsuario(username)).thenReturn(Optional.of(usuarioEntity));

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Then
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(usuarioEntity.getContrasenna(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}*/

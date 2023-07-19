package com.app.silvahnosbe.service;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.MovimientoRepository;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.UsuarioService;

@ExtendWith(SpringExtension.class)
public class MovimientoServiceTest {
    
    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    @Mock 
    private UsuarioService usuarioService;

    MovimientoEntity movimiento;

    @BeforeEach
    void setup() {
        movimiento = new MovimientoEntity();
        movimiento.setId(1l);
    }

    @DisplayName("Test para obtener todos los movimientos cuando existen movimientos")
    @Test
    void testObtenerMovimientos_ExistenMovimientos() {
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();
        movimiento.setId(1l);
        ArrayList<MovimientoEntity> movimientos = new ArrayList<>();
        movimientos.add(movimiento);

        given(movimientoRepository.findAll()).willReturn(movimientos);

        // When
        List<MovimientoEntity> resultado = movimientoService.obtenerMovimientos();

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getId()).isEqualTo(1l);
    }

    @DisplayName("Test para obtener todos los movimientos cuando no existen movimientos")
    @Test
    void testObtenerMovimientos_NoExistenMovimientos() {

        // Given
        ArrayList<MovimientoEntity> movimientos = new ArrayList<>();
        given(movimientoRepository.findAll()).willReturn(movimientos);

        // When
        List<MovimientoEntity> resultado = movimientoService.obtenerMovimientos();

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(0);
    }

    @DisplayName("Test para guardar un movimiento")
    @Test
    void testGuardarMovimiento_ReturnsMovimiento() {
        // Given
        MovimientoEntity movimiento = new MovimientoEntity();
        
        // Mocking SecurityContextHolder
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        
        // Mocking Authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        
        // Mocking username
        String username = "testUser";
        Mockito.when(authentication.getName()).thenReturn(username);
        
        // Mocking UsuarioEntity
        UsuarioEntity usuario = new UsuarioEntity();
        Mockito.when(usuarioService.obtenerUsuarioPorUsuario(username)).thenReturn(usuario);
        
        // Mocking movimientoRepository
        Mockito.when(movimientoRepository.save(movimiento)).thenReturn(movimiento);
        
        // When
        MovimientoEntity result = movimientoService.guardarMovimiento(movimiento);
        
        // Then
        assertEquals(usuario, result.getUsuario());
        Mockito.verify(movimientoRepository).save(movimiento);
    }

    @DisplayName("test de integración creación de movimiento")
    @Test
    void testCrearMovimiento() {
        // Given
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCorreo("correo1@gmail.com");
        usuario.setContrasenna("pass1");
        usuario.setUsuario("usuario1");
    
        MovimientoEntity movimiento = new MovimientoEntity();
        movimiento.setId(1L);
        movimiento.setUsuario(usuario);
        given(movimientoRepository.save(movimiento)).willReturn(movimiento);
    
        // Mocking SecurityContextHolder
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
    
        // Mocking Authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    
        // Mocking username
        String username = "testUser";
        Mockito.when(authentication.getName()).thenReturn(username);
    
        // Mocking usuarioService
        Mockito.when(usuarioService.obtenerUsuarioPorUsuario(username)).thenReturn(usuario);
    
        // When
        MovimientoEntity movimiento1 = movimientoService.guardarMovimiento(movimiento);
    
        // Then
        assertThat(movimiento1.getId()).isEqualTo(1L);
        assertThat(movimiento1.getUsuario().getCorreo()).isEqualTo("correo1@gmail.com");
    }
    

}

package com.app.silvahnosbe.service;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.MovimientoRepository;
import com.app.silvahnosbe.services.MovimientoService;

@ExtendWith(SpringExtension.class)
public class MovimientoServiceTest {
    
    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoService movimientoService;

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
    void testGuardarMovimiento() {
        // Given
        given(movimientoRepository.save(movimiento)).willReturn(movimiento);

        // When
        MovimientoEntity resultado = movimientoService.guardarMovimiento(movimiento);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1l);
    }

    @DisplayName("test de integración creación de movimiento")
    @Test
    void testCrearMovimiento(){
        // given
        LocalEntity local = new LocalEntity();
        local.setId(1l);
        local.setNombre("local1");
        local.setDireccion("direccion1");
        
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCorreo("correo1@gmail.com");
        usuario.setContrasenna("pass1");
        usuario.setUsuario("usuario1");
        
        MovimientoEntity movimiento = new MovimientoEntity();
        movimiento.setId(1l);
        movimiento.setLocal(local);
        movimiento.setUsuario(usuario);
        given(movimientoRepository.save(movimiento)).willReturn(movimiento);

        // when
        MovimientoEntity movimiento1 = movimientoService.guardarMovimiento(movimiento);

        // then
        assertThat(movimiento1.getId()).isEqualTo(1l);
        assertThat(movimiento1.getLocal().getId()).isEqualTo(1l);
        assertThat(movimiento1.getUsuario().getCorreo()).isEqualTo("correo1@gmail.com");

    }

}

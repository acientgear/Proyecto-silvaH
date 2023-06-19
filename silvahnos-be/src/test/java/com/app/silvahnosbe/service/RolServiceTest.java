package com.app.silvahnosbe.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.repositories.RolRepository;
import com.app.silvahnosbe.services.RolService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class RolServiceTest {
    
    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    RolEntity rol;

    @BeforeEach
    void setup() {
        rol = new RolEntity();
        rol.setId(1l);
        rol.setNombre("Rol 1");
    }

    @DisplayName("Test para obtener todos los roles cuando existen roles")
    @Test
    void testObtenerRoles_ExistenRoles() {
        // Given
        RolEntity rol = new RolEntity();
        rol.setNombre("Rol 1");
        ArrayList<RolEntity> roles = new ArrayList<>();
        roles.add(rol);

        given(rolRepository.findAll()).willReturn(roles);

        // When
        List<RolEntity> resultado = rolService.obtenerRol();

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Rol 1");
    }

    @DisplayName("Test para obtener todos los roles cuando no existen roles")
    @Test
    void testObtenerRoles_NoExistenRoles() {

        // Given
        ArrayList<RolEntity> roles = new ArrayList<>();
        given(rolRepository.findAll()).willReturn(roles);

        // When
        List<RolEntity> resultado = rolService.obtenerRol();

        // Then
        assertThat(resultado).isEmpty();
    }

    @DisplayName("test para obtener un Rol por id")
    @Test
    void testObtenerRolPorId() {
    
        given(rolRepository.findById(1L)).willReturn(java.util.Optional.of(rol));
        assertThat(rolService.obtenerRolPorId(1L)).isNotNull();
    }
}

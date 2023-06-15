package com.app.silvahnosbe.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.RolController;
import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.services.RolService;

@ExtendWith(SpringExtension.class)
public class RolControllerTest {

    @InjectMocks
    private RolController rolController;

    @Mock
    private RolService rolService;

    @DisplayName("Test para obtener todos los roles")
    @Test
    public void testGetAllRoles_ExistenRoles_ReturnsList() {
        // Given
        List<RolEntity> roles = new ArrayList<>();
        roles.add(new RolEntity());
        when(rolService.obtenerRol()).thenReturn(roles);

        // When
        ResponseEntity<List<RolEntity>> response = rolController.getRoles();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roles, response.getBody());
    }

    @DisplayName("Test para obtener todos los roles cuando no existen roles")
    @Test
    public void testGetAllRoles_NoExistenRoles_ReturnsNotFound() {
        // Given
        when(rolService.obtenerRol()).thenReturn(null);

        // When
        ResponseEntity<List<RolEntity>> response = rolController.getRoles();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @DisplayName("Test para obtener un rol por id")
    @Test
    public void testGetRolById_ExistenRol_ReturnsRol() {
        // Given
        RolEntity rol = new RolEntity();
        rol.setId(1L);
        when(rolService.obtenerRolPorId(1L)).thenReturn(rol);

        // When
        ResponseEntity<RolEntity> response = rolController.getRol(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rol, response.getBody());
    }

    @DisplayName("Test para obtener un rol por id cuando no existe")
    @Test
    public void testGetRolById_NoExistenRol_ReturnsNotFound() {
        // Given
        when(rolService.obtenerRolPorId(1L)).thenReturn(null);

        // When
        ResponseEntity<RolEntity> response = rolController.getRol(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    
}

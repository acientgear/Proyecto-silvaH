/*package com.app.silvahnosbe.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.LocalController;
import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.services.LocalService;

@ExtendWith(SpringExtension.class)
public class LocalControllerTest {
    
    @InjectMocks
    private LocalController localController;

    @Mock
    private LocalService localService;

    @DisplayName("Test para obtener todos los locales")
    @Test
    void testGetAllLocales_ExistenLocales_ReturnsList() {
        // Given
        List<LocalEntity> locales = new ArrayList<>();
        locales.add(new LocalEntity());
        when(localService.obtenerLocal()).thenReturn(locales);

        // When
        ResponseEntity<List<LocalEntity>> response = localController.getLocales();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locales, response.getBody());
    }

    @DisplayName("Test para obtener todos los locales cuando no existen locales")
    @Test
    void testGetAllLocales_NoExistenLocales_ReturnsNotFound() {
        // Given
        when(localService.obtenerLocal()).thenReturn(null);

        // When
        ResponseEntity<List<LocalEntity>> response = localController.getLocales();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @DisplayName("Test prara crear local")
    @Test
    void testCreateLocal_CreaLocal_ReturnsLocal() {
        // Given
        LocalEntity local = new LocalEntity();
        local.setId(1l);
        local.setNombre("Local 1");
        local.setDireccion("Direccion 1");
        when(localService.guardarLocal(local)).thenReturn(local);

        // When
        ResponseEntity<LocalEntity> response = localController.createLocal(local);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(local, response.getBody());
    }
}
*/
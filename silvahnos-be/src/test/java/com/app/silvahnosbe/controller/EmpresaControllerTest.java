package com.app.silvahnosbe.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.app.silvahnosbe.controllers.EmpresaController;
import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.EmpresaService;
import com.app.silvahnosbe.services.MovimientoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpresaControllerTest {

    @InjectMocks
    private EmpresaController empresaController;

    @Mock
    private MovimientoService movimientoService;

    @Mock
    private EmpresaService empresaService;

    @DisplayName("Test para obtener todos los egresos")
    @Test
    void testGetaAllEmpresas_ExistenEmpresas_ReturnsList() {
        // Given
        List<EmpresaEntity> empresas = new ArrayList<>();
        empresas.add(new EmpresaEntity());
        when(empresaService.obtenerEmpresa()).thenReturn(empresas);

        // When
        ResponseEntity<List<EmpresaEntity>> response = empresaController.getEmpresas();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresas, response.getBody());
    }

    @DisplayName("Test para obtener todos los egresos cuando no existen egresos")
    @Test
    void testGetAllEmpresas_NoExistenEmpresas_ReturnsNotFound() {
        // Given
        when(empresaService.obtenerEmpresa()).thenReturn(null);

        // When
        ResponseEntity<List<EmpresaEntity>> response = empresaController.getEmpresas();

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Test para crear una Empresa con tipo creación")
    @Test
    void testCreateEmpresa_EmpresaCreada_ReturnsEmpresa_Creación() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(null);
        when(empresaService.guardarEmpresa(empresa)).thenReturn(empresa);

        // When
        ResponseEntity<EmpresaEntity> response = empresaController.createEmpresa(empresa);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Creación", capturedMovimiento.getTipo());
        assertEquals("empresa", capturedMovimiento.getNombre_tabla());
        assertEquals(empresa.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una Empresa con tipo modificación")
    @Test
    void testCreateEmpresa_EmpresaCreada_ReturnsEmpresa_Empresa_Modificacion() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(1l);
        empresa.setBorrado(false);
        when(empresaService.guardarEmpresa(empresa)).thenReturn(empresa);

        // When
        ResponseEntity<EmpresaEntity> response = empresaController.createEmpresa(empresa);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Modificación", capturedMovimiento.getTipo());
        assertEquals("empresa", capturedMovimiento.getNombre_tabla());
        assertEquals(empresa.getId(), capturedMovimiento.getId_objeto());
    }

    @DisplayName("Test para crear una Empresa con tipo eliminacion")
    @Test
    void testCreateEmpresa_EmpresaCreada_ReturnsEmpresa_Empresa_Eliminacion() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(1l);
        empresa.setBorrado(true);
        when(empresaService.guardarEmpresa(empresa)).thenReturn(empresa);

        // When
        ResponseEntity<EmpresaEntity> response = empresaController.createEmpresa(empresa);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());

        // Verify movimientoService.guardarMovimiento() is called with the correct arguments
        ArgumentCaptor<MovimientoEntity> movimientoCaptor = ArgumentCaptor.forClass(MovimientoEntity.class);
        verify(movimientoService).guardarMovimiento(movimientoCaptor.capture());

        MovimientoEntity capturedMovimiento = movimientoCaptor.getValue();
        assertEquals("Eliminación", capturedMovimiento.getTipo());
        assertEquals("empresa", capturedMovimiento.getNombre_tabla());
        assertEquals(empresa.getId(), capturedMovimiento.getId_objeto());
    }
    
    @DisplayName("Test para obtener un egreso por id")
    @Test
    void testGetEmpresaById_EmpresaExiste_ReturnsEmpresa() {
        // Given
        EmpresaEntity empresa = new EmpresaEntity();
        when(empresaService.obtenerEmpresaPorId(1L)).thenReturn(empresa);

        // When
        ResponseEntity<EmpresaEntity> response = empresaController.getEmpresaById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());
    }

    @DisplayName("Test para obtener un egreso por id cuando no existe")
    @Test
    void testGetEmpresaById_EmpresaNoExiste_ReturnsNotFound() {
        // Given
        when(empresaService.obtenerEmpresaPorId(1L)).thenReturn(null);

        // When
        ResponseEntity<EmpresaEntity> response = empresaController.getEmpresaById(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
}

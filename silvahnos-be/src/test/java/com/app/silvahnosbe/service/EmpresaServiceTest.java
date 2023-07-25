package com.app.silvahnosbe.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.repositories.EmpresaRepository;
import com.app.silvahnosbe.services.EmpresaService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {
    
    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @DisplayName ("test para obtener todas las empresas cuando existen empresas")
    @Test
    void testObtenerEmpesas_ExistenEmpresas(){
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setNombre("Empresa 1");
        empresa.setRut("12345678-9");
        empresa.setDireccion("Direccion 1");
        
        given(empresaRepository.obtenerEmpresas()).willReturn(new ArrayList<>(List.of(empresa)));
        assertThat(empresaService.obtenerEmpresa()).isNotNull();
    }


    @DisplayName ("test para obtener todas las empresas cuando no existen empresas")
    @Test
    void testObtenerEmpesas_NoExistenEmpresas(){
        given(empresaRepository.obtenerEmpresas()).willReturn(null);
        assertThat(empresaService.obtenerEmpresa()).isNull();
    }

    @DisplayName ("test para obtener una empresa por id")
    @Test
    void testObtenerEmpresaPorId(){
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setNombre("Empresa 1");
        empresa.setRut("12345678-9");
        empresa.setDireccion("Direccion 1");
        empresa.setId(1L);
        
        given(empresaRepository.findById(1L)).willReturn(java.util.Optional.of(empresa));
        assertThat(empresaService.obtenerEmpresaPorId(1L)).isNotNull();
    }

    @DisplayName ("test para guardar una empresa")
    @Test
    void testGuardarEmpresa(){
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setNombre("Empresa 1");
        empresa.setRut("12345678-9");
        empresa.setDireccion("Direccion 1");
        empresa.setId(1L);
        
        given(empresaRepository.save(empresa)).willReturn(empresa);
        assertThat(empresaService.guardarEmpresa(empresa)).isNotNull();
    }
}

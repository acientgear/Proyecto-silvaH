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

import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.repositories.LocalRepository;
import com.app.silvahnosbe.services.LocalService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class LocalServiceTest {
    
    @Mock
    private LocalRepository localRepository;

    @InjectMocks
    private LocalService localService;

    LocalEntity local;

    @BeforeEach
    void setup() {
        local = new LocalEntity();
        local.setId(1l);
        local.setNombre("Local 1");
    }

    @DisplayName("Test para obtener todos los locales cuando existen locales")
    @Test
    void testObtenerLocales_ExistenLocales() {
        // Given
        LocalEntity local = new LocalEntity();
        local.setNombre("Local 1");
        ArrayList<LocalEntity> locales = new ArrayList<>();
        locales.add(local);

        given(localRepository.findAll()).willReturn(locales);

        // When
        List<LocalEntity> resultado = localService.obtenerLocal();

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Local 1");
    }

    @DisplayName("Test para obtener todos los locales cuando no existen locales")
    @Test
    void testObtenerLocales_NoExistenLocales() {
        // Given
        ArrayList<LocalEntity> locales = new ArrayList<>();

        given(localRepository.findAll()).willReturn(locales);

        // When
        List<LocalEntity> resultado = localService.obtenerLocal();

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(0);
    }

    @DisplayName("test para obtener un local por id")
    @Test
    void testObtenerLocalPorId() {
    
        given(localRepository.findById(1L)).willReturn(java.util.Optional.of(local));
        assertThat(localService.obtenerLocalPorId(1L)).isNotNull();
    }

    @DisplayName("Test para guardar un local")
    @Test
    void testGuardarLocal() {
        // Given
        given(localRepository.save(local)).willReturn(local);

        // When
        LocalEntity resultado = localService.guardarLocal(local);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1l);
        assertThat(resultado.getNombre()).isEqualTo("Local 1");
    }

    


}

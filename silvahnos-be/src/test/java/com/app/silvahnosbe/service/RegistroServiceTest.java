package com.app.silvahnosbe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.silvahnosbe.models.Registro;
import com.app.silvahnosbe.repositories.RegistroRepository;
import com.app.silvahnosbe.services.RegistroService;

@ExtendWith(MockitoExtension.class)
public class RegistroServiceTest {

    @Mock
    private RegistroRepository registroRepository;

    @InjectMocks
    private RegistroService registroService;

    Registro registro;

    @BeforeEach
    void setup() {
        registro = new Registro();
        registro.setTipo("Ingreso");
        registro.setFecha(new Date());
        registro.setDescripcion("Cuadratura BMW");
        registro.setMonto(150000);
    }

    @DisplayName("test para obtener registros")
    @Test
    void testObtenerRegistros() {

        Registro registro2 = new Registro();
        registro2.setTipo("Egreso");
        registro2.setFecha(new Date());
        registro2.setDescripcion("Pintura BMW");
        registro2.setMonto(150000);

        // given
        given(registroRepository.obtenerRegistros(2023, 5)).willReturn(new ArrayList<>(List.of(registro, registro2)));
        // when
        List<Registro> registros = registroService.obtenerRegistros(2023, 5);
        // then
        assertThat(registros).isNotNull();
        assertThat(registros.size()).isEqualTo(2);
    }

}

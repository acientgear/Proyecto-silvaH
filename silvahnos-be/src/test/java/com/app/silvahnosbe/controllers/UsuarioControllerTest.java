package com.app.silvahnosbe.controllers;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.CoreMatchers.is;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
public class UsuarioControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarUsuario(){

        //given
        UsuarioEntity usuario= new UsuarioEntity();
        usuario.setNombre("pedro");
        usuario.setId(1L);
        usuario.setEmail("prueba@email.com");
        usuario.setPassword("password");
        given(usuarioService.guardarUsuario(any(UsuarioEntity.class)))
                .willAnswer((invoction) ->invoction.getArgument(0) );


        // when

        ResultActions response = null;
        try {
            response = mockMvc.perform(post("/api/usuario")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(usuario))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //then
        try {
            response.andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.nombre",is(usuario.getNombre())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}




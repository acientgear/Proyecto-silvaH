package com.app.silvahnosbe.controllers;


import com.app.silvahnosbe.services.IngresoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class IngresoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngresoService ingresoService;

    @Autowired
    private ObjectMapper objectMapper;
}

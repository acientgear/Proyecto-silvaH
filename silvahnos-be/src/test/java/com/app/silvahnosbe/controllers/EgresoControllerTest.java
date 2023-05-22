package com.app.silvahnosbe.controllers;

import com.app.silvahnosbe.services.EgresoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class EgresoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EgresoService egresoService;

    @Autowired
    private ObjectMapper objectMapper;
}

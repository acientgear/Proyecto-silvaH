package com.app.silvahnosbe.service;

import com.app.silvahnosbe.services.FacturaService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class FacturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaService facturaService;

    @Autowired
    private ObjectMapper objectMapper;
}

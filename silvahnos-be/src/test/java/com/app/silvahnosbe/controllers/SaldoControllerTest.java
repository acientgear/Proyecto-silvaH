package com.app.silvahnosbe.controllers;

import com.app.silvahnosbe.services.SaldoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class SaldoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaldoService saldoService;

    @Autowired
    private ObjectMapper objectMapper;
}

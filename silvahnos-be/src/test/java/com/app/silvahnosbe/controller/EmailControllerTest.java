package com.app.silvahnosbe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.EmailController;
import com.app.silvahnosbe.util.EmailService;

@ExtendWith(SpringExtension.class)
class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @Test
    void testSendEmail() {
        // Arrange
        Mockito.doNothing().when(emailService).sendEmail();

        // Act
        ResponseEntity<String> response = emailController.sendEmail();

        // Assert
        assertEquals("Correo electronico enviado correctamente", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testSendCron() {
        // Arrange
        Mockito.doNothing().when(emailService).cronEmail();

        // Act
        ResponseEntity<String> response = emailController.sendCron();

        // Assert
        assertEquals("correo electronico enviado correctamente", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}

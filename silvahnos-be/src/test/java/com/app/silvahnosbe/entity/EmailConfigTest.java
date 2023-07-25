package com.app.silvahnosbe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.app.silvahnosbe.entities.EmailConfig;

class EmailConfigTest {
    @Test
    void testId(){
        // Given
        EmailConfig emailConfig = new EmailConfig();

        // When
        emailConfig.setId(1l);
        Long id = emailConfig.getId();

        // Then
        assertEquals(1, id);
        
    }

    @Test
    void testHost(){
        // Given
        EmailConfig emailConfig = new EmailConfig();

        // When
        emailConfig.setHost("host");
        String host = emailConfig.getHost();

        // Then
        assertEquals("host", host);
    }

    @Test
    void testPort(){
        // Given
        EmailConfig emailConfig = new EmailConfig();

        // When
        emailConfig.setPort(12345);
        int port = emailConfig.getPort();

        // Then
        assertEquals(12345, port);
    }

    @Test

    void testUsername(){
        // Given
        EmailConfig emailConfig = new EmailConfig();

        // When
        emailConfig.setUsername("username");
        String username = emailConfig.getUsername();

        // Then
        assertEquals("username", username);
    }

    @Test
    void testPassword(){
        // Given
        EmailConfig emailConfig = new EmailConfig();

        // When
        emailConfig.setPassword("password");
        String password = emailConfig.getPassword();

        // Then
        assertEquals("password", password);
    }

    @Test
    void testTexto(){
        // Given
        EmailConfig emailConfig = new EmailConfig();

        // When
        emailConfig.setTexto("texto");
        String texto = emailConfig.getTexto();

        // Then
        assertEquals("texto", texto);
    }
    
}

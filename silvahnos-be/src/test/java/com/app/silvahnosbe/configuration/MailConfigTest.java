package com.app.silvahnosbe.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.app.silvahnosbe.entities.EmailConfig;
import com.app.silvahnosbe.repositories.EmailConfigRepository;

/*@ExtendWith(MockitoExtension.class)
public class MailConfigTest {
        @Mock
    private EmailConfigRepository emailConfigRepository;

    private MailConfig mailConfig;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testJavaMailSender() {
        // Given
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setHost("smtp.example.com");
        emailConfig.setPort(587);
        emailConfig.setUsername("username");
        emailConfig.setPassword("password");

        when(emailConfigRepository.findById(1L)).thenReturn(Optional.of(emailConfig));

        // When
        JavaMailSender javaMailSender = mailConfig.javaMailSender();

        // Then
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        assertEquals(JavaMailSenderImpl.class, javaMailSender.getClass());
        assertEquals("smtp.example.com", javaMailSender.getHost());
        assertEquals(587, javaMailSender.getPort());
        assertEquals("username", javaMailSender.getUsername());
        assertEquals("password", javaMailSender.getPassword());
    }
    
}*/

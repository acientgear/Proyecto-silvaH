package com.app.silvahnosbe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.app.silvahnosbe.entities.EmailConfig;
import com.app.silvahnosbe.repositories.EmailConfigRepository;
import com.app.silvahnosbe.services.EmailService;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @Mock
    private EmailConfigRepository emailConfigRepository;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        // Configurar el comportamiento del mock del repositorio
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setUsername("correo@ejemplo.com");
        when(emailConfigRepository.findById(1L)).thenReturn(Optional.of(emailConfig));

        // Llamar al método que se está probando
        emailService.sendEmail();

        // Verificar que el método send del mailSender fue llamado con los parámetros
        // correctos
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage message = messageCaptor.getValue();

        assertEquals("correo@ejemplo.com", message.getFrom());
        assertEquals("luis.toro.f@usach.cl", message.getTo()[0]);
        assertEquals("Notificación factura", message.getSubject());
        assertEquals("Factura 131 está por vencer", message.getText());
    }
}

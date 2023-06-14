package com.app.silvahnosbe.services;

import com.app.silvahnosbe.entities.EmailConfig;
import com.app.silvahnosbe.repositories.EmailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailConfigRepository emailConfigRepository;

    public void sendEmail() {
        EmailConfig emailConfig = emailConfigRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuración de correo electrónico no encontrada."));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getUsername());
        message.setTo("ppaillao@gmail.com");
        message.setSubject("probando");
        message.setText("probando");

        mailSender.send(message);
    }

}

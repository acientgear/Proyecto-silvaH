package com.app.silvahnosbe.configuration;

import com.app.silvahnosbe.entities.EmailConfig;
import com.app.silvahnosbe.repositories.EmailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Autowired
    private EmailConfigRepository emailConfigRepository;

    @Bean
    public JavaMailSender javaMailSender() {
        EmailConfig emailConfig = emailConfigRepository.findById(1L).orElseThrow();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");

        return  mailSender;
    }
}

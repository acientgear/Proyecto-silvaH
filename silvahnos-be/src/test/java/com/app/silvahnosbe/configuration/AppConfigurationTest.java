package com.app.silvahnosbe.configuration;

import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppConfigurationTest {

    @Test
    public void testAppPasswordEncoderBean() {
        // Given
        BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        AppConfiguration appConfiguration = new AppConfiguration();

        // When
        PasswordEncoder passwordEncoder = appConfiguration.appPasswordEncoder();

        // Then
        assertEquals(bCryptPasswordEncoder.getClass(), passwordEncoder.getClass());
    }
}








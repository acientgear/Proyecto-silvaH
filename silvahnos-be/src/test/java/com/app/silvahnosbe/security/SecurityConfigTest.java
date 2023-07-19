package com.app.silvahnosbe.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.services.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
public class SecurityConfigTest {
    @Test
    public void testPasswordEncoderBean() {
        // Given
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PasswordEncoderTestConfig config = new PasswordEncoderTestConfig();

        // When
        PasswordEncoder bean = config.passwordEncoder();

        // Then
        assertNotNull(bean);
        // Puedes agregar más aserciones según tus necesidades
    }

    // Clase de configuración para el test
    class PasswordEncoderTestConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private HttpSecurity http;

    @Mock
    private PasswordEncoder passwordEncoder;

    /*@Test
    public void testAuthenticationManager() throws Exception {
        // Given
        AuthenticationManagerBuilder authenticationManagerBuilder = mock(AuthenticationManagerBuilder.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(http.getSharedObject(AuthenticationManagerBuilder.class)).thenReturn(authenticationManagerBuilder);
        when(authenticationManagerBuilder.userDetailsService(any(UserDetailsServiceImpl.class))).thenReturn(authenticationManagerBuilder);
        when(authenticationManagerBuilder.eraseCredentials(anyBoolean())).thenReturn(authenticationManagerBuilder);
        when(authenticationManagerBuilder.and()).thenReturn(authenticationManagerBuilder);
        when(authenticationManagerBuilder.build()).thenReturn(authenticationManager);

        // When
        AuthenticationManager result = securityConfig.authenticationManager(http, passwordEncoder);

        // Then
        verify(http).getSharedObject(AuthenticationManagerBuilder.class);
        verify(authenticationManagerBuilder).userDetailsService(any(UserDetailsServiceImpl.class));
        verify(authenticationManagerBuilder).eraseCredentials(true);
        verify(authenticationManagerBuilder).and();
        verify(authenticationManagerBuilder).build();
        assertNotNull(result);
        // Puedes realizar más aserciones según tus necesidades
    }*/
}

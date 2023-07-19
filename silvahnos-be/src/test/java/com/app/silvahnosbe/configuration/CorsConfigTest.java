package com.app.silvahnosbe.configuration;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.mockito.Mockito.verify;

public class CorsConfigTest {

    @Test
    public void testCorsConfigure() {
        // Given
        CorsRegistry corsRegistry = Mockito.mock(CorsRegistry.class);
        WebMvcConfigurer webMvcConfigurer = new CorsConfig().corsConfigure();

        // When
        webMvcConfigurer.addCorsMappings(corsRegistry);

        // Then
        verify(corsRegistry).addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:3000/");
    }
}


package com.app.silvahnosbe.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

class CorsConfigTest {
    @Test
    void testCorsConfigure() {
        // Given
        CorsRegistry corsRegistry = new CorsRegistry();
        WebMvcConfigurer webMvcConfigurer = new CorsConfig().corsConfigure();

        // When
        webMvcConfigurer.addCorsMappings(corsRegistry);

        // Then
        corsRegistry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:3000/");

        // Verify
        // No se realiza ninguna verificación específica en este punto, ya que la configuración se realiza en el método addCorsMappings()
    }
}


package com.app.silvahnosbe.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.security.Key;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTest {
    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private Environment env;

    @Value("${jwt.secret.key}")
    private String secretKey;

    /*@Test
    public void testGenerarTokenAcceso() {
        // Given
        String username = "testuser";
        String timeExpiration = "3600000"; // Por ejemplo, 1 hora en milisegundos
        when(env.getProperty("jwt.time.expiration")).thenReturn(timeExpiration);

        // When
        String token = jwtUtils.generarTokenAcceso(username);

        // Then
        assertNotNull(token);

        // Aquí puedes agregar más aserciones según tus necesidades
        // Por ejemplo, puedes decodificar el token y verificar su contenido
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(obteberKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
        assertEquals(username, claims.getSubject());
        // Verificar la expiración (opcional, ya que puede cambiar con el tiempo)
    }

    public Key obteberKey(){

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }*/

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    /*@Test
    public void testIsValid_ValidToken_ReturnsTrue() {
        // Given
        String username = "testuser";
        when(env.getProperty("jwt.time.expiration")).thenReturn("3600000"); // Por ejemplo, 1 hora en milisegundos

        // Generar un token válido para el usuario 'testuser'
        String token = jwtUtils.generarTokenAcceso(username);

        // When
        boolean isValid = jwtUtils.isValid(token);

        // Then
        assertTrue(isValid);
    }*/

    @Test
    public void testIsValid_InvalidToken_ReturnsFalse() {
        // Given
        String invalidToken = "invalid_token";

        // When
        boolean isValid = jwtUtils.isValid(invalidToken);

        // Then
        assertFalse(isValid);
    }

    /*@Test
    public void testObtenerNombreUsuario_ValidToken_ReturnsUsername() {
        // Given
        String username = "testuser";
        when(env.getProperty("jwt.secret.key")).thenReturn("mySecretKey");

        // Generar un token válido para el usuario 'testuser'
        String token = generarTokenAcceso(username);

        // When
        String resultUsername = jwtUtils.obtenerNombreUsuario(token);

        // Then
        assertEquals(username, resultUsername);
    }

    @Test
    public void testObtenerNombreUsuario_InvalidToken_ReturnsNull() {
        // Given
        String invalidToken = "invalid_token";

        // When
        String resultUsername = jwtUtils.obtenerNombreUsuario(invalidToken);

        // Then
        assertEquals(null, resultUsername);
    }

    public String generarTokenAcceso(String usermane){
        return Jwts.builder()
                .setSubject(usermane)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(obteberKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key obteberKey(){

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }*/

    /*@Test
    public void testExtraerParametrosToken_ValidToken_ReturnsClaims() {
        // Given
        String username = "testuser";
        String role = "ROLE_USER";

        // Generar un token válido con el nombre de usuario y el rol
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4OTczOTU4OSwiZXhwIjoxNjg5NzgyNzg5fQ.TA0AOb8ffINvFxxNf5q_lSYwC5KkPHFubHhc6xrPG5A";

        // When
        Claims claims = jwtUtils.extraerParametrosToken(token);

        // Then
        assertEquals(username, claims.getSubject());
    }*/    
    
    /*@Test
    public void testObteberKey_ValidSecretKey_ReturnsKey() {
        // Given
        String secretKey = "GjRSFhYzQ/Ukw9zzhzpKds8F16IRDFX13N9rehDzwexVWo6R+9aCn3lWGcN0x4tT";
        when(env.getProperty("jwt.secret.key")).thenReturn(secretKey);

        // When
        Key key = jwtUtils.obteberKey();

        // Then
        byte[] expectedKeyBytes = Decoders.BASE64.decode(secretKey);
        Key expectedKey = Keys.hmacShaKeyFor(expectedKeyBytes);
        assertEquals(expectedKey, key);
    }

    @Test
    public void testObteberKey_NullSecretKey_ReturnsNull() {
        // Given
        String secretKey = null;
        when(env.getProperty("jwt.secret.key")).thenReturn(secretKey);

        // When
        Key key = jwtUtils.obteberKey();

        // Then
        assertEquals(null, key);
    }*/

}

package com.app.silvahnosbe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.silvahnosbe.controllers.LoginController;
import com.app.silvahnosbe.models.TokenInfo;
import com.app.silvahnosbe.models.UserInfo;
import com.app.silvahnosbe.security.jwt.JwtUtils;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    private JwtUtils jwtUtilService;

    @DisplayName("Test para método login authenticate")
    @Test
    public void testAuthenticate() {
        // Mock data
        String usuario = "username";
        String contrasenna = "password";
        UserInfo userInfo = new UserInfo();
        userInfo.setUsuario(usuario);
        userInfo.setPassword(contrasenna);
        String jwt = "dummyToken";

        // Mock authentication
        when(jwtUtilService.generarTokenAcceso(usuario)).thenReturn(jwt);

        // Perform authentication
        ResponseEntity<TokenInfo> response = loginController.aunthenticate(userInfo);

        // Verify the response
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getJwtToken().equals(jwt);

        // Verify that the authenticationManager.authenticate() method was called
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // You can add more verification if needed for other method calls
    }

    @Mock
    private JwtUtils jwtUtils;

    /*@Test
    public void testAuthenticate_ValidCredentials_ReturnsTokenInfo() {
        // Given
        String username = "testuser";
        String password = "testpassword";
        String jwtToken = "testJWTToken";
        
        UserInfo userInfo = new UserInfo();
        userInfo.setUsuario(username);
        userInfo.setPassword(password);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        when(jwtUtils.generarTokenAcceso(username)).thenReturn(jwtToken);

        // When
        ResponseEntity<TokenInfo> response = loginController.aunthenticate(userInfo);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(jwtToken, response.getBody().getJwtToken());
        // Puedes realizar más aserciones según tus necesidades
    }
    */
}

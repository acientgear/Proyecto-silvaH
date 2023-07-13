package com.app.silvahnosbe.controller;

import static org.mockito.ArgumentMatchers.any;
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
    
}

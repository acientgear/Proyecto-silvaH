package com.app.silvahnosbe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.models.TokenInfo;
import com.app.silvahnosbe.models.UserInfo;
import com.app.silvahnosbe.security.jwt.JwtUtils;

@RestController
@CrossOrigin
@RequestMapping("/iniciar_sesion")
public class LoginController {

    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtilService;
    
    @PostMapping
    public ResponseEntity<TokenInfo> aunthenticate (@RequestBody UserInfo userInfo){
        // System.out.println("Usuario: " + userInfo.getUsuario());
        // System.out.println("Contrasenna: " + userInfo.getPassword());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userInfo.getUsuario(), userInfo.getPassword()));

        //final UserDetails userDetails = userDetailsService.loadUserByUsername(userInfo.getUsuario());
        final String jwt = jwtUtilService.generarTokenAcceso(userInfo.getUsuario());
        TokenInfo tokenInfo = new TokenInfo(jwt);
        return ResponseEntity.ok(tokenInfo);
    }
}

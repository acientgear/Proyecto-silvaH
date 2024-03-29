package com.app.silvahnosbe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="login",description = "controlador login")
public class LoginController {

    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtilService;

    @Operation(summary = "auntentica a un usuario ",description = "permite a un usuario identificarse en el sistema retorna un token")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @PostMapping
    public ResponseEntity<TokenInfo> aunthenticate (@RequestBody UserInfo userInfo){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userInfo.getUsuario(), userInfo.getPassword()));

        final String jwt = jwtUtilService.generarTokenAcceso(userInfo.getUsuario());
        TokenInfo tokenInfo = new TokenInfo(jwt);
        return ResponseEntity.ok(tokenInfo);
    }
}

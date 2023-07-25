package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name ="usuario" ,description="controladores de la clase usuario" )
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    public UsuarioController(PasswordEncoder passwordEncoder, UsuarioService usuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    // setters
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "obtiene todos los usuarios ",description = "retorna una lista de usuarios")
    @ApiResponses(value={
        @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
        @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
        
       
    @GetMapping("")
    public ResponseEntity<List<UsuarioEntity>> getUsuarios(){
        List<UsuarioEntity> usuarios = usuarioService.obtenerUsuarios();
        if(usuarios == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarios);
    }

    @Operation(summary = "crea o edita un usuario ",description = "retorna un usuario")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos guardados correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no guardados  ")
    })


    @PostMapping
    public ResponseEntity<UsuarioEntity> createUsuario(@RequestBody UsuarioEntity usuario){

        usuario.setContrasenna(passwordEncoder.encode(usuario.getContrasenna()));
        UsuarioEntity usuarioGuardado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok().body(usuarioGuardado);
    }
}
package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;

@RestController
@CrossOrigin
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


    @GetMapping("")
    public ResponseEntity<List<UsuarioEntity>> getUsuarios(){
        List<UsuarioEntity> usuarios = usuarioService.obtenerUsuarios();
        if(usuarios == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarios);
    }
    @PostMapping
    public ResponseEntity<UsuarioEntity> createUsuario(@RequestBody UsuarioEntity usuario){

        usuario.setContrasenna(passwordEncoder.encode(usuario.getContrasenna()));
        UsuarioEntity usuarioGuardado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok().body(usuarioGuardado);
    }

    /*@GetMapping("/test")
    public ResponseEntity<UsuarioEntity> getUsuario(){
        UsuarioEntity usuarios = usuarioService.obtenerUsuarioPorUsuario("admin");
        if(usuarios == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/test2")
    public ResponseEntity<UsuarioEntity> hol(){
        return ResponseEntity.ok().body(null);
    } */   



}
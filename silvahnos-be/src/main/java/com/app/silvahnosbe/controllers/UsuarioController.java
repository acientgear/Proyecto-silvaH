package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

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
        UsuarioEntity usuarioGuardado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok().body(usuarioGuardado);
    }
}
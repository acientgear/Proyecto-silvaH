package com.app.silvahnosbe.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity <List<UsuarioEntity>> getAllUsuarios(){
        List<UsuarioEntity> usuarios=usuarioService.obtenerUsuarios();
        if(usuarios==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarios); 
    }
    
    @PostMapping 
    public ResponseEntity<UsuarioEntity> crearUsuario(@RequestBody UsuarioEntity usuario){
        UsuarioEntity nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable("id") Long id){
        usuarioService.borrar(id);
        return ResponseEntity.ok().body(null);
    }

}

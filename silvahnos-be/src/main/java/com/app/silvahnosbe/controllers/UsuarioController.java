package com.app.silvahnosbe.controllers;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity <ArrayList<UsuarioEntity>> getAllUsuarios(){
        ArrayList<UsuarioEntity> usuarios=usuarioService.obtenerUsuarios();
        if(usuarios==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarios); 
    }
    
    @PostMapping 
    public ResponseEntity<UsuarioEntity> crearUsuario(@RequestBody UsuarioEntity usuario){
        UsuarioEntity nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok().body(nuevoUsuario);
    }
    
    
}

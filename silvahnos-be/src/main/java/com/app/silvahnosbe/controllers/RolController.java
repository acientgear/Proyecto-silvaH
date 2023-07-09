package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.services.RolService;

@RestController
@CrossOrigin
@RequestMapping("/roles")
public class RolController {
    @Autowired
    RolService rolService;

    @GetMapping("")
    public ResponseEntity<List<RolEntity>> getRoles(){
        List<RolEntity> roles = rolService.obtenerRol();
        if(roles == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolEntity> getRol(@PathVariable("id") long id){
        RolEntity rol = rolService.obtenerRolPorId(id);
        if(rol == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(rol);
    }
}
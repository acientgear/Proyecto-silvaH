package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.services.CorreoService;

@CrossOrigin
@RestController
@RequestMapping("/Correos")
public class CorreoController {
    @Autowired
    CorreoService correoService;

    @GetMapping("")
    public ResponseEntity<List<CorreoEntity>> getParametros(){
        List<CorreoEntity> correos= correoService.obtenerCorreos();
        if(correos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(correos);
    }
}
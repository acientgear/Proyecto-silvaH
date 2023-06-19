package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.services.CorreoService;

@RestController
@RequestMapping("/Correos")
public class CorreoController {
    @Autowired
    CorreoService correoService;

    @GetMapping("")
    public ResponseEntity<List<CorreoEntity>> getCorreos(){
        List<CorreoEntity> correos= correoService.obtenerCorreos();
        if(correos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(correos);
    }

    @PostMapping("/{nuevoCorreo}")
    public ResponseEntity<CorreoEntity> actualizarCorreo(@PathVariable("nuevoCorreo") String nuevoCorreo){
        CorreoEntity correoActualizado = correoService.actualizarCorreo(nuevoCorreo);
        return ResponseEntity.ok().body(correoActualizado);
    }
}
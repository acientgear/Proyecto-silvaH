package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.services.EstadoService;

@RestController
@CrossOrigin
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    EstadoService estadoService;

    @GetMapping("")
    public ResponseEntity<List<EstadoEntity>> getEstados(){
        List<EstadoEntity> estados = estadoService.obtenerEstado();
        if(estados == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoEntity> getEstado(@PathVariable("id") long id){
        EstadoEntity estado = estadoService.obtenerEstadoPorId(id);
        if(estado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(estado);
    }
}
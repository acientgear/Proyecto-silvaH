package com.app.silvahnosbe.controllers;

import java.util.ArrayList;

import org.hibernate.annotations.ConverterRegistrations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.services.EgresoService;

@CrossOrigin
@RestController
@RequestMapping("/egresos")
public class EgresoController {
    @Autowired
    EgresoService egresoService;

    @GetMapping
    public ResponseEntity<ArrayList<EgresoEntity>> getAllEgresos(){
        ArrayList<EgresoEntity> egresos= egresoService.obtenerEgresos();
        if(egresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EgresoEntity> getEgresoById(@PathVariable("id") Long id){
        EgresoEntity egreso = egresoService.obtenerEgresoPorId(id);
        if(egreso == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egreso);
    }

    @PostMapping
    public ResponseEntity<EgresoEntity> createEgreso(@RequestBody EgresoEntity egreso){
        EgresoEntity egresoGuardado = egresoService.guardarEgreso(egreso);
        return ResponseEntity.ok().body(egresoGuardado);
    }
}

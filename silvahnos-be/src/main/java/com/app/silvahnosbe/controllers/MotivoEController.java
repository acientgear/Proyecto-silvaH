package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.services.MotivoEService;

@RestController
@CrossOrigin
@RequestMapping("/motivosE")
public class MotivoEController {
    @Autowired
    MotivoEService motivoEService;

    @PostMapping
    public ResponseEntity<MotivoEEntity> createMotivoE(@RequestBody MotivoEEntity motivoE){
        MotivoEEntity motivoEGuardado = motivoEService.guardarMotivoE(motivoE);
        return ResponseEntity.ok().body(motivoEGuardado);
    }

    @GetMapping("")
    public ResponseEntity<List<MotivoEEntity>> getMotivosE(){
        List<MotivoEEntity> motivosE = motivoEService.obtenerMotivoE();
        if(motivosE == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivosE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotivoEEntity> getMotivoEById(@PathVariable(value = "id") Long id){
        MotivoEEntity motivoE = motivoEService.obtenerMotivoEPorId(id);
        if(motivoE == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivoE);
    }
}
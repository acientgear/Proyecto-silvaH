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
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MotivoEService;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@CrossOrigin
@RequestMapping("/motivosE")
public class MotivoEController {
    @Autowired
    MotivoEService motivoEService;

    @Autowired
    MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MotivoEEntity> createMotivoE(@RequestBody MotivoEEntity motivoE){
        String tipo = ""; 
        if(motivoE.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(motivoE.isBorrado() == true){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        MotivoEEntity motivoEGuardado = motivoEService.guardarMotivoE(motivoE);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,null,null,null,"moivoE",motivoE.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
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
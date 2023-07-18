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

import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MotivoIService;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@CrossOrigin
@RequestMapping("/motivosI")
public class MotivoIController {
    @Autowired
    MotivoIService motivoIService;

    @Autowired
    MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MotivoIEntity> createMotivoI(@RequestBody MotivoIEntity motivoI){
        String tipo = ""; 
        if(motivoI.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(motivoI.isBorrado() == true){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        MotivoIEntity motivoIGuardado = motivoIService.guardarMotivoI(motivoI);
        MovimientoEntity movimiento = new MovimientoEntity(null,
                                                           null,
                                                           tipo,
                                                           "motivoI",
                                                           motivoIGuardado.getId(),
                                                           null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(motivoIGuardado);
    }

    @GetMapping("")
    public ResponseEntity<List<MotivoIEntity>> getMotivosI(){
        List<MotivoIEntity> motivosI = motivoIService.obtenerMotivoI();
        if(motivosI == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivosI);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotivoIEntity> getMotivoIById(@PathVariable(value = "id") Long id){
        MotivoIEntity motivoI = motivoIService.obtenerMotivoIPorId(id);
        if(motivoI == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivoI);
    }
}
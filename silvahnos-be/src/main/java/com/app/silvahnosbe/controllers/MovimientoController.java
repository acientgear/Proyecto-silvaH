package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    MovimientoService movimientoService;

    @GetMapping("")
    public ResponseEntity<List<MovimientoEntity>> getMovimientos(){
        List<MovimientoEntity> movimientos = movimientoService.obtenerMovimientos();
        if(movimientos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movimientos);
    }
    @PostMapping
    public ResponseEntity<MovimientoEntity> createMovimiento(@RequestBody MovimientoEntity movimiento){
        MovimientoEntity moviminetoGuardado = movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(moviminetoGuardado);
    }
}
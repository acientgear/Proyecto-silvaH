package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<MovimientoEntity>> getMovimientos(){
        List<MovimientoEntity> movimientos = movimientoService.obtenerMovimientos();
        if(movimientos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movimientos);
    }
    
}
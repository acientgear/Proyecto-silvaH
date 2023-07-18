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

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.ParametroService;

@RestController
@CrossOrigin
@RequestMapping("/Parametros")
public class ParametroController {
    @Autowired
    ParametroService parametroService;

    @Autowired
    MovimientoService movimientoService;

    @GetMapping("")
    public ResponseEntity<List<ParametroEntity>> getParametros(){
        List<ParametroEntity> parametros= parametroService.obtenerParametros();
        if(parametros == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(parametros);
    }

    @PostMapping
    public ResponseEntity<ParametroEntity> createParametro(@RequestBody ParametroEntity parametro){
        ParametroEntity parametroGuardado = parametroService.guardarParametro(parametro);
        return ResponseEntity.ok().body(parametroGuardado);
    }

    @PostMapping("/{nuevoValor}")
    public ResponseEntity<ParametroEntity> actualizarParametro(@PathVariable("nuevoValor") String nuevoValor){
        String tipo = "Modificación";
        ParametroEntity parametroActualizado = parametroService.actualizarParametro(nuevoValor);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,"parametro",parametroActualizado.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(parametroActualizado);
    }

}
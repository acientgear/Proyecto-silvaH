package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.services.ParametroService;

@RestController
@RequestMapping("/Parametros")
public class ParametroController {
    @Autowired
    ParametroService parametroService;

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
        ParametroEntity parametroActualizado = parametroService.actualizarParametro(nuevoValor);
        return ResponseEntity.ok().body(parametroActualizado);
    }

}
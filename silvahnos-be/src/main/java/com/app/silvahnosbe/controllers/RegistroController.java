package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.models.Registro;
import com.app.silvahnosbe.services.RegistroService;

@RestController
@CrossOrigin
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<Registro>> getRegistroByAnioAndMes(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<Registro> registros = registroService.obtenerRegistros(anio, mes);
        if(registros == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(registros);
    }
}

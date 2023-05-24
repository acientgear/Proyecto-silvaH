package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.models.Monto;
import com.app.silvahnosbe.services.MontoService;


@RestController
@RequestMapping("/montos")
public class MontoController {
    @Autowired
    MontoService montoService;

    @GetMapping("/ingreso/{anio}/{mes}")
    public ResponseEntity<List<Monto>> obtenerMontosIngreso(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        List<Monto> montos = montoService.obtenerMontoIngreso(anio, mes);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }

    @GetMapping("/egreso/{anio}/{mes}")
    public ResponseEntity<List<Monto>> obtenerMontosEgreso(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        List<Monto> montos = montoService.obtenerMontoEgreso(anio, mes);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }
    
}

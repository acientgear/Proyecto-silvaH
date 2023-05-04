package com.app.silvahnosbe.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.services.IngresoService;

@CrossOrigin
@RestController
@RequestMapping("/ingresos")
public class IngresoController {
    @Autowired
    IngresoService ingresoService;

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<ArrayList<IngresoEntity>> getAllIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        ArrayList<IngresoEntity> ingresos= ingresoService.obtenerIngresos(anio, mes);
        if(ingresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingresos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoEntity> getIngresoById(@PathVariable("id") Long id){
        IngresoEntity ingreso = ingresoService.obtenerIngresoPorId(id);
        if(ingreso == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingreso);
    }

    @PostMapping
    public ResponseEntity<IngresoEntity> createIngreso(@RequestBody IngresoEntity ingreso){
        IngresoEntity ingresoGuardado = ingresoService.guardarIngreso(ingreso);
        return ResponseEntity.ok().body(ingresoGuardado);
    }

    @GetMapping("/ultimos")
    public ResponseEntity<ArrayList<IngresoEntity>> getUltimosIngresos(){
        ArrayList<IngresoEntity> ingresos = ingresoService.obtenerUltimosIngresos();
        if(ingresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingresos);
    }

    @GetMapping("/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        Integer total = ingresoService.obtenerTotalIngresosPorMes(anio, mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/monto/{anio}/{mes}/{dia}")
    public ResponseEntity<Integer> getMontoPorDia(@PathVariable("anio") int anio, @PathVariable("mes") int mes, @PathVariable("dia") int dia){
        Integer total = ingresoService.obtenerMontoPorDia(anio,mes,dia);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }
}

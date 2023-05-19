package com.app.silvahnosbe.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.models.MontoOrigenModel;
import com.app.silvahnosbe.models.RegistroModel;
import com.app.silvahnosbe.services.EgresoService;

@CrossOrigin
@RestController
@RequestMapping("/egresos")
public class EgresoController {
    @Autowired
    EgresoService egresoService;

    @GetMapping
    public ResponseEntity<ArrayList<EgresoEntity>> getAllEgresos(){
        ArrayList<EgresoEntity> egresos= egresoService.obtenerEgresos();
        if(egresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EgresoEntity> getEgresoById(@PathVariable("id") Long id){
        EgresoEntity egreso = egresoService.obtenerEgresoPorId(id);
        if(egreso == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egreso);
    }

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<ArrayList<EgresoEntity>> getEgresoByAnioAndMes(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        ArrayList<EgresoEntity> egresos = egresoService.obtenerEgresoPorAnioAndMes(anio, mes);
        if(egresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @PostMapping
    public ResponseEntity<EgresoEntity> createEgreso(@RequestBody EgresoEntity egreso){
        EgresoEntity egresoGuardado = egresoService.guardarEgreso(egreso);
        return ResponseEntity.ok().body(egresoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EgresoEntity> deleteEgreso(@PathVariable("id") Long id){
        EgresoEntity egreso = egresoService.obtenerEgresoPorId(id);
        if(egreso == null){
            return ResponseEntity.notFound().build();
        }
        egresoService.eliminarEgreso(egreso);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ultimos")
    public ResponseEntity<ArrayList<EgresoEntity>> getUltimosEgresos(){
        ArrayList<EgresoEntity> egresos = egresoService.obtenerUltimosEgresos();
        if(egresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @GetMapping("/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalEgresosPorMes(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        Integer total = egresoService.obtenerTotalEgresosPorMes(anio, mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/monto/{anio}/{mes}/{dia}")
    public ResponseEntity<Integer> getMontoPorDia(@PathVariable("anio") int anio, @PathVariable("mes") int mes, @PathVariable("dia") int dia){
        Integer total = egresoService.obtenerMontoPorDia(anio,mes,dia);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/ingresos/{anio}/{mes}")
    public ArrayList<RegistroModel> getEgresosIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        ArrayList<RegistroModel> registros = egresoService.obtenerEgresosIngresos(anio,mes);
        return registros;
    }

    @GetMapping("/origen/{anio}/{mes}")
    public ArrayList<MontoOrigenModel> getEgresosPorOrigen(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        ArrayList<MontoOrigenModel> montosOrigen = egresoService.obtenerMontoOrigen(anio,mes);
        return montosOrigen;
    }
}

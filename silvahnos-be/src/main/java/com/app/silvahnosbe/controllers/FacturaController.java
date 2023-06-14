package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.services.FacturaService;

@CrossOrigin
@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<FacturaEntity>> getFacturas(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<FacturaEntity> facturas= facturaService.obtenerFacturas(anio, mes);
        if(facturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(facturas);
    }

    @PostMapping
    public ResponseEntity<FacturaEntity> createFactura(@RequestBody FacturaEntity factura){
        FacturaEntity facturaGuardada = facturaService.guardarFactura(factura);
        return ResponseEntity.ok().body(facturaGuardada);
    }

    @GetMapping("/iva/{anio}/{mes}")
    public ResponseEntity<Integer> getIva(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        Integer iva = facturaService.obtenerIva(anio, mes);
        if(iva == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(iva);
    }

    @GetMapping("/proximasVencer/{anio}/{mes}")
    public ResponseEntity<List<FacturaEntity>> getProximasVencer(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<FacturaEntity> facturas= facturaService.obtenerProximasVencer(anio, mes);
        if(facturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(facturas);
    }

}
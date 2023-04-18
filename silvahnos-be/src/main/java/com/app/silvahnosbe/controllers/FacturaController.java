package com.app.silvahnosbe.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.services.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @GetMapping
    public ResponseEntity<ArrayList<FacturaEntity>> getAllFacturas(){
        ArrayList<FacturaEntity> facturas= facturaService.obtenerFacturas();
        if(facturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaEntity> getFacturaById(@PathVariable("id") Long id){
        FacturaEntity factura = facturaService.obtenerFacturaPorId(id);
        if(factura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(factura);
    }

    @PostMapping
    public ResponseEntity<FacturaEntity> createFactura(@RequestBody FacturaEntity factura){
        FacturaEntity facturaGuardada = facturaService.guardarFactura(factura);
        return ResponseEntity.ok().body(facturaGuardada);
    }
}
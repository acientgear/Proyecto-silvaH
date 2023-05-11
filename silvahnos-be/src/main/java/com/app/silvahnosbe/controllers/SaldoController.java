package com.app.silvahnosbe.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.silvahnosbe.models.SaldoModel;
import com.app.silvahnosbe.services.SaldoService;

@CrossOrigin
@Controller
@RequestMapping("/saldo")
public class SaldoController {
    @Autowired
    private SaldoService saldoService;

    @GetMapping("/{anio}")
    public ResponseEntity<ArrayList<SaldoModel>> getAllSaldos(@PathVariable ("anio") Integer anio){
        ArrayList<SaldoModel> saldos = saldoService.obtenerSaldos(anio);
        if(saldos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(saldos);
    }

    @GetMapping("/{mes}/{anio}")
    public ResponseEntity<Integer> getSaldoCuenta(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        Integer saldoMesAnio = saldoService.obtenerSaldoCuenta(anio, mes);
        return ResponseEntity.ok().body(saldoMesAnio);
    }
}

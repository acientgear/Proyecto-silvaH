package com.app.silvahnosbe.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.silvahnosbe.entities.SaldoEntity;
import com.app.silvahnosbe.services.SaldoService;

@CrossOrigin
@Controller
@RequestMapping("/saldo")
public class SaldoController {
    @Autowired
    private SaldoService saldoService;

    @RequestMapping
    public ResponseEntity<ArrayList<SaldoEntity>> getAllSaldos(){
        ArrayList<SaldoEntity> saldos = saldoService.obtenerSaldos();
        if(saldos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(saldos);
    }
}

package com.app.silvahnosbe.controllers;

import java.util.List;

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

import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.services.LocalService;

@RestController
@RequestMapping("/locales")
public class LocalController {
    @Autowired
    LocalService localService;

    @PostMapping
    public ResponseEntity<LocalEntity> createLocal(@RequestBody LocalEntity local){
        LocalEntity localGuardado = localService.guardarLocal(local);
        return ResponseEntity.ok().body(localGuardado);
    }

    @GetMapping("")
    public ResponseEntity<List<LocalEntity>> getLocales(){
        List<LocalEntity> locales = localService.obtenerLocal();
        if(locales == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(locales);
    }
}
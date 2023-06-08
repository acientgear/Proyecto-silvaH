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

import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaEntity> createEmpresa(@RequestBody EmpresaEntity empresa){
        EmpresaEntity empresaGuardado = empresaService.guardarEmpresa(empresa);
        return ResponseEntity.ok().body(empresaGuardado);
    }

    @GetMapping("")
    public ResponseEntity<List<EmpresaEntity>> getEmpresas(){
        List<EmpresaEntity> empresas = empresaService.obtenerEmpresa();
        if(empresas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaEntity> getEmpresaById(@PathVariable(value = "id") Long id){
        EmpresaEntity empresa = empresaService.obtenerEmpresaPorId(id);
        if(empresa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(empresa);
    }
}
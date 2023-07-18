package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.EmpresaService;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@CrossOrigin
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<EmpresaEntity> createEmpresa(@RequestBody EmpresaEntity empresa){
        String tipo = ""; 
        if(empresa.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(empresa.isBorrado() == true){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        EmpresaEntity empresaGuardado = empresaService.guardarEmpresa(empresa);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,null,null,null,"empresa",empresa.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
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
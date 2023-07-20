package com.app.silvahnosbe.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Empresa" , description = "controladores de la entidad empresa , requerido en facturas")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    MovimientoService movimientoService;


    @Operation(summary = "registra una empresa ",description = "registra una empresa en la base de datos y la retorna ")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos guarados correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no guardados ")
    })
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
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,"empresa",empresaGuardado.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(empresaGuardado);
    }


    @Operation(summary = "obtiene todas la empresas ",description = "retorna una lista con todas las empresas")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<EmpresaEntity>> getEmpresas(){
        List<EmpresaEntity> empresas = empresaService.obtenerEmpresa();
        if(empresas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(empresas);
    }

    @Operation(summary = "busca una empresa ",description = "retorna una empresa segun si ID ")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaEntity> getEmpresaById(@PathVariable(value = "id") Long id){
        EmpresaEntity empresa = empresaService.obtenerEmpresaPorId(id);
        if(empresa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(empresa);
    }
}
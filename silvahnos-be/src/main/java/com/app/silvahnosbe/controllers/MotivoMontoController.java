package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.services.MotivoMontoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.app.silvahnosbe.models.MotivoMonto;

@RestController
@CrossOrigin
@RequestMapping("/motivoMonto")
@Tag(name="motivoMonto",description = "controladores de entidad motivoMonto")
public class MotivoMontoController {
    
    @Autowired
    MotivoMontoService montivoMontoService;

    @Operation(summary = "obtiene los montos de los ingresos ",description = "retorna una lista con los montos de los ingresos por mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/ingreso/{anio}/{mes}")
    public ResponseEntity<List<MotivoMonto>> obtenerMontosIngreso(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        List<MotivoMonto> montos = montivoMontoService.obtenerMontoIngreso(anio, mes);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }

    @Operation(summary = "obtiene los montos de los egresos ",description = "retorna una lista con los montos de los egresos por mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/egreso/{anio}/{mes}")
    public ResponseEntity<List<MotivoMonto>> obtenerMontosEgreso(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        List<MotivoMonto> montos = montivoMontoService.obtenerMontoEgreso(anio, mes);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }
}

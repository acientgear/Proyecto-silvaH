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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.models.Monto;
import com.app.silvahnosbe.services.MontoService;


@RestController
@CrossOrigin
@RequestMapping("/montos")
@Tag(name="monto",description = "controladores de entidad monto")
public class MontoController {
    @Autowired
    MontoService montoService;

    @Operation(summary = "obtiene los montos de los ingresos ",description = "retorna una lista con los montos de los ingresos por mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/ingreso/{anio}/{mes}")
    public ResponseEntity<List<Monto>> obtenerMontosIngreso(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        List<Monto> montos = montoService.obtenerMontoIngreso(anio, mes);
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
    public ResponseEntity<List<Monto>> obtenerMontosEgreso(@PathVariable("anio") Integer anio, @PathVariable("mes") Integer mes){
        List<Monto> montos = montoService.obtenerMontoEgreso(anio, mes);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }

    @GetMapping("/ingresos/totalMesAnual/{anio}")
    public ResponseEntity<List<Monto>> obtenerMontosIngresoTotalMesAnual(@PathVariable("anio") Integer anio){
        List<Monto> montos = montoService.obtenerMontoIngresoTotalMesAnual(anio);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }

    @GetMapping("/egresos/totalMesAnual/{anio}")
    public ResponseEntity<List<Monto>> obtenerMontosEgresoTotalMesAnual(@PathVariable("anio") Integer anio){
        List<Monto> montos = montoService.obtenerMontoEgresoTotalMesAnual(anio);
        if(montos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(montos);
    }
    
}

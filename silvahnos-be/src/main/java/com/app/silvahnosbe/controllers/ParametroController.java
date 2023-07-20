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

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.ParametroService;

@RestController
@CrossOrigin
@RequestMapping("/Parametros")
@Tag(name="Parametros" ,description = "controladores de la entidad parametro, esta entidad guarda la cantidad de dias antes de que venza una factura para su notificacion")
public class ParametroController {
    @Autowired
    ParametroService parametroService;

    @Autowired
    MovimientoService movimientoService;


    @Operation(summary = "obtener parametro ",description = "retorna una lista con los parametros existentes ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<ParametroEntity>> getParametros(){
        List<ParametroEntity> parametros= parametroService.obtenerParametros();
        if(parametros == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(parametros);
    }

    @Operation(summary = "crea un parametro ",description = "registra un nuevo parametro ne la base de datos y lo retorna ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos guardaos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos guardados encontrados ")
    })

    @PostMapping
    public ResponseEntity<ParametroEntity> createParametro(@RequestBody ParametroEntity parametro){
        ParametroEntity parametroGuardado = parametroService.guardarParametro(parametro);
        return ResponseEntity.ok().body(parametroGuardado);
    }

    @Operation(summary = "actualiza un parametro ",description = "permite actualizar un parametro existente  ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @PostMapping("/{nuevoValor}")
    public ResponseEntity<ParametroEntity> actualizarParametro(@PathVariable("nuevoValor") String nuevoValor){
        String tipo = "Modificación";
        ParametroEntity parametroActualizado = parametroService.actualizarParametro(nuevoValor);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,"parametro",parametroActualizado.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(parametroActualizado);
    }

}
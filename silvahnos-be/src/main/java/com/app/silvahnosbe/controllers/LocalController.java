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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.services.LocalService;

@RestController
@CrossOrigin
@RequestMapping("/locales")
@Tag(name="Local" ,description = "controladores de la entidad local")
public class LocalController {
    @Autowired
    LocalService localService;


    @Operation(summary = "registra un nuevo local ",description = "permite la creacion de un nuevo local y retorna el local creado")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @PostMapping
    public ResponseEntity<LocalEntity> createLocal(@RequestBody LocalEntity local){
        LocalEntity localGuardado = localService.guardarLocal(local);
        return ResponseEntity.ok().body(localGuardado);
    }


    @Operation(summary = "obtiene todos los locales ",description = "retorna una lista con todos los locales registrados ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("")
    public ResponseEntity<List<LocalEntity>> getLocales(){
        List<LocalEntity> locales = localService.obtenerLocal();
        if(locales == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(locales);
    }
}
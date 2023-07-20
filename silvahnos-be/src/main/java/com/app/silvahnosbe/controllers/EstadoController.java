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

import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.services.EstadoService;

@RestController
@CrossOrigin
@RequestMapping("/estados")
@Tag(name="Estado",description = "controladores de la tabla estado")
public class EstadoController {
    @Autowired
    EstadoService estadoService;


    @Operation(summary = "obtiene los estado ",description = "retorna una lista de estado , pueden ser 3 ")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<EstadoEntity>> getEstados(){
        List<EstadoEntity> estados = estadoService.obtenerEstado();
        if(estados == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(estados);
    }

    @Operation(summary = "obtiene un estado ",description = "retorna el estado buscado por su id ")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstadoEntity> getEstado(@PathVariable("id") long id){
        EstadoEntity estado = estadoService.obtenerEstadoPorId(id);
        if(estado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(estado);
    }
}
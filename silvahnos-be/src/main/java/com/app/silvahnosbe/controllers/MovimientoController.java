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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/movimientos")
@Tag(name="movimientos" , description = "controladores de la entidad movimiento, para mantener un registro de los movimientos hechos se creo esta entidad como un log ")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "obtiene los movimientos ",description = "retorna una lista de movimientos ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<MovimientoEntity>> getMovimientos(){
        List<MovimientoEntity> movimientos = movimientoService.obtenerMovimientos();
        if(movimientos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movimientos);
    }
    
}
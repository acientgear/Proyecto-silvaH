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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.CorreoService;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@CrossOrigin
@Tag(name ="Correo" ,description="controladores de la clase correo" )
@RequestMapping("/Correos")
public class CorreoController {
    @Autowired
    CorreoService correoService;

    @Autowired
    MovimientoService movimientoService;


    @Operation(summary = "obtiene los correos ",description = "retorna una lista de correos")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("")
    public ResponseEntity<List<CorreoEntity>> getCorreos(){
        List<CorreoEntity> correos= correoService.obtenerCorreos();
        if(correos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(correos);
    }


    @Operation(summary = "guarda un correo ",description = "retorna un correo ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos guardados correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no guardados ")
    })

    @PostMapping("/{nuevoCorreo}")
    public ResponseEntity<CorreoEntity> actualizarCorreo(@PathVariable("nuevoCorreo") String nuevoCorreo){
        CorreoEntity correoActualizado = correoService.actualizarCorreo(nuevoCorreo);
        MovimientoEntity movimiento = new MovimientoEntity(null, 
                                                        null,
                                                        "Modificación",
                                                        "correo",
                                                        correoActualizado.getId(),
                                                        null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(correoActualizado);
    }
}
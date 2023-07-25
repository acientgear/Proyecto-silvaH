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

import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MotivoIService;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@CrossOrigin
@RequestMapping("/motivosI")
@Tag(name = "motivos ingresos" , description = "controladores de motivos ingresos")
public class MotivoIController {
    @Autowired
    MotivoIService motivoIService;

    @Autowired
    MovimientoService movimientoService;


    @Operation(summary = "crea un motivo de ingreso ",description = "registra un motivo de ingreso y lo retorna ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @PostMapping
    public ResponseEntity<MotivoIEntity> createMotivoI(@RequestBody MotivoIEntity motivoI){
        String tipo = ""; 
        if(motivoI.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(motivoI.isBorrado()){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        MotivoIEntity motivoIGuardado = motivoIService.guardarMotivoI(motivoI);
        MovimientoEntity movimiento = new MovimientoEntity(null,
                                                           null,
                                                           tipo,
                                                           "motivoI",
                                                           motivoIGuardado.getId(),
                                                           null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(motivoIGuardado);
    }



    @Operation(summary = "obtiene todos los motivos de ingresos ",description = "retorna una lista con los motivos de ingresos ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<MotivoIEntity>> getMotivosI(){
        List<MotivoIEntity> motivosI = motivoIService.obtenerMotivoI();
        if(motivosI == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivosI);
    }

    @Operation(summary = "obtiene un motivo de ingreso ",description = "si encuentra el motivo lo retorna si no lo encuentra retorna nulll ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MotivoIEntity> getMotivoIById(@PathVariable(value = "id") Long id){
        MotivoIEntity motivoI = motivoIService.obtenerMotivoIPorId(id);
        if(motivoI == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivoI);
    }
}
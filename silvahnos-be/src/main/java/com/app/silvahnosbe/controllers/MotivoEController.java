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

import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.MotivoEService;
import com.app.silvahnosbe.services.MovimientoService;

@RestController
@CrossOrigin
@RequestMapping("/motivosE")
@Tag(name="motivos Egreso0" , description = "controladores de los motivos de un egreso")
public class MotivoEController {
    @Autowired
    MotivoEService motivoEService;

    @Autowired
    MovimientoService movimientoService;

    @Operation(summary = "registrar un motivo ",description = "permite registrar un nuevo motivo y retorna el motivo creado")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos guardado correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados "),
            @ApiResponse(responseCode = "403",description = "acceso no autorizado")
    })
    @PostMapping
    public ResponseEntity<MotivoEEntity> createMotivoE(@RequestBody MotivoEEntity motivoE){
        String tipo = ""; 
        if(motivoE.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(motivoE.isBorrado()){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        MotivoEEntity motivoEGuardado = motivoEService.guardarMotivoE(motivoE);
        MovimientoEntity movimiento = new MovimientoEntity(null,
                                                           null,
                                                           tipo,
                                                           "motivoE",
                                                           motivoEGuardado.getId(),
                                                           null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(motivoEGuardado);
    }

    @Operation(summary = "obtiene los motivos de egresos",description = "retorna una lista con todos los motivos de egreso ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<MotivoEEntity>> getMotivosE(){
        List<MotivoEEntity> motivosE = motivoEService.obtenerMotivoE();
        if(motivosE == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivosE);
    }

    @Operation(summary = "obtiene un motivo de  egresos ",description = "")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/{id}")
    public ResponseEntity<MotivoEEntity> getMotivoEById(@PathVariable(value = "id") Long id){
        MotivoEEntity motivoE = motivoEService.obtenerMotivoEPorId(id);
        if(motivoE == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(motivoE);
    }
}
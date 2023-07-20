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

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.services.RolService;

@RestController
@CrossOrigin
@RequestMapping("/roles")
@Tag(name="roles",description = "contralador de los roles de los usuarios")
public class RolController {
    @Autowired
    RolService rolService;

    @Operation(summary = "obtiene los roles ",description = "retorna una lista con los roles ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("")
    public ResponseEntity<List<RolEntity>> getRoles(){
        List<RolEntity> roles = rolService.obtenerRol();
        if(roles == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roles);
    }

    @Operation(summary = "obtiene un rol ",description = "busca un rol segun su id si no lo encuentra retorna null ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RolEntity> getRol(@PathVariable("id") long id){
        RolEntity rol = rolService.obtenerRolPorId(id);
        if(rol == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(rol);
    }
}
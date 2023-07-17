package com.app.silvahnosbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<MovimientoEntity>> getMovimientos(){
        List<MovimientoEntity> movimientos = movimientoService.obtenerMovimientos();
        if(movimientos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movimientos);
    }
    
    @PostMapping
    public ResponseEntity<MovimientoEntity> createMovimiento(@RequestBody MovimientoEntity movimiento) {
        
        // Obtener el id del usuario logueado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Usuario logueado: " + username);
        
        //obtener el usuario logueado
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorUsuario(username);
        movimiento.setUsuario(usuario);
    
        // Guardar el movimiento y devolver la respuesta
        MovimientoEntity movimientoGuardado = movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(movimientoGuardado);
    }
    
}
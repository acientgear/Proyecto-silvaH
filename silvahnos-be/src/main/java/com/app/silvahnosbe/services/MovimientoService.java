package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.MovimientoRepository;

@Service
public class MovimientoService {
    
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    UsuarioService usuarioService;

    public List<MovimientoEntity> obtenerMovimientos(){

        return (List<MovimientoEntity>) movimientoRepository.findAll();
    }

    public MovimientoEntity guardarMovimiento(MovimientoEntity movimiento){
        // Obtener el id del usuario logueado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        //obtener el usuario logueado
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorUsuario(username);
        movimiento.setUsuario(usuario);
        return movimientoRepository.save((movimiento));
    }
}
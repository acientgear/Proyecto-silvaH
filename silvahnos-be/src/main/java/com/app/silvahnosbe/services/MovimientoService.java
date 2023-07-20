package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.MovimientoRepository;


/**
 *
 *servicio de movimientos
 * @author ignacio gres
 * @author Leo vergara
 *
 */

@Service
public class MovimientoService {
    
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    UsuarioService usuarioService;


    /**
     *funcion que permite obtener los movimientos realizados por todos los usuarios
     * @param null
     * @return lista de movimientos
     *
     */

    public List<MovimientoEntity> obtenerMovimientos(){

        return (List<MovimientoEntity>) movimientoRepository.findAll();
    }


    /**
     *funcion que permite guardar un movmiento realizado por un usuario
     * @param moviemto
     * @return movimeinto creado
     */

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
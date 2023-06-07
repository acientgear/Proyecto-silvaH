package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.repositories.MovimientoRepository;

@Service
public class MovimientoService {
    
    @Autowired
    MovimientoRepository movimientoRepository;

    public List<MovimientoEntity> obtenerMovimientos(){

        return (List<MovimientoEntity>) movimientoRepository.findAll();
    }

    public MovimientoEntity guardarMovimiento(MovimientoEntity movimiento){
        return movimientoRepository.save((movimiento));
    }
}
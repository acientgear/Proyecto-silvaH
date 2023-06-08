package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.repositories.EstadoRepository;

@Service
public class EstadoService {
    
    @Autowired
    EstadoRepository estadoRepository;

    public List<EstadoEntity> obtenerEstado(){
        return (List<EstadoEntity>) estadoRepository.findAll();
    }

    public EstadoEntity obtenerEstadoPorId(Long id){
        return estadoRepository.findById(id).orElse(null);
    }
}
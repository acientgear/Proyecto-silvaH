package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.repositories.CorreoRepository;

@Service
public class CorreoService {
    
    @Autowired
    CorreoRepository correoRepository;

    public List<CorreoEntity> obtenerCorreos(){
        return (List<CorreoEntity>) correoRepository.findAll();
    }

    public CorreoEntity obtenerCorreoPorId(Long id){
        return correoRepository.findById(id).orElse(null);
    }
}
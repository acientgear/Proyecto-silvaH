package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.repositories.ParametroRepository;

@Service
public class ParametroService {
    
    @Autowired
    ParametroRepository parametroRepository;

    public List<ParametroEntity> obtenerParametros(){
        return (List<ParametroEntity>) parametroRepository.findAll();
    }

    public ParametroEntity obtenerParametroPorId(Long id){
        return parametroRepository.findById(id).orElse(null);
    }

    public ParametroEntity guardarParametro(ParametroEntity parametro){
        return parametroRepository.save(parametro);
    }

    public ParametroEntity actualizarParametro(String nuevoValor){
        ParametroEntity parametro = parametroRepository.findById(1L).orElse(null);
        parametro.setValor(nuevoValor);
        return parametroRepository.save(parametro);
    }
}
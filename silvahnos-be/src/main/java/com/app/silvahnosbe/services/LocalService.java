package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.LocalEntity;
import com.app.silvahnosbe.repositories.LocalRepository;

@Service
public class LocalService {
    
    @Autowired
    LocalRepository localRepository;

    public List<LocalEntity> obtenerLocal(){

        return (List<LocalEntity>) localRepository.findAll();
    }

    public LocalEntity obtenerLocalPorId(Long id){
        return localRepository.findById(id).orElse(null);
    }

    public LocalEntity guardarLocal(LocalEntity local){
        return localRepository.save((local));
    }
}
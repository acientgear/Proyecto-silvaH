package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.repositories.MotivoIRepository;

@Service
public class MotivoIService {
    
    @Autowired
    MotivoIRepository motivoIRepository;

    public List<MotivoIEntity> obtenerMotivoI(){

        return (List<MotivoIEntity>) motivoIRepository.findAll();
    }

    public MotivoIEntity obtenerMotivoIPorId(Long id){
        return motivoIRepository.findById(id).orElse(null);
    }

    public MotivoIEntity guardarMotivoI(MotivoIEntity motivoI){
        return motivoIRepository.save((motivoI));
    }
}
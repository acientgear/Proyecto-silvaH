package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.repositories.MotivoERepository;

@Service
public class MotivoEService {
    
    @Autowired
    MotivoERepository motivoERepository;

    public List<MotivoEEntity> obtenerMotivoE(){

        return (List<MotivoEEntity>) motivoERepository.findAll();
    }

    public MotivoEEntity obtenerMotivoEPorId(Long id){
        return motivoERepository.findById(id).orElse(null);
    }

    public MotivoEEntity guardarMotivoE(MotivoEEntity motivoE){
        return motivoERepository.save((motivoE));
    }
}
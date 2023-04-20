package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;

@Service
public class EgresoService {
    
    @Autowired
    EgresoRepository egresoRepository;

    public ArrayList<EgresoEntity> obtenerEgresos(){
        return (ArrayList<EgresoEntity>) egresoRepository.findAll();
    }

    public EgresoEntity obtenerEgresoPorId(Long id){
        return egresoRepository.findById(id).orElse(null);
    }

    public EgresoEntity guardarEgreso(EgresoEntity egreso){
        return egresoRepository.save((egreso));
    }

    public void eliminarEgreso(EgresoEntity egreso){
        egreso.setBorrado(true);
    }
}

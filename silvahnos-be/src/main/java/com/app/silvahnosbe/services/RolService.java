package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.repositories.RolRepository;

@Service
public class RolService {
    
    @Autowired
    RolRepository rolRepository;

    public List<RolEntity> obtenerRol(){
        return (List<RolEntity>) rolRepository.findAll();
    }

    public RolEntity obtenerRolPorId(Long id){
        return rolRepository.findById(id).orElse(null);
    }
}
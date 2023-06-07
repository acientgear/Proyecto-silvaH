package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.repositories.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    EmpresaRepository empresaRepository;

    public List<EmpresaEntity> obtenerEmpresa(){

        return (List<EmpresaEntity>) empresaRepository.findAll();
    }

    public EmpresaEntity obtenerEmpresaPorId(Long id){
        return empresaRepository.findById(id).orElse(null);
    }

    public EmpresaEntity guardarEmpresa(EmpresaEntity empresa){
        return empresaRepository.save((empresa));
    }
}
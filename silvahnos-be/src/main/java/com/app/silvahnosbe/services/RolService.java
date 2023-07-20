package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.RolEntity;
import com.app.silvahnosbe.repositories.RolRepository;

/**
 *serivico de rol
 * @author Ignacio Grez
 *
 */

@Service
public class RolService {
    
    @Autowired
    RolRepository rolRepository;


    /**
     * funciones que obtiene todos los roles
     * @param null
     * @return lista de roles
     */

    public List<RolEntity> obtenerRol(){
        return (List<RolEntity>) rolRepository.findAll();
    }

    /**
     * funcion que retorna un rol por su id
     * @param id long
     * @return retorna un rol si existe el id
     */


    public RolEntity obtenerRolPorId(Long id){
        return rolRepository.findById(id).orElse(null);
    }
}
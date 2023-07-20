package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EmpresaEntity;
import com.app.silvahnosbe.repositories.EmpresaRepository;

/**
 * servios de entidad empresa
 * @author Ignacio Grez
 */


@Service
public class EmpresaService {
    
    @Autowired
    EmpresaRepository empresaRepository;


    /**
     *funcion que lista todas las empresas registradas
     * @param null
     * @return retorna una lista con las empresas encontradas
     */

    public List<EmpresaEntity> obtenerEmpresa(){

        return (List<EmpresaEntity>) empresaRepository.obtenerEmpresas();
    }

    /**
     *funcion que obtiene una empresa por su id
     * @param int long
     * @return retorna la empresa si la encuentra
     */


    public EmpresaEntity obtenerEmpresaPorId(Long id){
        return empresaRepository.findById(id).orElse(null);
    }


    /**
     *funcion que guarda una empresa
     * @param empresa contiene nombre de la empresa
     * @return retorna la empresa si fue guardada correctamente
     */

    public EmpresaEntity guardarEmpresa(EmpresaEntity empresa){
        return empresaRepository.save((empresa));
    }
}
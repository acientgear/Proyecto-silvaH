package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.ParametroEntity;
import com.app.silvahnosbe.repositories.ParametroRepository;

/**
 *
 * servicio parametro
 *
 * @author Ignacio Grez
 */

@Service
public class ParametroService {
    
    @Autowired
    ParametroRepository parametroRepository;


    /**
     * funcion que obtiene todos los parametros
     * @param null
     * @return una lista con todos los parametros
     *
     */

    public List<ParametroEntity> obtenerParametros(){
        return (List<ParametroEntity>) parametroRepository.findAll();
    }


    /**
     *funcion que obtiene un parametro por id
     * @param id long
     * @return retorna el parametro si existe la id
     */

    public ParametroEntity obtenerParametroPorId(Long id){
        return parametroRepository.findById(id).orElse(null);
    }


    /**
     * funcion que permite guardar un parametro
     * @param parametro
     * @return retorna el parametro si fue guardado correctamente
     */

    public ParametroEntity guardarParametro(ParametroEntity parametro){
        return parametroRepository.save(parametro);
    }


    /**
     * funcion que permite actualizar un parametro
     * @param nuevoValor string
     * @return retorna el parametro actualizado
     *
     */

    public ParametroEntity actualizarParametro(String nuevoValor){
        ParametroEntity parametro = parametroRepository.findById(1L).orElse(null);
        parametro.setValor(nuevoValor);
        return parametroRepository.save(parametro);
    }
}
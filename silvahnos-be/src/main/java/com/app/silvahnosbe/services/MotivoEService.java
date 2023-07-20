package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MotivoEEntity;
import com.app.silvahnosbe.repositories.MotivoERepository;

/**
 * servicios de la entidad motivo
 * @author Ignacio Grez
 *
 */

@Service
public class MotivoEService {
    
    @Autowired
    MotivoERepository motivoERepository;


    /**
     * funcion que permite obtener todos los motivos de egresos
     * @Param null
     * @return devuelve una lista con todos los motivos de egresos
     *
    * */


    public List<MotivoEEntity> obtenerMotivoE(){

        return (List<MotivoEEntity>) motivoERepository.obtenerMotivosE();
    }

    /**
     * funcion que permite obtener un motivo por su id
     * @param id long
     * @return retorna un motivo si concide con la id
     */

    public MotivoEEntity obtenerMotivoEPorId(Long id){
        return motivoERepository.findById(id).orElse(null);
    }

    /**
     *funcion que permite registrar un nuevo motivo
     * @param motivoE
     * @return retorna el motivo si fue guardado exitosamente
     */

    public MotivoEEntity guardarMotivoE(MotivoEEntity motivoE){
        return motivoERepository.save((motivoE));
    }
}
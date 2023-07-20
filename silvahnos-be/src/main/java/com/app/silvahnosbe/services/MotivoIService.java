package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.MotivoIEntity;
import com.app.silvahnosbe.repositories.MotivoIRepository;

/**
 * servicios de motivo
 * @author Ignacio grez
 */

@Service
public class MotivoIService {
    
    @Autowired
    MotivoIRepository motivoIRepository;

    /**
     *funcion que lista todos los motivos de ingresos
     * @param null
     * @return retorna una lista con todos los motivos de ingresos
     */

    public List<MotivoIEntity> obtenerMotivoI(){

        return (List<MotivoIEntity>) motivoIRepository.obtenerMotivosI();
    }


    /**
     *funcion que permite obtener un motivo por su id
     * @param id long
     * @return retorna un motivo que coincide con la id
     */

    public MotivoIEntity obtenerMotivoIPorId(Long id){
        return motivoIRepository.findById(id).orElse(null);
    }

    /**
     *funcion que permite guardar un motivo de ingreso
     * @param motivoI
     * @return devuelve un motivo de ingreso si fue guardado correctamente
     */
    public MotivoIEntity guardarMotivoI(MotivoIEntity motivoI){
        return motivoIRepository.save((motivoI));
    }
}
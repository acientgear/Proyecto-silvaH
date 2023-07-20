package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.repositories.EstadoRepository;


/**
 *servicio de entidad estado
 * @author Ignacio Grez
 */

@Service
public class EstadoService {
    
    @Autowired
    EstadoRepository estadoRepository;


    /**
     *funcion que lista los estados registrados en la base de datos
     * los estado son pagado,no pagado ,notificado
     * @param null
     * @return retorna una lista con los estados
     */

    public List<EstadoEntity> obtenerEstado(){
        return (List<EstadoEntity>) estadoRepository.findAll();
    }

    /**
     *funcion que obtiene un estado por su id
     * @param id long
     * @return  devuelve el estado si lo encuentra
     *
     */


    public EstadoEntity obtenerEstadoPorId(Long id){
        return estadoRepository.findById(id).orElse(null);
    }
}
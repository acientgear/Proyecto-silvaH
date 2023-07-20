package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.CorreoEntity;
import com.app.silvahnosbe.repositories.CorreoRepository;


/**
 * Servicios de la entidad correo
 * @author IgnacionGrez
 *
 */

@Service
public class CorreoService {
    
    @Autowired
    CorreoRepository correoRepository;

    /**
     *esta funcion retorna la una lista con los correos en la base de datos
     * @Param no tiene parametros de entrada
     * @return lista de correos
     *
    */

    public List<CorreoEntity> obtenerCorreos(){
        return (List<CorreoEntity>) correoRepository.findAll();
    }


    /**
     * esta funcion permite obtener una direccion de correo almacenada en la base de datos mediante su ID
     * @param id long
     * @return la entidad encontrada si no retorna null
     */

    public CorreoEntity obtenerCorreoPorId(Long id){
        return correoRepository.findById(id).orElse(null);
    }
    /**
     * esta funcion permite actualizar el correo en la base de datos para cambiar el destinatario
     * @param string con el nombre del correo
     * @return entidad actualziada
     */


    public CorreoEntity actualizarCorreo(String nuevoCorreo){
        CorreoEntity correo = correoRepository.findById(1L).orElse(null);
        correo.setDireccion(nuevoCorreo);
        correoRepository.save(correo);
        return correo;
    }
}
package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.models.Registro;
import com.app.silvahnosbe.repositories.RegistroRepository;


/**
 *servicios de registros
 * @author Leo Vergara
 *
 *
 */

@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    /**
     *funcion que permite obtener los registros de un año y mes
     * @param anio
     * @param mes
     * @return retorna una lista con los registros
     */

    public List<Registro> obtenerRegistros(int anio, int mes){
        return (List<Registro>) registroRepository.obtenerRegistros(anio, mes);
    }
}

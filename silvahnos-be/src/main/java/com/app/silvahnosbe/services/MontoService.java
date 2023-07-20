package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.models.Monto;
import com.app.silvahnosbe.repositories.MontoRepository;

/**
 *servicios de monto
 * @author Leo vergara
 */

@Service
public class MontoService {
    @Autowired
    MontoRepository montoRepository;

    /**
     * funcion que permite obtener el monto de los  ingresos por mes y año
     * @param int mes anio
     * @return devuelve una lista donde cada elemento es el monto correspondiente a un mes
     */

    public List<Monto> obtenerMontoIngreso(int anio, int mes){
        return (List<Monto>) montoRepository.obtenerMontoIngresoPorAnioAndMes(anio, mes);
    }

    /**
     * funcion que permite obtener el monto de los  egresos por mes y año
     * @param int mes anio
     * @return devuelve una lista donde cada elemento es el monto correspondiente a un mes
     */

    public List<Monto> obtenerMontoEgreso(int anio, int mes){
        return (List<Monto>) montoRepository.obtenerMontoEgresoPorAnioAndMes(anio, mes);
    }
}

package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.repositories.MotivoMontoRepository;
import com.app.silvahnosbe.models.MotivoMonto;


@Service
public class MotivoMontoService {
    
    @Autowired
    MotivoMontoRepository montoRepository;

    /**
     * funcion que permite obtener el monto de los  ingresos por mes y año
     * @param int mes anio
     * @return devuelve una lista donde cada elemento es el monto correspondiente a un mes
     */

    public List<MotivoMonto> obtenerMontoIngreso(int anio, int mes){
        return (List<MotivoMonto>) montoRepository.obtenerMontoIngresoPorAnioAndMes(anio, mes);
    }

    /**
     * funcion que permite obtener el monto de los  egresos por mes y año
     * @param int mes anio
     * @return devuelve una lista donde cada elemento es el monto correspondiente a un mes
     */

    public List<MotivoMonto> obtenerMontoEgreso(int anio, int mes){
        return (List<MotivoMonto>) montoRepository.obtenerMontoEgresoPorAnioAndMes(anio, mes);
    }
}

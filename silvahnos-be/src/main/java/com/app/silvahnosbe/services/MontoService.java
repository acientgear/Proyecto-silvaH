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
    *funcion que obtiene los montos de ingreso totales por cada mes de un año determinado
    * @param int anio
    * @return retorn una lista con los montos de ingreso totales por cada mes de un año determinado
    */

    public List<Monto> obtenerMontoIngresoTotalMesAnual(int anio){
        List<Monto> montos = (List<Monto>) montoRepository.obtenerMontoIngresoTotalMesAnual(anio);
        return montos;
    }

    /**
    *funcion que obtiene los montos de egreso totales por cada mes de un año determinado
    * @param int anio
    * @return retorn una lista con los montos de egreso totales por cada mes de un año determinado
    */

    public List<Monto> obtenerMontoEgresoTotalMesAnual(int anio){
        return (List<Monto>) montoRepository.obtenerMontoEgresoTotalMesAnual(anio);
    }
}

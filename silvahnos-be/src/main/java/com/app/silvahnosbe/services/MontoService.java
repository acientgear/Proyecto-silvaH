package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.models.Monto;
import com.app.silvahnosbe.repositories.MontoRepository;

@Service
public class MontoService {
    @Autowired
    MontoRepository montoRepository;
    
    public List<Monto> obtenerMontoIngreso(int anio, int mes){
        return (List<Monto>) montoRepository.obtenerMontoIngresoPorAnioAndMes(anio, mes);
    }

    public List<Monto> obtenerMontoEgreso(int anio, int mes){
        return (List<Monto>) montoRepository.obtenerMontoEgresoPorAnioAndMes(anio, mes);
    }
}

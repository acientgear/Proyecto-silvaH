package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;

@Service
public class IngresoService {
    
    @Autowired
    IngresoRepository ingresoRepository;

    public ArrayList<IngresoEntity> obtenerIngresos(int anio, int mes){
        return (ArrayList<IngresoEntity>) ingresoRepository.obtenerIngresos(anio, mes);
    }

    public IngresoEntity obtenerIngresoPorId(Long id){
        return ingresoRepository.findById(id).orElse(null);
    }

    public IngresoEntity guardarIngreso(IngresoEntity ingreso){
        return ingresoRepository.save(ingreso);
    }
}

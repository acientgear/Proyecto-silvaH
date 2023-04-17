package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;

@Service
public class FacturaService {
    
    @Autowired
    IngresoRepository facturaRepository;

    public ArrayList<FacturaEntity> obtenerIngresos(){
        return (ArrayList<FacturaEntity>) facturaRepository.findAll();
    }

    public FacturaEntity obtenerIngresoPorId(Long id){
        return facturaRepository.findById(id).orElse(null);
    }

    public FacturaEntity guardarIngreso(FacturaEntity factura){
        return facturaRepository.save(factura);
    }
}
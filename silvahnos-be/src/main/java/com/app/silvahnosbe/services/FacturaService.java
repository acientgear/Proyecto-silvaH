package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;

@Service
public class FacturaService {
    
    @Autowired
    FacturaRepository facturaRepository;

    public List<FacturaEntity> obtenerFacturas(int anio, int mes){
        return  facturaRepository.obteberFacturas(anio, mes);
    }

    public FacturaEntity guardarFactura(FacturaEntity factura){
        return facturaRepository.save(factura);
    }

    public Integer obtenerIva(int anio, int mes){
        return facturaRepository.obtenerIva(anio, mes);
    }

    public List<FacturaEntity> obtenerProximasVencer(int anio, int mes){
        return (List<FacturaEntity>) facturaRepository.obtenerProximasVencer(anio, mes);
    }

    public List<FacturaEntity> facturaV (int dias){
        return facturaRepository.facturaV(dias);
    }

}
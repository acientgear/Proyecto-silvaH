package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;
import com.app.silvahnosbe.repositories.IngresoRepository;

@Service
public class EgresoService {
    
    @Autowired
    EgresoRepository egresoRepository;

    @Autowired
    IngresoRepository ingresoRepository;

    @Autowired
    IngresoService ingresoService;

    public EgresoEntity obtenerEgresoPorId(Long id){
        return egresoRepository.findById(id).orElse(null);
    }

    public EgresoEntity guardarEgreso(EgresoEntity egreso){
        return egresoRepository.save((egreso));
    }

    public void eliminarEgreso(EgresoEntity egreso){
        egresoRepository.deleteById(egreso.getId());
    }

    public List<EgresoEntity> obtenerEgresoPorAnioAndMes(int anio, int mes){
        return (List<EgresoEntity>) egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
    }

    public List<EgresoEntity> obtenerUltimosEgresos(){
        return (List<EgresoEntity>) egresoRepository.obtenerUltimosEgresos();
    }

    public Integer obtenerTotalEgresosPorMes(int anio, int mes){
        List<EgresoEntity> egresos = (List<EgresoEntity>) egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
        Integer total = 0;
        for(EgresoEntity egreso : egresos){
            total += egreso.getMonto();
        }
        return total;
    }

    public Integer obtenerMontoPorDia(int anio, int mes, int dia){
        Integer monto = egresoRepository.obtenerMontoPorDia(anio, mes, dia);
        if (monto == null){
            return 0;
        }
        return monto;
    }

}

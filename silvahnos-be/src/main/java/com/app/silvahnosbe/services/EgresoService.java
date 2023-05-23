package com.app.silvahnosbe.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.models.MontoOrigenModel;
import com.app.silvahnosbe.models.RegistroModel;
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

    public List<EgresoEntity> obtenerEgresos(){
        return (List<EgresoEntity>) egresoRepository.obtenerEgresos();
    }

    public EgresoEntity obtenerEgresoPorId(Long id){
        return egresoRepository.findById(id).orElse(null);
    }

    public EgresoEntity guardarEgreso(EgresoEntity egreso){
        return egresoRepository.save((egreso));
    }

    public void eliminarEgreso(EgresoEntity egreso){
        egresoRepository.deleteById(egreso.getId());
    }

    public ArrayList<EgresoEntity> obtenerEgresoPorAnioAndMes(int anio, int mes){
        return (ArrayList<EgresoEntity>) egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
    }

    public ArrayList<EgresoEntity> obtenerUltimosEgresos(){
        return (ArrayList<EgresoEntity>) egresoRepository.obtenerUltimosEgresos();
    }

    public Integer obtenerTotalEgresosPorMes(int anio, int mes){
        ArrayList<EgresoEntity> egresos = (ArrayList<EgresoEntity>) egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
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

    public ArrayList<RegistroModel> obtenerEgresosIngresos(int anio,int mes){
        ArrayList<EgresoEntity> egresos = egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
        ArrayList<IngresoEntity> ingresos = ingresoService.obtenerIngresos(anio, mes);
        ArrayList<RegistroModel> registros = new ArrayList<RegistroModel>();
        for(EgresoEntity egreso : egresos){
            RegistroModel registro = new RegistroModel();
            registro.setFecha(egreso.getFecha_creacion());
            registro.setDescripcion(egreso.getDescripcion());
            registro.setMonto(egreso.getMonto());
            registro.setTipo("Egreso");
            registros.add(registro);
        }
        for(IngresoEntity ingreso : ingresos){
            RegistroModel registro = new RegistroModel();
            registro.setFecha(ingreso.getFecha_creacion());
            registro.setDescripcion(ingreso.getDescripcion());
            registro.setMonto(ingreso.getMonto());
            registro.setTipo("Ingreso");
            registros.add(registro);
        }
        registros.sort((RegistroModel r1, RegistroModel r2) -> r2.getFecha().compareTo(r1.getFecha()));

        return registros;
    }

    public ArrayList<MontoOrigenModel> obtenerMontoOrigen(int anio, int mes){
        return (ArrayList<MontoOrigenModel>) egresoRepository.obtenerMontoOrigenPorAnioAndMes(anio, mes);
    }
}

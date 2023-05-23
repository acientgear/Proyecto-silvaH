package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;

@Service
public class IngresoService {
    
    @Autowired
    IngresoRepository ingresoRepository;

    public List<IngresoEntity> obtenerIngresos(int anio, int mes){
        return (List<IngresoEntity>) ingresoRepository.obtenerIngresos(anio, mes);
    }

    public IngresoEntity obtenerIngresoPorId(Long id){
        return ingresoRepository.findById(id).orElse(null);
    }

    public IngresoEntity guardarIngreso(IngresoEntity ingreso){
        return ingresoRepository.save(ingreso);
    }

    public List<IngresoEntity> obtenerUltimosIngresos(){
        return (List<IngresoEntity>) ingresoRepository.obtenerUltimosIngresos();
    }

    public Integer obtenerTotalIngresosPorMes(int anio, int mes){
        List<IngresoEntity> ingresos = (List<IngresoEntity>) ingresoRepository.obtenerIngresos(anio, mes);
        Integer total = 0;
        for(IngresoEntity ingreso : ingresos){
            total += ingreso.getMonto();
        }
        return total;
    }

    public Integer obtenerMontoPorDia(int anio, int mes, int dia){
        Integer monto = ingresoRepository.obtenerMontoPorDia(anio, mes, dia);
        if (monto == null){
            return 0;
        }
        return monto;
    }
    

    public Integer obtenerSaldoCuenta(int mes){
        Integer monto = ingresoRepository.obtenerSaldoCuenta(mes);
        if (monto == null){
            return 0;
        }
        return monto;
    }
}

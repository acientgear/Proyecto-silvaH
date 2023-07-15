package com.app.silvahnosbe.services;

import java.util.ArrayList;
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

    /*public Integer obtenerMontoPorDia(int anio, int mes, int dia){
        Integer monto = ingresoRepository.obtenerMontoPorDia(anio, mes, dia);
        if (monto == null){
            return 0;
        }
        return monto;
    }*/
    
    public Integer obtenerSaldoCuenta(int mes){
        Integer monto = ingresoRepository.obtenerSaldoCuenta(mes);
        if (monto == null){
            return 0;
        }
        return monto;
    }

    public static boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

    public static int obtenerDiasMes(int mes, int año) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (mes == 2 && esBisiesto(año)) {
            return 29; // Febrero en un año bisiesto
        } else {
            return diasPorMes[mes - 1]; // Restamos 1 porque los meses se indexan desde 0 en el arreglo
        }
    }

    public List<Integer> getMontosPorDia(int anio, int mes){
        Integer numeroDias = obtenerDiasMes(mes, anio);
        List<Integer> montos = new ArrayList<Integer>();
        for(int i = 1; i <= numeroDias; i++){
            Integer monto = ingresoRepository.obtenerMontoPorDia(anio, mes, i);
            montos.add(monto);
        }
        return montos;
    }
}

package com.app.silvahnosbe.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;

@Service
public class EgresoService {
    
    @Autowired
    EgresoRepository egresoRepository;

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

    /*public Integer obtenerMontoPorDia(int anio, int mes, int dia){
        Integer monto = egresoRepository.obtenerMontoPorDia(anio, mes, dia);
        if (monto == null){
            return 0;
        }
        return monto;
    }*/

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
            Integer monto = egresoRepository.obtenerMontoPorDia(anio, mes, i);
            montos.add(monto);
        }
        return montos;
    }

    public List<Integer> getMontosUltimos5Dias() {
        List<Integer> montos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 5; i++) {
            Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
            Integer mes = calendar.get(Calendar.MONTH) + 1; // Mes se indexa desde 0 en Calendar, por eso se suma 1
            Integer anio = calendar.get(Calendar.YEAR);
            Integer monto = egresoRepository.obtenerMontoPorDia(anio, mes, dia);
            montos.add(monto);
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Restamos un día al calendario
        }

        return montos;
    }

}

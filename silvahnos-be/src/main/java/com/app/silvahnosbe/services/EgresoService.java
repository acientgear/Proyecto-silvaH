package com.app.silvahnosbe.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.repositories.EgresoRepository;


/**
 * servicios de egreso
 * @author Luis toro
 *
 */

@Service
public class EgresoService {
    
    @Autowired
    EgresoRepository egresoRepository;


    /**
    * esta funcion permite obtener un egreso segun su ID
    * @param id long
    * @return retorna el egreso si lo encuentra , en caso contrario retorna null
    *
    */

    public EgresoEntity obtenerEgresoPorId(Long id){
        return egresoRepository.findById(id).orElse(null);
    }


    /**
     *funcion que permite el registrar un nuevo egreso
     * @param egreso
     * @return retorna la entidad si se guardo correctamente
     *
     */

    public EgresoEntity guardarEgreso(EgresoEntity egreso){
        egresoRepository.save((egreso));
        Timestamp fechaActual = egreso.getFecha_creacion();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(fechaActual);
        calendar1.add(Calendar.HOUR_OF_DAY, -4); // Restar 4 horas
        fechaActual = new Timestamp(calendar1.getTimeInMillis());   
        egreso.setFecha_creacion(fechaActual);
        return egreso;
    }


    /**
     * esta funcion permite eliminar un egreso
     * @param id long
     * @return no tiene retorno si borra correctamente
     */

    public void eliminarEgreso(EgresoEntity egreso){
        egresoRepository.deleteById(egreso.getId());
    }

    /**
     * funcion que lista los egresos por mes y año
     * @author leo Vergara
     * @param int anio mes
     * @return retorna una lista con las entidades encontradas
     */



    public List<EgresoEntity> obtenerEgresoPorAnioAndMes(int anio, int mes){
        return (List<EgresoEntity>) egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
    }

    /**
     *funcion que obtiene una lista con los ultimos egresos registrados
     * @author Leo Vergara
     * @param null
     * @return  lista con los ultimos 3 egresos
     */


    public List<EgresoEntity> obtenerUltimosEgresos(){
        return (List<EgresoEntity>) egresoRepository.obtenerUltimosEgresos();
    }

    /**
     *funcion que obtiene el total de los egresos por mes
     * @param int anio mes
     * @return retorna un int con la suma de los egresos
     */


    public Integer obtenerTotalEgresosPorMes(int anio, int mes){
        List<EgresoEntity> egresos = (List<EgresoEntity>) egresoRepository.obtenerEgresosPorAnioAndMes(anio, mes);
        Integer total = 0;
        for(EgresoEntity egreso : egresos){
            total += egreso.getMonto();
        }
        return total;
    }

    /**
     * funcion que revisa si un año es bisiesto
     * @author Luis Toro
     * @param int año
     * @return boleano
     */

    public static boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }


    /**
     * esta funcion obtiene los dias de un mes , con ayuda de la funcion esBisiesto setea los dias de febrero en ese año en 29 dias
     * para el resto se determinan segun lo normal
     * @param int mes año
     * @return retorna un int representando la cantidad de dias del mes
     */
    public static int obtenerDiasMes(int mes, int año) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (mes == 2 && esBisiesto(año)) {
            return 29; // Febrero en un año bisiesto
        } else {
            return diasPorMes[mes - 1]; // Restamos 1 porque los meses se indexan desde 0 en el arreglo
        }
    }

    /**
     * esta funcion retorna una lista del total por dia de un mes
     * @param int anio mes
     * @return retorna una lista donde cada elemento representa un dia y contiene el total del dia
     * @author Luis Toro
     */


    public List<Integer> getMontosPorDia(int anio, int mes){
        Integer numeroDias = obtenerDiasMes(mes, anio);
        List<Integer> montos = new ArrayList<Integer>();
        for(int i = 1; i <= numeroDias; i++){
            Integer monto = egresoRepository.obtenerMontoPorDia(anio, mes, i);
            montos.add(monto);
        }
        return montos;
    }

    /**
     *esta funcion obtiene los montos de los ultimos 5 dias
     *
     * @param null
     * @return retorna una lista donde cada elemento es un monto de los ultimos 5 dias
     * @author Luis Toro
     */


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

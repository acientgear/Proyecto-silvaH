package com.app.silvahnosbe.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.repositories.IngresoRepository;

/**
 * servicios de ingreso
 * @author Luis Toro
 * @author Leo Vergara
 *
 */

@Service
public class IngresoService {
    
    @Autowired
    IngresoRepository ingresoRepository;

    /**
     * obtiene los ingresos segun año y mes y devuelve una lista
     * @param int anio int mes
     * @return retorna una lista con los ingresos del mes y año escogidos
     */

    public List<IngresoEntity> obtenerIngresos(int anio, int mes){
        return (List<IngresoEntity>) ingresoRepository.obtenerIngresos(anio, mes);
    }


    /**
     * funcion que permite obtiener un ingreso mediante su id
     * @param id lon
     * @return retorna el egreso si lo encuentra , en caso contrario retorna null
     *
     */

    public IngresoEntity obtenerIngresoPorId(Long id){
        return ingresoRepository.findById(id).orElse(null);
    }


    /**
     *funcion que permite guarda un ingreso
     * @param ingreso
     * @return si fue guardado correctamente lo retorna
     *
     */

    public IngresoEntity guardarIngreso(IngresoEntity ingreso){
        ingresoRepository.save(ingreso);
        Timestamp fechaActual = ingreso.getFecha_creacion();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(fechaActual);
        calendar1.add(Calendar.HOUR_OF_DAY, -4); // Restar 4 horas
        fechaActual = new Timestamp(calendar1.getTimeInMillis());   
        ingreso.setFecha_creacion(fechaActual);
        return ingreso;
    }

    /**
     *
     * funcion que permite obtiener los ultimos ingresos
     * @param null
     * @return devuelve una lista con los ultimos 3 ingresos
     *
     */

    public List<IngresoEntity> obtenerUltimosIngresos(){
        return (List<IngresoEntity>) ingresoRepository.obtenerUltimosIngresos();
    }


    /**
     * esta funcion permite obtener el total de los ingresos de un mes
     * @param int anio mes
     * @return returna el total de los ingresos del mes como int
     */

    public Integer obtenerTotalIngresosPorMes(int anio, int mes){
        List<IngresoEntity> ingresos = (List<IngresoEntity>) ingresoRepository.obtenerIngresos(anio, mes);
        Integer total = 0;
        for(IngresoEntity ingreso : ingresos){
            total += ingreso.getMonto();
        }
        return total;
    }

    /**
     * funcion obtenerSaldoCuenta
     * obtiene  el saldo de la cuenta de un mes
     * @param int mes
     * @return retorna un monto
     *
     */


    public Integer obtenerSaldoCuenta(int mes){
        Integer monto = ingresoRepository.obtenerSaldoCuenta(mes);
        if (monto == null){
            return 0;
        }
        return monto;
    }


     /**
      * funcion que revisa si un año es bisiesto
      * @author Luis Toro
      * @param int año
       * @return boleano
      */



    public static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }


    /**
     * esta funcion obtiene los dias de un mes , con ayuda de la funcion esBisiesto setea los dias de febrero en ese año en 29 dias
     * para el resto se determinan segun lo normal
     * @param int mes año
     * @return retorna un int representando la cantidad de dias del mes
     */

    public static int obtenerDiasMes(int mes, int anio) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (mes == 2 && esBisiesto(anio)) {
            return 29; // Febrero en un año bisiesto
        } else {
            return diasPorMes[mes - 1]; // Restamos 1 porque los meses se indexan desde 0 en el arreglo
        }
    }

    /**
     *funcion que obtiene los montos totales de los ingresos por dia
     * @param int anio mes
     * @return retorna una lista con los montos totales de los dias del mes
     */


    public List<Integer> getMontosPorDia(int anio, int mes){
        Integer numeroDias = obtenerDiasMes(mes, anio);
        List<Integer> montos = new ArrayList<Integer>();
        for(int i = 1; i <= numeroDias; i++){
            Integer monto = ingresoRepository.obtenerMontoPorDia(anio, mes, i);
            montos.add(monto);
        }
        return montos;
    }


    /**
     *funcion que obtiene los montos de los ultimos 5 dias
     * @param null
     * @return retorn una lista con los montos de los ultimos  5 dias
     */


    public List<Integer> getMontosUltimos5Dias() {
        List<Integer> montos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 5; i++) {
            Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
            Integer mes = calendar.get(Calendar.MONTH) + 1; // Mes se indexa desde 0 en Calendar, por eso se suma 1
            Integer anio = calendar.get(Calendar.YEAR);
            Integer monto = ingresoRepository.obtenerMontoPorDia(anio, mes, dia);
            montos.add(monto);
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Restamos un día al calendario
        }

        return montos;
    }
}

package com.app.silvahnosbe.services;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;

/**
 *
 * servicios de factura
 * @author Luis Toro
 * @author acientgear
 *
 */

@Service
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    /**
     * funcion que retorna una lista de facturas segun año y mes
     * @param int año mes
     * @return lista con facturas
     */

    public List<FacturaEntity> obtenerFacturas(int anio, int mes) {
        return facturaRepository.obteberFacturas(anio, mes);
    }


    /**
     *funcion que permite registrar una factura
     * @param factura contiene numero,fecha,monto
     * @return si se guarda correctamente retorna la factura
     */

    public FacturaEntity guardarFactura(FacturaEntity factura) {
        facturaRepository.save(factura);
        Timestamp fechaActual = factura.getFecha_creacion();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(fechaActual);
        calendar1.add(Calendar.HOUR_OF_DAY, -4); // Restar 4 horas
        fechaActual = new Timestamp(calendar1.getTimeInMillis());   
        factura.setFecha_creacion(fechaActual);
        return factura;
    }


    /**
     *
     * esta funcion permite cambiar el estado de una factura de no pagado o notificado a pagado
     * @param factura
     * @return retorna la factura con el estado cambiado
     */

    public FacturaEntity pagarFactura(FacturaEntity factura) {
        /*
        if (factura.getFecha_pago() != null) {
            /*Restar un día a la fecha de pago */
            /*Calendar calendar = Calendar.getInstance();
            calendar.setTime(factura.getFecha_pago());
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Restar un día

            Date fecha = calendar.getTime();

            factura.setFecha_pago(fecha);
        } */
        
        java.sql.Timestamp fecha = new java.sql.Timestamp(new Date().getTime());
        factura.setFecha_pago(fecha);

        return facturaRepository.save(factura);
    }

    /**
     *esta funcion permite obtener el iva de un conjunto de facturas
     * @param int año mes
     * @return cantidad total del iva del mes deseado
     */


    public Integer obtenerIva(int anio, int mes) {
        return facturaRepository.obtenerIva(anio, mes);
    }

    /**
     *esta funcion obtiene facturas proximas a vencer desde la fecha actual
     * @param int año mes
     * @return retorna una lista de facturas
     */


    public List<FacturaEntity> obtenerProximasVencer(int anio, int mes) {
        return (List<FacturaEntity>) facturaRepository.obtenerProximasVencer(anio, mes);
    }


    /**
     *funcion que busca facturas que vencen en una determianda cantidad de dias , utilizada para notificar
     * @param  int dias
     * @return retorna una lista de facturas
     */

    public List<FacturaEntity> facturaV(int dias) {
        return facturaRepository.facturaV(dias);
    }

}
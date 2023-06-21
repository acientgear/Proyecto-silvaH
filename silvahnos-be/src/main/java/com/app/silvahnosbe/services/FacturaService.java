package com.app.silvahnosbe.services;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    public List<FacturaEntity> obtenerFacturas(int anio, int mes) {
        return facturaRepository.obteberFacturas(anio, mes);
    }

    public FacturaEntity guardarFactura(FacturaEntity factura) {
        /*Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(factura.getFecha_vencimiento());
        calendar1.add(Calendar.DAY_OF_MONTH, 1); // Sumar un día

        Date fecha_v = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(factura.getFecha_emision());
        calendar2.add(Calendar.DAY_OF_MONTH, 1); // Sumar un día

        Date fecha_e = calendar2.getTime();

        factura.setFecha_vencimiento(fecha_v);
        factura.setFecha_emision(fecha_e);*/
        return facturaRepository.save(factura);
    }

    public FacturaEntity pagarFactura(FacturaEntity factura) {
        if (factura.getFecha_pago() != null) {
            /*Restar un día a la fecha de pago */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(factura.getFecha_pago());
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Restar un día

            Date fecha = calendar.getTime();

            factura.setFecha_pago(fecha);
        }
        return facturaRepository.save(factura);
    }

    public Integer obtenerIva(int anio, int mes) {
        return facturaRepository.obtenerIva(anio, mes);
    }

    public List<FacturaEntity> obtenerProximasVencer(int anio, int mes) {
        return (List<FacturaEntity>) facturaRepository.obtenerProximasVencer(anio, mes);
    }

    public List<FacturaEntity> facturaV(int dias) {
        return facturaRepository.facturaV(dias);
    }

}
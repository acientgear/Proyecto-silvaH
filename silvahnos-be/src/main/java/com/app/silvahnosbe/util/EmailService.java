package com.app.silvahnosbe.util;

import com.app.silvahnosbe.entities.EmailConfig;
import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.CorreoRepository;
import com.app.silvahnosbe.repositories.EmailConfigRepository;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.repositories.ParametroRepository;
import com.app.silvahnosbe.services.CorreoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * servicio email
 * este servicio se centra en el envio automatizado de notificaciones de
 * facturas
 * 
 * @author acientgear
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CorreoRepository correoRepository;

    @Autowired
    private CorreoService correoService;

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ParametroRepository parametroRepository;
    @Autowired
    private EmailConfigRepository emailConfigRepository;

    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");

    // arreglo que contiene los nombres de los meses del año
    String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio",
            "agosto", "septiembre", "octubre", "noviembre", "diciembre" };

    /**
     *
     * esta funcion envia un email a un destinarario , su funcionanmiento es solo de
     * prueba de conexion
     * 
     * @param null
     * @return null
     */

    public void sendEmail() {
        /*
         * EmailConfig emailConfig = emailConfigRepository.findById(1L)
         * .orElseThrow(() -> new
         * RuntimeException("Configuración de correo electrónico no encontrada."));
         */
        String destino = correoService.obtenerCorreoPorId(1L).getDireccion();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acientgear@gmail.com");
        message.setTo(destino);
        message.setSubject("Notificación factura");
        message.setText("Factura 131 está por vencer: \n");

        mailSender.send(message);
    }

    /**
     * esta funcion envia un email con las facturas sin cobrar en un intervalo de
     * tiempo mediante cron job
     * se comprueba su funcionamiento con la funcion sendEmail
     * 
     * @param null
     * @return no tiene retorno,
     */

    @Scheduled(cron = "0 7 * * * *")
    public void cronEmail() {
        EstadoEntity estado = new EstadoEntity();
        estado.setId(2l);
        String diasS = parametroRepository.findById(1l).orElseThrow().getValor();
        int dias = Integer.parseInt(diasS);
        String destino = correoRepository.findById(1L).get().getDireccion();
        List<FacturaEntity> facturas = facturaRepository.facturaV(dias);
        if (facturas.size() == 0) {
            return;
        }
        facturas.forEach(factura -> {
            factura.setEstado(estado);
        });
        facturaRepository.saveAll(facturas);
        String mensaje = "\n\n";
        int i = 0;
        while (i < facturas.size()) {
            java.sql.Timestamp fechaVencimiento = facturas.get(i).getFecha_vencimiento();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaVencimiento);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH) + 1;
            int anio = calendar.get(Calendar.YEAR);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(facturas.get(i).getFecha_emision());
            int diaE = calendar2.get(Calendar.DAY_OF_MONTH);
            int mesE = calendar2.get(Calendar.MONTH) + 1;
            int anioE = calendar2.get(Calendar.YEAR);

            mensaje = mensaje + "Factura N° " + facturas.get(i).getNumero_factura() + ", con fecha de vencimiento el "
                    + dia + " del " + meses[mes - 1] + " del " + anio + ". \nEmitida el " + diaE + " del "
                    + meses[mesE - 1] + " del " + anioE
                    + " a la empresa " + facturas.get(i).getEmpresa().getNombre() + " por un monto de "
                    + formatearMonto(facturas.get(i).getMonto()) + ".\n\n";
            i = i + 1;
        }
        EmailConfig emailConfig = emailConfigRepository.findById(1l).orElseThrow();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getUsername());
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int anio = calendar.get(Calendar.YEAR);
        message.setSubject("Facturas no cobradas que vencen en menos de " + diasS + " días" + ":  " + " " + dia 
                +"-"+ mes +"-"+ anio);
        message.setTo(destino);
        message.setText(emailConfig.getTexto() + mensaje);
        mailSender.send(message);
    }

    private String formatearMonto(double monto) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(0); // Establece el número mínimo de decimales
        format.setMaximumFractionDigits(2); // Establece el número máximo de decimales
        return format.format(monto);
    }

}

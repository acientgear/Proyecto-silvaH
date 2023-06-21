package com.app.silvahnosbe.services;

import com.app.silvahnosbe.entities.EmailConfig;
import com.app.silvahnosbe.entities.EstadoEntity;
import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.repositories.CorreoRepository;
import com.app.silvahnosbe.repositories.EmailConfigRepository;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.repositories.ParametroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    Date fecha = new Date();

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
        message.setText("Factura 131 está por vencer");

        mailSender.send(message);
    }

    @Scheduled(cron = "0 */1 * * * *")
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
        String mensaje = "\n";
        int i = 0;
        while (i < facturas.size()) {
            Date fechaVencimiento = facturas.get(i).getFecha_vencimiento();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaVencimiento);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH) + 1;
            int anio = calendar.get(Calendar.YEAR);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'del' MM 'del' yyyy");
            String fechaFormateada = dateFormat.format(fechaVencimiento);
            mensaje = mensaje + "Factura N° :" + facturas.get(i).getNumero_factura() + ", con fecha de vencimiento el "
                    + dia + " del " + mes + " del año " + anio + "\n";
            i = i + 1;
        }
        EmailConfig emailConfig = emailConfigRepository.findById(1l).orElseThrow();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getUsername());
        message.setSubject("Facturas a vencer en " + diasS + " días" + ":  " + "  " + formato.format(fecha));
        message.setTo(destino);
        message.setText(emailConfig.getTexto() + mensaje);
        mailSender.send(message);
    }

}

package com.app.silvahnosbe.services;

import com.app.silvahnosbe.entities.EmailConfig;
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

import java.util.Date;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CorreoRepository correoRepository;

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ParametroRepository parametroRepository;
    @Autowired
    private EmailConfigRepository emailConfigRepository;

    SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yy");
    Date fecha= new Date();
    public void sendEmail() {
        EmailConfig emailConfig = emailConfigRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuración de correo electrónico no encontrada."));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getUsername());
        message.setTo("luis.toro.f@usach.cl");
        message.setSubject("Notificación factura");
        message.setText("Factura 131 está por vencer");

        mailSender.send(message);
    }


    @Scheduled(cron="0 */5 * * * *")
    public void cronEmail(){
        int dias = Integer.valueOf(parametroRepository.findById(1l).orElseThrow().getValor());
        String destino=correoRepository.findById(1L).get().getDireccion();
        List <FacturaEntity> facturas= facturaRepository.facturaV(dias);
        if (facturas==null){
            return ;
        }
        String mensaje="\n";
        int i=0;
        while (i<facturas.size()){
            mensaje=mensaje +"n° :"+ facturas.get(i).getNumero_factura() +"\n " ;
            i=i+1;

        }
        EmailConfig emailConfig=emailConfigRepository.findById(1l).orElseThrow();
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom(emailConfig.getUsername());
        message.setSubject("facturas proximas a vencer:  "+"  " + formato.format(fecha));
        message.setTo(destino);
        message.setText(emailConfig.getTexto()+ mensaje);
        mailSender.send(message);
       // facturaRepository.updateEstado(dias);

    }


}

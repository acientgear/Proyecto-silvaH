package com.app.silvahnosbe.controllers;


import com.app.silvahnosbe.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity <String> sendEmail(){
        emailService.sendEmail();
        return ResponseEntity.ok("Correo electronico enviado correctamente");
    }

    @PostMapping("/sendCron")
    public ResponseEntity<String>sendCron(){
        emailService.cronEmail();
        return ResponseEntity.ok().body("correo electronico enviado correctamente");
    }
}

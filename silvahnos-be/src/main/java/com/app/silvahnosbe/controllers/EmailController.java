package com.app.silvahnosbe.controllers;


import com.app.silvahnosbe.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Email",description = "controlador del servicio de mensajeria automatizado , funcionamiento solo de pruebas tecnicas")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @Operation(summary = "manda un correo  ",description = "manda un email a un destinatario registrado en la base de datos, uso de pruebas tecnicas")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="mensaje enviado, puede no haberse enviado "),
            @ApiResponse(responseCode = "404", description = "error en conexion")
    })
    @PostMapping("/send-email")
    public ResponseEntity <String> sendEmail(){
        emailService.sendEmail();
        return ResponseEntity.ok("Correo electronico enviado correctamente");
    }

    @Operation(summary = "manda un correo ",description = "envia un mensaje al destinatario con una lista de facturas por vencer en un tiempo determinado ")

    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="mensaje enviado "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @PostMapping("/sendCron")
    public ResponseEntity<String>sendCron(){
        emailService.cronEmail();
        return ResponseEntity.ok().body("correo electronico enviado correctamente");
    }
}

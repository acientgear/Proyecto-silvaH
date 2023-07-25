package com.app.silvahnosbe.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.services.BackupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/back")
public class BackupController {
 
    @Autowired
    private BackupService backupService;

    @Operation(summary = "realiza el backup de la bd",description = "retorna un mensaje de confirmacion")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="backup generado correctamente "),
    })

    @GetMapping("/backup")
    public ResponseEntity<String> doBackup() throws ClassNotFoundException, IOException, SQLException{

        backupService.makeBackup();
        return ResponseEntity.ok("backup generado correctamente");
    }

}

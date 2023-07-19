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

@RestController
@CrossOrigin
@RequestMapping("/back")
public class BackupController {
 
    @Autowired
    private BackupService backupService;

    @GetMapping("/backup")
    public ResponseEntity<String> doBackup() throws ClassNotFoundException, IOException, SQLException{

        backupService.makeBackup();
        return ResponseEntity.ok("backup generado correctamente");
    }

}

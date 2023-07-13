package com.app.silvahnosbe.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.services.IngresoService;
import com.app.silvahnosbe.services.reports.IngresoInterface;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;

@RestController
@CrossOrigin
@RequestMapping("/ingresos")
public class IngresoController {
    @Autowired
    IngresoService ingresoService;

    @Autowired
    IngresoInterface ingresoInterface;

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<IngresoEntity>> getAllIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<IngresoEntity> ingresos= ingresoService.obtenerIngresos(anio, mes);
        if(ingresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingresos);
    }

    @PostMapping
    public ResponseEntity<IngresoEntity> createIngreso(@RequestBody IngresoEntity ingreso){
        IngresoEntity ingresoGuardado = ingresoService.guardarIngreso(ingreso);
        return ResponseEntity.ok().body(ingresoGuardado);
    }

    @GetMapping("/ultimos")
    public ResponseEntity<List<IngresoEntity>> getUltimosIngresos(){
        List<IngresoEntity> ingresos = ingresoService.obtenerUltimosIngresos();
        if(ingresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingresos);
    }

    @GetMapping("/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        Integer total = ingresoService.obtenerTotalIngresosPorMes(anio, mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/monto/{anio}/{mes}/{dia}")
    public ResponseEntity<Integer> getMontoPorDia(@PathVariable("anio") int anio, @PathVariable("mes") int mes, @PathVariable("dia") int dia){
        Integer total = ingresoService.obtenerMontoPorDia(anio,mes,dia);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/total/{mes}")
    public ResponseEntity<Integer> obtenerSaldoCuenta(@PathVariable("mes") int mes){
        Integer total = ingresoService.obtenerSaldoCuenta(mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/export-pdf/{anio}/{mes}")
    public ResponseEntity<Resource> exportPdf(@PathVariable("anio") int anio, @PathVariable("mes") int mes)
            throws JRException, FileNotFoundException {
        byte[] pdfBytes = ingresoInterface.exportPdf(anio, mes);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        String filename = "ingresos " + anio + "-" + mes + ".pdf";
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", filename);
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}

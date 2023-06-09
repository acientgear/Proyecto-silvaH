package com.app.silvahnosbe.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.EgresoEntity;
import com.app.silvahnosbe.services.EgresoService;
import com.app.silvahnosbe.services.reports.EgresoInterface;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/egresos")
public class EgresoController {
    @Autowired
    EgresoService egresoService;

    @Autowired
    EgresoInterface egresoInterface;

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<EgresoEntity>> getEgresoByAnioAndMes(@PathVariable("anio") int anio,
            @PathVariable("mes") int mes) {
        List<EgresoEntity> egresos = egresoService.obtenerEgresoPorAnioAndMes(anio, mes);
        if (egresos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @PostMapping
    public ResponseEntity<EgresoEntity> createEgreso(@RequestBody EgresoEntity egreso) {
        EgresoEntity egresoGuardado = egresoService.guardarEgreso(egreso);
        return ResponseEntity.ok().body(egresoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EgresoEntity> deleteEgreso(@PathVariable("id") Long id) {
        EgresoEntity egreso = egresoService.obtenerEgresoPorId(id);
        if (egreso == null) {
            return ResponseEntity.notFound().build();
        }
        egresoService.eliminarEgreso(egreso);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ultimos")
    public ResponseEntity<List<EgresoEntity>> getUltimosEgresos() {
        List<EgresoEntity> egresos = egresoService.obtenerUltimosEgresos();
        if (egresos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @GetMapping("/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalEgresosPorMes(@PathVariable("anio") int anio, @PathVariable("mes") int mes) {
        Integer total = egresoService.obtenerTotalEgresosPorMes(anio, mes);
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/monto/{anio}/{mes}/{dia}")
    public ResponseEntity<Integer> getMontoPorDia(@PathVariable("anio") int anio, @PathVariable("mes") int mes,
            @PathVariable("dia") int dia) {
        Integer total = egresoService.obtenerMontoPorDia(anio, mes, dia);
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/export-pdf/{anio}/{mes}")
    public ResponseEntity<Resource> exportPdf(@PathVariable("anio") int anio, @PathVariable("mes") int mes)
            throws JRException, FileNotFoundException {
        byte[] pdfBytes = egresoInterface.exportPdf(anio, mes);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "egresos.pdf");
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}

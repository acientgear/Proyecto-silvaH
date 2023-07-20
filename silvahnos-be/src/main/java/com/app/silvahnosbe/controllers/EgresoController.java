package com.app.silvahnosbe.controllers;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.EgresoService;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.reports.EgresoInterface;

import net.sf.jasperreports.engine.JRException;

import org.springframework.http.*;

@RestController
@CrossOrigin
@RequestMapping("/egresos")
@Tag(name="Egresos",description="controladores de la entidad egreso")
public class EgresoController {
    @Autowired
    EgresoService egresoService;

    @Autowired
    EgresoInterface egresoInterface;

    @Autowired
    MovimientoService movimientoService;

    @Operation(summary = "obtiene egresos de un mes ",description = "retorna una lista de egresos segun un año y mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<EgresoEntity>> getEgresoByAnioAndMes(@PathVariable("anio") int anio,
            @PathVariable("mes") int mes) {
        List<EgresoEntity> egresos = egresoService.obtenerEgresoPorAnioAndMes(anio, mes);
        if (egresos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }

    @Operation(summary = "registra un egreso ",description = "registra un egreso en la base de datos y retorna el egreso creado ")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="se guardo de manera exitosa "),
            @ApiResponse(responseCode = "404", description = "datos no guarados ")
    })

    @PostMapping
    public ResponseEntity<EgresoEntity> createEgreso(@RequestBody EgresoEntity egreso) {
        String tipo = "";
        if(egreso.getId() == null){
            tipo = "Creación";
        }else{
            if(egreso.isBorrado() == true){
                tipo = "Eliminación";
            }else{
                tipo = "Modificación";
            }
        }
        EgresoEntity egresoGuardado = egresoService.guardarEgreso(egreso);
        MovimientoEntity movimiento = new MovimientoEntity(null, 
                                                            null, 
                                                            tipo,
                                                            "egreso", 
                                                            egresoGuardado.getId(),
                                                            null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(egresoGuardado);
    }


    @Operation(summary = "borra un egreso ",description = "elimina un egreso segun un id")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos borrados "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<EgresoEntity> deleteEgreso(@PathVariable("id") Long id) {
        EgresoEntity egreso = egresoService.obtenerEgresoPorId(id);
        if (egreso == null) {
            return ResponseEntity.notFound().build();
        }
        egresoService.eliminarEgreso(egreso);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "obtiene los ultigmos egresos registrados",description = "retorna  los ultimo egresos registrados como una lista")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/ultimos")
    public ResponseEntity<List<EgresoEntity>> getUltimosEgresos() {
        List<EgresoEntity> egresos = egresoService.obtenerUltimosEgresos();
        if (egresos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(egresos);
    }


    @Operation(summary = "obtiene el total de los egresos del mes ",description = "retorna la suma de todos los egresos del mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalEgresosPorMes(@PathVariable("anio") int anio, @PathVariable("mes") int mes) {
        Integer total = egresoService.obtenerTotalEgresosPorMes(anio, mes);
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }


    @Operation(summary = "obtiene el total de egresos del dia ",description = "retorna el total de los egresos registrados en el dia")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/monto/{anio}/{mes}")
    public ResponseEntity<List<Integer>> getMontoPorDia(@PathVariable("anio") int anio, @PathVariable("mes") int mes) {
        List<Integer> total = egresoService.getMontosPorDia(anio, mes);
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }


    @Operation(summary = "obtiene el total de egresos de los ultimos 5 dias ",description = "retorna el total de los egresos registrados en los ultimos 5 dias")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/monto-5-dias/")
    public ResponseEntity<List<Integer>> getMontos5dias() {
        List<Integer> total = egresoService.getMontosUltimos5Dias();
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @Operation(summary = "genera un reporte en PDF ",description = "retorna un reporte PDF con los datos requeridos por el usuario")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/export-pdf/{fi}/{ff}")
    public ResponseEntity<Resource> exportPdf(@PathVariable("fi") String fechaInicio, @PathVariable("ff") String fechaFin)
            throws JRException, FileNotFoundException {
        String fi = fechaInicio + " 00:00:00";
        String ff = fechaFin + " 23:59:59";
        byte[] pdfBytes = egresoInterface.exportPdf(fi, ff);
        String[] fiS = fechaInicio.split("-"); 
        String[] ffS = fechaFin.split("-");
        fi = fiS[2] + "-" + fiS[1] + "-" + fiS[0];
        ff = ffS[2] + "-" + ffS[1] + "-" + ffS[0];
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        String date = new SimpleDateFormat("dd-MM-yyyy HH-mm").format(new Timestamp(System.currentTimeMillis()));
        String filename = "Egresos Desde="+ fi +" Hasta="+ ff + " Generado="+date+".pdf";
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}

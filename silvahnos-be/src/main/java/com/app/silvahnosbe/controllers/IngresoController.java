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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.IngresoEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.IngresoService;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.reports.IngresoInterface;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;

@RestController
@CrossOrigin
@RequestMapping("/ingresos")
@Tag(name="ingresos" ,description = "controladores de la entidad ingresos")
public class IngresoController {
    @Autowired
    IngresoService ingresoService;

    @Autowired
    IngresoInterface ingresoInterface;

    @Autowired
    MovimientoService movimientoService;

    @Operation(summary = "obtiene todos los ingresos de un mes ",description = "retorna una lista con los ingresos de un mes en especifico")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<IngresoEntity>> getAllIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<IngresoEntity> ingresos= ingresoService.obtenerIngresos(anio, mes);
        if(ingresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingresos);
    }

    @Operation(summary = "registra un ingreso nuevo ",description = "retorna el ingreso si este fue creado correctamente")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @PostMapping
    public ResponseEntity<IngresoEntity> createIngreso(@RequestBody IngresoEntity ingreso){
        String tipo = ""; 
        if(ingreso.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(ingreso.isBorrado()){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        IngresoEntity ingresoGuardado = ingresoService.guardarIngreso(ingreso);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,"ingreso",ingresoGuardado.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(ingresoGuardado);
    }



    @Operation(summary = "obtiene los ultimos 3 ingresos ",description = "retorna una lista con los ultimos 3 ingresos registrados")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/ultimos")
    public ResponseEntity<List<IngresoEntity>> getUltimosIngresos(){
        List<IngresoEntity> ingresos = ingresoService.obtenerUltimosIngresos();
        if(ingresos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingresos);
    }


    @Operation(summary = "obtiene el total de ingresos del mes ",description = "retorna el total de los ingresos registrados en el mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/total/{anio}/{mes}")
    public ResponseEntity<Integer> getTotalIngresos(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        Integer total = ingresoService.obtenerTotalIngresosPorMes(anio, mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }


    @Operation(summary = "obtiene el total de ingresos del dia ",description = "retorna el total de los ingresos registrados en el dia")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })
    @GetMapping("/monto/{anio}/{mes}")
    public ResponseEntity<List<Integer>> getMontoPorDia(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<Integer> total = ingresoService.getMontosPorDia(anio,mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }

    @Operation(summary = "obtiene el ingreso del mes",description = "retorna el total de los ingresos obtenidos este mes")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/total/{mes}")
    public ResponseEntity<Integer> obtenerSaldoCuenta(@PathVariable("mes") int mes){
        Integer total = ingresoService.obtenerSaldoCuenta(mes);
        if(total == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }


    @Operation(summary = "genera un reporte en PDF ",description = "retorna un reporte en PDF con los ingresos de un intervalo de tiempo")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/export-pdf/{fi}/{ff}")
    public ResponseEntity<Resource> exportPdf(@PathVariable("fi") String fechaInicio, @PathVariable("ff") String fechaFin)
            throws JRException, FileNotFoundException {
        String fi = fechaInicio + " 00:00:00";
        String ff = fechaFin + " 23:59:59";
        byte[] pdfBytes = ingresoInterface.exportPdf(fi, ff);
        String[] fiS = fechaInicio.split("-"); 
        String[] ffS = fechaFin.split("-");
        fi = fiS[2] + "-" + fiS[1] + "-" + fiS[0];
        ff = ffS[2] + "-" + ffS[1] + "-" + ffS[0];
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        String date = new SimpleDateFormat("dd-MM-yyyy HH-mm").format(new Timestamp(System.currentTimeMillis()));
        String filename = "Ingresos Desde="+ fi +" Hasta="+ ff + " Generado="+date+".pdf";
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        return ResponseEntity.ok().headers(headers).body(resource);
    }


    @Operation(summary = "obtiene el monto de 5 dias ",description = "retorna el total de los ingresos de los ultimos 5 dias")
    @ApiResponses(value={
            @ApiResponse(responseCode="200",description="datos obtenidos correctamente "),
            @ApiResponse(responseCode = "404", description = "datos no encontrados ")
    })

    @GetMapping("/monto-5-dias/")
    public ResponseEntity<List<Integer>> getMontos5dias() {
        List<Integer> total = ingresoService.getMontosUltimos5Dias();
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(total);
    }
}

package com.app.silvahnosbe.controllers;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.silvahnosbe.entities.FacturaEntity;
import com.app.silvahnosbe.entities.MovimientoEntity;
import com.app.silvahnosbe.services.FacturaService;
import com.app.silvahnosbe.services.MovimientoService;
import com.app.silvahnosbe.services.reports.FacturaInterface;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;

@RestController
@CrossOrigin
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @Autowired
    FacturaInterface facturaInterface;

    @Autowired
    MovimientoService movimientoService;

    @GetMapping("/{anio}/{mes}")
    public ResponseEntity<List<FacturaEntity>> getFacturas(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<FacturaEntity> facturas= facturaService.obtenerFacturas(anio, mes);
        if(facturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(facturas);
    }

    @PostMapping
    public ResponseEntity<FacturaEntity> createFactura(@RequestBody FacturaEntity factura){
        String tipo = ""; 
        if(factura.getId() == null){ 
            tipo = "Creación"; 
        }else{ 
            if(factura.isBorrado() == true){ 
                tipo = "Eliminación"; 
            }else{ 
                tipo = "Modificación"; 
            } 
        } 
        FacturaEntity facturaGuardada = facturaService.guardarFactura(factura);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,tipo,"factura",facturaGuardada.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(facturaGuardada);
    }

    @PostMapping("/pagar")
    public ResponseEntity<FacturaEntity> pagarFactura(@RequestBody FacturaEntity factura){
        FacturaEntity facturaGuardada = facturaService.pagarFactura(factura);
        MovimientoEntity movimiento = new MovimientoEntity(null,null,"Pago","factura",facturaGuardada.getId(),null);
        movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok().body(facturaGuardada);
    }

    @GetMapping("/iva/{anio}/{mes}")
    public ResponseEntity<Integer> getIva(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        Integer iva = facturaService.obtenerIva(anio, mes);
        if(iva == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(iva);
    }

    @GetMapping("/proximasVencer/{anio}/{mes}")
    public ResponseEntity<List<FacturaEntity>> getProximasVencer(@PathVariable("anio") int anio, @PathVariable("mes") int mes){
        List<FacturaEntity> facturas= facturaService.obtenerProximasVencer(anio, mes);
        if(facturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(facturas);
    }

    @GetMapping("/vencer")
    public ResponseEntity<List<FacturaEntity>> facturasV(@Param("dias") int dias){
        List<FacturaEntity> facturas= facturaService.facturaV(dias);
        if(facturas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(facturas);
    }

    @GetMapping("/export-pdf/{fi}/{ff}")
    public ResponseEntity<Resource> exportPdf(@PathVariable("fi") String fechaInicio, @PathVariable("ff") String fechaFin)
            throws JRException, FileNotFoundException {
        String fi = fechaInicio + " 00:00:00";
        String ff = fechaFin + " 23:59:59";
        byte[] pdfBytes = facturaInterface.exportPdf(fi, ff);
        String[] fiS = fechaInicio.split("-"); 
        String[] ffS = fechaFin.split("-");
        fi = fiS[2] + "-" + fiS[1] + "-" + fiS[0];
        ff = ffS[2] + "-" + ffS[1] + "-" + ffS[0];
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        String date = new SimpleDateFormat("dd-MM-yyyy HH-mm").format(new Timestamp(System.currentTimeMillis()));
        String filename = "Facturas Desde="+ fi +" Hasta="+ ff + " Generado="+date+".pdf";
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
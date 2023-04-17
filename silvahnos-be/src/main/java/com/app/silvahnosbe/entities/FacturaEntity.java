package com.app.silvahnosbe.entities;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Timestamp fecha_emision;
    private Timestamp fecha_vencimiento;
    private Timestamp fecha_pago;
    private String observaciones;
    private boolean borrado;
    private Timestamp fecha_creacion;
    private Timestamp fecha_modificacion;
    private Timestamp fecha_borrado;
    private int numero_factiura;
}
package com.app.silvahnosbe.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "factura")
@SQLDelete(sql = "UPDATE factura SET borrado = true WHERE id=?")
@Where(clause ="borrado=false")
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
    
    @CreationTimestamp
    private Timestamp fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    @UpdateTimestamp
    private Timestamp fecha_borrado;
    private int numero_factura;
}
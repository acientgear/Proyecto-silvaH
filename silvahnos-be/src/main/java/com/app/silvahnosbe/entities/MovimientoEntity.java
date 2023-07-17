package com.app.silvahnosbe.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    // Relacion con local
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LocalEntity local;*/

    // Relacion con usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UsuarioEntity usuario;

    private String tipo;

    // Relacion con egreso
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_egreso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EgresoEntity egreso;

    // Relación con ingreso
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_ingreso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private IngresoEntity ingreso;

    // Relación con factura
    /*@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_factura")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FacturaEntity factura; */    

    @CreationTimestamp
    private Timestamp fecha_creacion;
}
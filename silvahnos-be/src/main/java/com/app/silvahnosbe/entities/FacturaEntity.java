package com.app.silvahnosbe.entities;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    // Datos
    private int numero_factura;
    private Date fecha_emision;
    private Date fecha_vencimiento;
    private Date fecha_pago;
    private Integer monto;
    private String observaciones;
    private boolean borrado;

    // Relaciones con estado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EstadoEntity estado;

    // Relaciones con empresa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EmpresaEntity empresa;

    // Relaciones con movimiento
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MovimientoEntity movimiento;

    // Fechas de acciones
    @CreationTimestamp
    private Timestamp fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    private Timestamp fecha_borrado;

}
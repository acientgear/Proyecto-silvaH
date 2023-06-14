package com.app.silvahnosbe.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingreso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngresoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int monto;
    private String patente;
    private String descripcion;
    private boolean borrado;

    // Relacion con motivo
    @ManyToOne
    @JoinColumn(name = "motivo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MotivoIEntity motivo;

    // Relacion con movimientos
    @OneToOne
    @JoinColumn(name = "movimiento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MovimientoEntity movimiento;

    // Relacion con factura
    @ManyToOne
    @JoinColumn(name = "factura")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FacturaEntity factura;

    // Tiempo de acciones
    @CreationTimestamp
    private Timestamp fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    private Timestamp fecha_borrado;
}

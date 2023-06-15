package com.app.silvahnosbe.entities;

import java.util.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "egreso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EgresoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private int monto;
    private String descripcion;
    private boolean borrado;

    // Relacion con motivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motivo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MotivoEEntity motivo;

    // Relacion con movimientos
    @OneToOne
    @JoinColumn(name = "movimiento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MovimientoEntity movimiento;
    
    // Tiempo de acciones
    @CreationTimestamp
    private Date fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    private Timestamp fecha_borrado;
}

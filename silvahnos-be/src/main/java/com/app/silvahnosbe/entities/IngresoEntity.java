package com.app.silvahnosbe.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String origen;
    private String patente;
    private String descripcion;
    private boolean borrado;

    @CreationTimestamp
    private Timestamp fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    
    private Timestamp fecha_borrado;
}

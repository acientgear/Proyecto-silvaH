package com.app.silvahnosbe.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
    private String patente;
    private String descripcion;
    private Boolean borrado;

    @CreationTimestamp
    private Date fecha_creacion;
    
    private Timestamp fecha_modificacion;
    private Timestamp fecha_borrado;
}

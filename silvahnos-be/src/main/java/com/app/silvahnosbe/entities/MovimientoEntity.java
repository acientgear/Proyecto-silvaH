package com.app.silvahnosbe.entities;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LocalEntity local;

    // Relacion con usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UsuarioEntity usuario;
}
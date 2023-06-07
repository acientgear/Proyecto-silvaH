package com.app.silvahnosbe.entities;

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

    private Long local;
    private String usuario;
    private long rol;
    private long id_objetivo; //id de la factura, ingreso o egreso
    private String tipo; // si es factura, ingreo o egreso
    private String mov; // accion realizada, create, delete, update.
}
package com.app.silvahnosbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saldo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Integer anio;
    private Integer mes;
    private Integer ingresos_totales;
    private Integer egresos_totales;
}

package com.app.silvahnosbe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registro{
    @Id
    private Long id;
    private String tipo;
    private Date fecha;
    private String descripcion;
    private Integer monto;
}

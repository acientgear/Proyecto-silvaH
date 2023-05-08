package com.app.silvahnosbe.models;

import lombok.Data;
import java.util.Date;

@Data
public class RegistroModel {
    private String tipo;
    private Date fecha;
    private String descripcion;
    private Integer monto;
}

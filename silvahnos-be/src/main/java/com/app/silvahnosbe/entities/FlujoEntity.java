package com.app.silvahnosbe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlujoEntity {
    private Integer anio;
    private Integer mes;
    private Integer total_ingresos;
    private Integer total_egresos;
    private Integer saldo_cuenta;
}

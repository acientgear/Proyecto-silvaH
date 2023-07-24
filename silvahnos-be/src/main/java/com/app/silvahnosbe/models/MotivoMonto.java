package com.app.silvahnosbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotivoMonto {
    @Id
    private String motivo;
    private Integer monto_total;
}

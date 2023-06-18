package com.app.silvahnosbe.entities;

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
    public EgresoEntity(int i, int j, String string, boolean b, MotivoEEntity motivoe, MovimientoEntity movimiento2,
            Timestamp fecha, Timestamp fecha2, Timestamp fecha3) {
                this.id = (long) i;
                this.monto = j;
                this.descripcion = string;
                this.borrado = b;
                this.motivo = motivoe;
                this.movimiento = movimiento2;
                this.fecha_creacion = fecha;
                this.fecha_modificacion = fecha2;
                this.fecha_borrado = fecha3;
    }
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
    private Timestamp fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    private Timestamp fecha_borrado;
}

package com.app.silvahnosbe.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@SQLDelete(sql = "UPDATE usuario SET borrado = true WHERE id=?")
@Where(clause ="borrado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
 
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true ,nullable = false)
    private Long id ;

    private String nombre;
    private String password;
    @Column(unique = true , nullable = false)
    private String email;
    private boolean borrado=Boolean.FALSE;

    @CreationTimestamp
    private Timestamp fecha_creacion;
    @UpdateTimestamp
    private Timestamp fecha_modificacion;
    @UpdateTimestamp
    private Timestamp fecha_borrado;


}

package com.app.silvahnosbe.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "configuracion_email")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfig {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;



    private String host;
    private int port;
    private String username;
    private String password;
    private String texto;
    private boolean auth;
    private boolean starttls;
}

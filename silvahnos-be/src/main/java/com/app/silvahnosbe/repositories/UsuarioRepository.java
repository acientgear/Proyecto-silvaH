package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.UsuarioEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long>{
    Optional<UsuarioEntity> findByUsuario(String usuario);
    Optional<UsuarioEntity> findByCorreo(String correo);
}
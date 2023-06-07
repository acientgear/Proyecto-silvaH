package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.UsuarioEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long>{
}
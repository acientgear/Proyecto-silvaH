package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EstadoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity,Long>{
}
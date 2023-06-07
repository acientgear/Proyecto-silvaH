package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.MovimientoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity,Long>{
}
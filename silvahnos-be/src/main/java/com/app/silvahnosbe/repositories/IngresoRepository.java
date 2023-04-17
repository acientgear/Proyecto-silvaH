package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.IngresoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<IngresoEntity, Long>{
}

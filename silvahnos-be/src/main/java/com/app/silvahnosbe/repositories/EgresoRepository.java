package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EgresoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepository extends JpaRepository<EgresoEntity,Long>{
}
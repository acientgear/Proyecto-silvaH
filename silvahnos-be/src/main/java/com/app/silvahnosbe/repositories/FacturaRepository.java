package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.FacturaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long>{
}

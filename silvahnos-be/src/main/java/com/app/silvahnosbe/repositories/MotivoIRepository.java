package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.MotivoIEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoIRepository extends JpaRepository<MotivoIEntity,Long>{
}
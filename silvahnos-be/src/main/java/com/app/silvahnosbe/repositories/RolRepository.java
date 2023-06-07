package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.RolEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity,Long>{
}
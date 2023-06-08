package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EmpresaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long>{
}
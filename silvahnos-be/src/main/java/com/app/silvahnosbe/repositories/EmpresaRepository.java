package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EmpresaEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long>{
    @Query(value = "SELECT * FROM empresa as e WHERE e.borrado = 0", nativeQuery = true)
    public List<EmpresaEntity> obtenerEmpresas();

}
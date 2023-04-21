package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EgresoEntity;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepository extends JpaRepository<EgresoEntity,Long>{
    @Query(value = "SELECT * FROM egreso i WHERE i.borrado = 0", nativeQuery = true)
    ArrayList<EgresoEntity> obtenerEgresos();
}
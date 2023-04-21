package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EgresoEntity;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepository extends JpaRepository<EgresoEntity,Long>{
    @Query(value = "SELECT * FROM egreso e WHERE e.borrado = 0", nativeQuery = true)
    ArrayList<EgresoEntity> obtenerEgresos();

    @Query(value = "SELECT * FROM egreso as e WHERE YEAR(e.fecha_creacion) = :anio and MONTH(e.fecha_creacion) = :mes and e.borrado = 0", nativeQuery = true)
    ArrayList<EgresoEntity> obtenerEgresosPorAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);
}
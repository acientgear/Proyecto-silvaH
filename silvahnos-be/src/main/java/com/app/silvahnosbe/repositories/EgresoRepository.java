package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.EgresoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepository extends JpaRepository<EgresoEntity,Long>{
    @Query(value = "SELECT * FROM egreso as e WHERE YEAR(e.fecha_creacion) = :anio and MONTH(e.fecha_creacion) = :mes and e.borrado = 0 ORDER BY e.fecha_creacion DESC", nativeQuery = true)
    List<EgresoEntity> obtenerEgresosPorAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value = "SELECT * FROM egreso e WHERE e.borrado = 0 ORDER BY e.fecha_creacion DESC LIMIT 3", nativeQuery = true)
    List<EgresoEntity> obtenerUltimosEgresos();

    @Query(value = "SELECT COALESCE(sum(e.monto), 0) as monto FROM egreso e WHERE year(e.fecha_creacion) = :anio and month(e.fecha_creacion) = :mes and day(e.fecha_creacion) = :dia and e.borrado = 0", nativeQuery = true)
    Integer obtenerMontoPorDia(@Param("anio") int anio, @Param("mes") int mes, @Param("dia") int dia);

    @Query(value="SELECT * " + 
                 "FROM egreso e " +
                 "WHERE e.borrado = 0 AND " +
                 ":fi <= e.fecha_creacion AND " +
                 "e.fecha_creacion <= :ff", nativeQuery = true)
    List<EgresoEntity> obtenerEgresosEntre(@Param("fi") String fechaInicio, @Param("ff") String fechaFin);

    @Query(value="SELECT COALESCE(SUM(e.monto), 0) FROM egreso e WHERE e.borrado = 0 AND :fi <= e.fecha_creacion AND e.fecha_creacion <= :ff", nativeQuery = true)
    Integer obtenerTotalEntre(@Param("fi") String fechaInicio, @Param("ff") String fechaFin);
}
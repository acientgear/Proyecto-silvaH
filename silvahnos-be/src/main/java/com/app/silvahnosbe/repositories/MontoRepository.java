package com.app.silvahnosbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.models.Monto;

@Repository
public interface MontoRepository extends JpaRepository<Monto, Long>{
    @Query(value = "SELECT SUM(i.monto) as monto_total, i.motivo FROM ingreso as i WHERE i.borrado = 0 AND YEAR(i.fecha_creacion) = :anio AND MONTH(i.fecha_creacion) = :mes GROUP BY i.motivo",nativeQuery = true)
    List<Monto> obtenerMontoIngresoPorAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value = "SELECT SUM(e.monto) as monto_total, e.motivo FROM egreso as e WHERE e.borrado = 0 AND YEAR(e.fecha_creacion) = :anio AND MONTH(e.fecha_creacion) = :mes GROUP BY e.motivo",nativeQuery = true)
    List<Monto> obtenerMontoEgresoPorAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);
}

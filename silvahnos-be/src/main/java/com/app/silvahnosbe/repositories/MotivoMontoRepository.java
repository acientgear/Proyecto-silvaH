package com.app.silvahnosbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.models.MotivoMonto;

@Repository
public interface MotivoMontoRepository extends JpaRepository<MotivoMonto, Long>{

    @Query(value = "SELECT SUM(i.monto) as monto_total, m.nombre as motivo\n" + //
            "FROM ingreso as i, motivoi as m\n" + //
            "WHERE i.borrado = 0 AND YEAR(i.fecha_creacion) = :anio AND MONTH(i.fecha_creacion) = :mes AND i.motivo = m.id\n"
            + //
            "GROUP BY i.motivo\n" + //
            "ORDER BY monto_total DESC", nativeQuery = true)
    List<MotivoMonto> obtenerMontoIngresoPorAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value = "SELECT SUM(e.monto) as monto_total, m.nombre as motivo \n" + //
            "FROM egreso as e, motivoe as m\n" + //
            "WHERE e.borrado = 0 AND YEAR(e.fecha_creacion) = :anio AND MONTH(e.fecha_creacion) = :mes AND e.motivo = m.id\n"
            + //
            "GROUP BY e.motivo\n" + //
            "ORDER BY monto_total DESC", nativeQuery = true)
    List<MotivoMonto> obtenerMontoEgresoPorAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);
}

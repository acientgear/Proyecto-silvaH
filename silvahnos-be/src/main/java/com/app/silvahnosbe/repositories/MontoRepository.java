package com.app.silvahnosbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.models.Monto;

@Repository
public interface MontoRepository extends JpaRepository<Monto, Long> {


        // Realizar la consulta para obtener los montos por mes
        @Query(value = "SELECT COALESCE(SUM(i.monto), 0) as monto_total\n" + //
                        "FROM meses m\n" + //
                        "LEFT JOIN ingreso i ON YEAR(i.fecha_creacion) = :anio AND MONTH(i.fecha_creacion) = m.mes AND i.borrado = 0\n"
                        + //
                        "GROUP BY m.mes", nativeQuery = true)
        List<Monto> obtenerMontoIngresoTotalMesAnual(@Param("anio") int anio);

        @Query(value = "SELECT COALESCE(SUM(e.monto), 0) as monto_total\n" + //
                        "FROM meses m\n" + //
                        "LEFT JOIN egreso e ON YEAR(e.fecha_creacion) = :anio AND MONTH(e.fecha_creacion) = m.mes AND e.borrado = 0\n"
                        + //
                        "GROUP BY m.mes", nativeQuery = true)
        List<Monto> obtenerMontoEgresoTotalMesAnual(@Param("anio") int anio);

}

package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.IngresoEntity;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<IngresoEntity, Long>{
    @Query(value = "SELECT * FROM ingreso i WHERE "+
                    "YEAR(i.fecha_creacion) = :anio and "+
                    "MONTH(i.fecha_creacion) = :mes and i.borrado = 0", nativeQuery = true)
    ArrayList<IngresoEntity> obtenerIngresos(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value = "SELECT * FROM ingreso i WHERE i.borrado = 0 ORDER BY i.fecha_creacion DESC LIMIT 3", nativeQuery = true)
    ArrayList<IngresoEntity> obtenerUltimosIngresos();

    @Query(value = "SELECT sum(i.monto) as monto FROM ingreso i WHERE year(i.fecha_creacion) = :anio and month(i.fecha_creacion) = :mes and day(i.fecha_creacion) = :dia and i.borrado = 0", nativeQuery = true)
    Integer obtenerMontoPorDia(@Param("anio") int anio, @Param("mes") int mes, @Param("dia") int dia);
}

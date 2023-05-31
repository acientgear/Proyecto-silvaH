package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.FacturaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long>{
    @Query(value = "SELECT * FROM factura f WHERE "+
            "YEAR(f.fecha_creacion) = :anio and "+
            "MONTH(f.fecha_creacion) = :mes and f.borrado = 0 ORDER BY f.fecha_creacion DESC", nativeQuery = true)
    List<FacturaEntity> obteberFacturas(@Param("anio") int anio, @Param("mes") int mes);
}

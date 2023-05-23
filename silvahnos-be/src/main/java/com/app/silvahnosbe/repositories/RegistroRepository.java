package com.app.silvahnosbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.models.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
    @Query(value = "SELECT id, \"Egreso\" as tipo, fecha_creacion as fecha, descripcion, monto " +
                    "FROM egreso " +
                    "WHERE YEAR(fecha_creacion) = :anio AND MONTH(fecha_creacion) = :mes AND borrado = 0 " +
                    "UNION " +  
                    "SELECT id, \"Ingreso\" as tipo, fecha_creacion as fecha, descripcion, monto FROM ingreso " + 
                    "WHERE YEAR(fecha_creacion) = :anio AND MONTH(fecha_creacion) = :mes AND borrado = 0 " +
                    "ORDER BY fecha DESC;", nativeQuery = true)
    List<Registro> obtenerRegistros(@Param("anio") int anio, @Param("mes") int mes);  
}

package com.app.silvahnosbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.models.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
    @Query(value = "SELECT m.id, e.descripcion, e.fecha_creacion as fecha, e.monto, m.nombre_tabla as tipo "+
                   "FROM movimiento as m, egreso as e WHERE m.nombre_tabla = \"egreso\" AND m.tipo = \"Creación\" "+
                   "AND e.id = m.id_objeto AND e.borrado = 0 AND MONTH(e.fecha_creacion) = :mes AND "+
                   "YEAR(e.fecha_creacion) = :anio UNION SELECT m.id, i.descripcion,  "+
                   "i.fecha_creacion as fecha, i.monto, m.nombre_tabla as tipo FROM movimiento as m, ingreso as i "+
                   "WHERE m.nombre_tabla = \"ingreso\" AND m.tipo = \"Creación\" AND i.id = m.id_objeto  "+ 
                   "AND i.borrado = 0 AND MONTH(i.fecha_creacion) = :mes AND YEAR(i.fecha_creacion) = :anio "+
                   "ORDER by fecha DESC", nativeQuery = true)
    List<Registro> obtenerRegistros(@Param("anio") int anio, @Param("mes") int mes);  
}

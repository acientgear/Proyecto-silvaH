package com.app.silvahnosbe.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.models.FlujoModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    @Query(value = "SELECT t.mes, t.anio, COALESCE(t.total_ingresos, 0) as total_ingresos, COALESCE(t.total_egresos, 0) as total_egresos FROM(SELECT YEAR(fecha_creacion) AS anio, MONTH(fecha_creacion) AS mes, SUM(monto) AS total_ingresos, (SELECT SUM(monto) FROM egreso WHERE borrado = 0 AND YEAR(fecha_creacion) = anio AND MONTH(fecha_creacion) = mes) AS total_egresos FROM ingreso WHERE borrado = 0 AND YEAR(fecha_creacion) = 2023 GROUP BY YEAR(fecha_creacion), MONTH(fecha_creacion) ORDER BY YEAR(fecha_creacion), MONTH(fecha_creacion)) as t;", nativeQuery = true)
    ArrayList<FlujoModel> obtenerFlujo();   

}

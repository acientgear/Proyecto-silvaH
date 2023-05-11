package com.app.silvahnosbe.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.entities.SaldoEntity;
import com.app.silvahnosbe.models.SaldoModel;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoEntity, Long>{
    @Query(value = "SELECT m.nombre, coalesce(s.ingresos_totales,0) as ingresos, coalesce(s.egresos_totales,0) as egresos FROM meses m LEFT JOIN saldo s ON m.id = s.mes AND s.anio = :anio ORDER BY m.id;", nativeQuery = true)
    ArrayList<SaldoModel> obtenerSaldos(@Param("anio") Integer anio);

    @Query(value = "SELECT s.ingresos_totales - s.egresos_totales FROM saldo s WHERE s.anio = :anio AND s.mes = :mes", nativeQuery = true)
    Integer obtenerSaldoCuenta(@Param("anio") Integer anio, @Param("mes") Integer mes);
}

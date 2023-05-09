package com.app.silvahnosbe.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.silvahnosbe.entities.SaldoEntity;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoEntity, Long>{
    @Query(value = "SELECT * FROM saldo s", nativeQuery = true)
    ArrayList<SaldoEntity> obtenerSaldos();
}

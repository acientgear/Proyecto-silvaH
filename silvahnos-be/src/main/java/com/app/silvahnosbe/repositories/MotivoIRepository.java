package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.MotivoIEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoIRepository extends JpaRepository<MotivoIEntity,Long>{
    @Query(value = "SELECT * FROM motivoi as mi WHERE mi.borrado = 0", nativeQuery = true)
    public List<MotivoIEntity> obtenerMotivosI();
}
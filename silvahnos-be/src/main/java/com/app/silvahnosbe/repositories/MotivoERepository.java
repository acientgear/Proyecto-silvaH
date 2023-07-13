package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.MotivoEEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoERepository extends JpaRepository<MotivoEEntity,Long>{
    @Query(value = "SELECT * FROM motivoe as me WHERE me.borrado = 0 ORDER BY nombre", nativeQuery = true)
    public List<MotivoEEntity> obtenerMotivosE();
}
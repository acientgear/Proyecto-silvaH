package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.MotivoEEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoERepository extends JpaRepository<MotivoEEntity,Long>{
}
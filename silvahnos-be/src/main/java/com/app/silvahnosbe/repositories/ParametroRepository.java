package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.ParametroEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<ParametroEntity,Long>{}
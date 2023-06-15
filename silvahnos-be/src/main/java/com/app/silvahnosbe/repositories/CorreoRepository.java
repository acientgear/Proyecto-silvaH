package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.CorreoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorreoRepository extends JpaRepository<CorreoEntity,Long>{}
package com.app.silvahnosbe.repositories;


import com.app.silvahnosbe.entities.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailConfigRepository extends JpaRepository<EmailConfig,Long> {


}

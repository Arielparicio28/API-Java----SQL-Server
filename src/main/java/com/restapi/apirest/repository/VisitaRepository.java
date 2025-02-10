package com.restapi.apirest.repository;

import com.restapi.apirest.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository                               //JpaRepository proporciona métodos CRUD sin necesidad de implementación
public interface VisitaRepository extends JpaRepository<Visita, Long> {
}


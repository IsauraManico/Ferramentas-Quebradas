package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
    Optional<Technician> findByFkPerson_PkPerson(Integer fkPerson);
}
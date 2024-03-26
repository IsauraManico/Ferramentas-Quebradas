package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
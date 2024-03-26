package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NormalUserRepository extends JpaRepository<NormalUser, Integer> {
    Optional<NormalUser> findByFkPerson_PkPerson(Integer pkPerson);
}
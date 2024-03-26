package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.TypeOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfAccountRepository extends JpaRepository<TypeOfAccount, Integer> {
}
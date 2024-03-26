package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.UserSubServiceClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSubServiceClassificationRepository extends JpaRepository<UserSubServiceClassification, Integer> {
    @Query("SELECT ussc FROM UserSubServiceClassification ussc WHERE ussc.fkAccount.pkAccount = :pkAccount AND ussc.fkSubService.pkSubService = :pkSubService")
    Optional<UserSubServiceClassification> findByFkAccount_PkAccountAndFkSubService_PkSubService(Integer pkAccount, Integer pkSubService);
}

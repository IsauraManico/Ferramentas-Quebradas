package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer>
{
    @Query("SELECT ac FROM Account ac WHERE (ac.username = :username OR ac.email = :username) AND ac.password = :password")
    Optional<Account> findAccountByUsernameEqualsAndPasswordEquals(String username, String password);

    @Query("SELECT ac FROM Account ac WHERE (ac.username = :username OR ac.email = :username)")
    Optional<Account> findAccountByUsernameEqualsOrEmailEquals(String username);

    @Query("SELECT acc.profilePicture FROM Account acc WHERE acc.fkPerson.pkPerson = :fkPerson")
    String findFkPerson_ProfilePicture(int fkPerson);

    @Query("SELECT acc FROM Account acc, Technician t WHERE t.pkTechnician = :pkTechnician AND acc.fkPerson.pkPerson = t.fkPerson.pkPerson")
    Optional<Account> findTechnicianAccountByTechnicianPk(int pkTechnician);

    Optional<Account> findAccountByFkPerson_PkPerson(int pkPerson);
}
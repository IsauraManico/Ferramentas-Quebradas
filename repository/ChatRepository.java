package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("SELECT c FROM Chat c " +
            "WHERE " +
            "(c.fkAccount1.pkAccount = :pkAccount1 AND c.fkAccount2.pkAccount = :pkAccount2) " +
            "OR " +
            "(c.fkAccount1.pkAccount = :pkAccount2 AND c.fkAccount2.pkAccount = :pkAccount1)")
    Optional<Chat> findChatByUsers(Integer pkAccount1, Integer pkAccount2);

    @Query("SELECT c FROM Chat c " +
            "WHERE " +
            "(c.fkAccount1.pkAccount = :userId OR c.fkAccount2.pkAccount = :userId) ORDER BY c.lastChatActivity DESC")
    List<Chat> findAllByUser(Integer userId);
}
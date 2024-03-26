package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.ChatNormalMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatNormalMessageRepository extends JpaRepository<ChatNormalMessage, Integer> {
    Optional<ChatNormalMessage> findByFkChatMessage_PkChatMessage(Integer fkChatMessage);
}
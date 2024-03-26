package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.ChatSubServiceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatSubServiceMessageRepository extends JpaRepository<ChatSubServiceMessage, Integer> {
    Optional<ChatSubServiceMessage> findByFkChatMessage_PkChatMessage(Integer pkChatMessage);
}
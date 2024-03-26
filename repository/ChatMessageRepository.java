package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findAllByFkChat_PkChat(Integer chatId);
}
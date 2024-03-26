package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.ChatMessageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageTypeRepository extends JpaRepository<ChatMessageType, Integer> {
}
package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {
}
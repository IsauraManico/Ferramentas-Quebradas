package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
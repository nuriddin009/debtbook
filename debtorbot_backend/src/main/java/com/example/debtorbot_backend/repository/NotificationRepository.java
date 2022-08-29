package com.example.debtorbot_backend.repository;

import com.example.debtorbot_backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {



}

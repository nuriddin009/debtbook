package com.example.debtorbot_backend.repository;


import com.example.debtorbot_backend.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<BotUser, UUID> {

    Optional<BotUser> findByChatId(String chatId);




}

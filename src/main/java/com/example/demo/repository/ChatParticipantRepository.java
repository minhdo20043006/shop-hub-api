package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ChatParticipant;

@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Integer> {
    List<ChatParticipant> findByAccountId(Integer accountId);

    Optional<ChatParticipant> findByChatIdAndAccountId(Integer chatId, Integer accountId);
}

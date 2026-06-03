package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Integer chatId);

    Page<Message> findByChatIdOrderByCreatedAtDesc(Integer chatId, Pageable pageable);

    java.util.Optional<Message> findFirstByChatIdOrderByCreatedAtDesc(Integer chatId);
}

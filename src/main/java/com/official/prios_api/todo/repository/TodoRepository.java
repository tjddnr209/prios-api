package com.official.prios_api.todo.repository;

import com.official.prios_api.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}

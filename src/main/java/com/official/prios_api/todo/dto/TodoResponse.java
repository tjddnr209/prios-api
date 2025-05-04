package com.official.prios_api.todo.dto;

import com.official.prios_api.todo.domain.Todo;

import java.time.LocalDateTime;

public record TodoResponse(
        Long id,
        String content,
        boolean isCompleted,
        LocalDateTime createdAt
) {
    public static TodoResponse from(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getContent(),
                todo.isCompleted(),
                todo.getCreatedAt()
        );
    }
}


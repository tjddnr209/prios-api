package com.furyjoker.priosapi.todo.dto;

import com.furyjoker.priosapi.todo.domain.Todo;

public record TodoResponse(
        Long id,
        String title,
        String description,
        boolean isCompleted,
        String tagName
) {
    public static TodoResponse from(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getTag().getName()
        );
    }
}

package com.furyjoker.priosapi.todo.command;

public record TodoCreateCommand(
        String title,
        String description,
        Long tagId
) {}

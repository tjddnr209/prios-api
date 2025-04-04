package com.furyjoker.priosapi.todo.command;

public record TodoUpdateCommand(
        String title,
        String description,
        Long tagId
) {}

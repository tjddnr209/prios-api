package com.furyjoker.priosapi.todo.command;

public record TodoStatusUpdateCommand(
        boolean isCompleted
) {}

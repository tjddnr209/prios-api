package com.furyjoker.priosapi.schedule.command;

import java.time.LocalDateTime;

public record ScheduleUpdateCommand(
        String title,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt
) {}

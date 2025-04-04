package com.furyjoker.priosapi.schedule.command;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.schedule.domain.Schedule;
import com.furyjoker.priosapi.schedule.domain.ScheduleRepeatType;

import java.time.LocalDateTime;

public record ScheduleCreateCommand(
        String title,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        ScheduleRepeatType repeatType,
        int repeatCount
) {
    public Schedule toEntity(Member member, String groupId) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .groupId(groupId)
                .member(member)
                .build();
    }

    public Schedule toEntity(Member member, String groupId, LocalDateTime start, LocalDateTime end) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(start)
                .endAt(end)
                .groupId(groupId)
                .member(member)
                .build();
    }
}

package com.furyjoker.priosapi.schedule.service;

import com.furyjoker.priosapi.schedule.command.ScheduleCreateCommand;
import com.furyjoker.priosapi.schedule.command.ScheduleUpdateCommand;
import com.furyjoker.priosapi.schedule.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    void createSchedule(Long memberId, ScheduleCreateCommand command);
    List<ScheduleResponse> getSchedules(Long memberId);
    void updateSchedule(Long memberId, Long scheduleId, ScheduleUpdateCommand command);
    void deleteSchedule(Long memberId, Long scheduleId);
    void deleteScheduleGroup(Long memberId, String groupId);
}

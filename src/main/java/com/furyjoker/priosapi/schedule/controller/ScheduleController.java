package com.furyjoker.priosapi.schedule.controller;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.global.resolver.LoginMember;
import com.furyjoker.priosapi.schedule.command.ScheduleCreateCommand;
import com.furyjoker.priosapi.schedule.command.ScheduleUpdateCommand;
import com.furyjoker.priosapi.schedule.controller.docs.ScheduleApiDocs;
import com.furyjoker.priosapi.schedule.dto.ScheduleResponse;
import com.furyjoker.priosapi.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController implements ScheduleApiDocs {

    private final ScheduleService scheduleService;

    @PostMapping
    public ApiResponse<Void> createSchedule(@LoginMember Long memberId,
                                            @RequestBody ScheduleCreateCommand command) {
        scheduleService.createSchedule(memberId, command);
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<ScheduleResponse>> getSchedules(@LoginMember Long memberId) {
        return ApiResponse.success(scheduleService.getSchedules(memberId));
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<Void> updateSchedule(@LoginMember Long memberId,
                                            @PathVariable Long scheduleId,
                                            @RequestBody ScheduleUpdateCommand command) {
        scheduleService.updateSchedule(memberId, scheduleId, command);
        return ApiResponse.success();
    }
}

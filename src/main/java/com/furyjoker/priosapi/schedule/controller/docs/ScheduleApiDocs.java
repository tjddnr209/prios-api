package com.furyjoker.priosapi.schedule.controller.docs;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.schedule.command.ScheduleCreateCommand;
import com.furyjoker.priosapi.schedule.command.ScheduleUpdateCommand;
import com.furyjoker.priosapi.schedule.dto.ScheduleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

public interface ScheduleApiDocs {

    @Operation(summary = "일정 생성", description = "일정을 생성합니다. 반복 일정도 포함됩니다.")
    ApiResponse<Void> createSchedule(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @RequestBody(
                    description = "일정 생성 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ScheduleCreateCommand.class))
            )
            ScheduleCreateCommand command
    );

    @Operation(summary = "일정 목록 조회", description = "로그인한 사용자의 전체 일정을 조회합니다.")
    ApiResponse<List<ScheduleResponse>> getSchedules(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId
    );

    @Operation(summary = "일정 수정", description = "기존 일정을 수정합니다.")
    ApiResponse<Void> updateSchedule(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @Parameter(description = "수정할 일정 ID", example = "1")
            Long scheduleId,

            @RequestBody(
                    description = "일정 수정 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ScheduleUpdateCommand.class))
            )
            ScheduleUpdateCommand command
    );
}

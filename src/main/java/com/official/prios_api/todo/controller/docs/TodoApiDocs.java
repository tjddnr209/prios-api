package com.official.prios_api.todo.controller.docs;

import com.official.prios_api.global.response.ApiResponse;
import com.official.prios_api.todo.dto.TodoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Todo", description = "할 일(todo) 관련 API")
public interface TodoApiDocs {

    @Operation(
            summary = "오늘의 할 일 목록 조회",
            description = "AI가 생성한 오늘의 할 일 목록을 우선순위 순서대로 조회합니다."
    )
    ApiResponse<List<TodoResponse>> getToday();

    @Operation(
            summary = "할 일 완료 체크",
            description = "특정 할 일을 완료 상태로 변경합니다."
    )
    ApiResponse<Void> complete(
            @Parameter(description = "완료할 todo ID", example = "1")
            Long todoId
    );
}


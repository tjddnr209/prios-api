package com.furyjoker.priosapi.todo.controller.docs;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.todo.command.TodoCreateCommand;
import com.furyjoker.priosapi.todo.command.TodoUpdateCommand;
import com.furyjoker.priosapi.todo.command.TodoStatusUpdateCommand;
import com.furyjoker.priosapi.todo.dto.TodoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

public interface TodoApiDocs {

    @Operation(summary = "할 일 생성", description = "할 일을 새로 생성합니다.")
    ApiResponse<Void> createTodo(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @RequestBody(
                    description = "할 일 생성 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TodoCreateCommand.class))
            )
            TodoCreateCommand command
    );

    @Operation(summary = "할 일 목록 조회", description = "사용자의 전체 할 일 목록을 조회합니다.")
    ApiResponse<List<TodoResponse>> getTodos(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId
    );

    @Operation(summary = "할 일 수정", description = "특정 할 일을 수정합니다.")
    ApiResponse<Void> updateTodo(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @Parameter(description = "할 일 ID", example = "1")
            Long todoId,

            @RequestBody(
                    description = "할 일 수정 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TodoUpdateCommand.class))
            )
            TodoUpdateCommand command
    );

    @Operation(summary = "할 일 완료 상태 변경", description = "할 일의 완료 여부를 토글합니다.")
    ApiResponse<Void> toggleComplete(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @Parameter(description = "할 일 ID", example = "1")
            Long todoId,

            @RequestBody(
                    description = "완료 여부 수정 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TodoStatusUpdateCommand.class))
            )
            TodoStatusUpdateCommand command
    );

    @Operation(summary = "할 일 삭제", description = "특정 할 일을 삭제합니다.")
    ApiResponse<Void> deleteTodo(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @Parameter(description = "할 일 ID", example = "1")
            Long todoId
    );
}

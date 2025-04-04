package com.furyjoker.priosapi.todo.controller;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.global.resolver.LoginMember;
import com.furyjoker.priosapi.todo.command.TodoCreateCommand;
import com.furyjoker.priosapi.todo.command.TodoUpdateCommand;
import com.furyjoker.priosapi.todo.command.TodoStatusUpdateCommand;
import com.furyjoker.priosapi.todo.controller.docs.TodoApiDocs;
import com.furyjoker.priosapi.todo.dto.TodoResponse;
import com.furyjoker.priosapi.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController implements TodoApiDocs {

    private final TodoService todoService;

    @PostMapping
    public ApiResponse<Void> createTodo(@LoginMember Long memberId, @RequestBody TodoCreateCommand command) {
        todoService.createTodo(memberId, command);
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<TodoResponse>> getTodos(@LoginMember Long memberId) {
        return ApiResponse.success(todoService.getTodos(memberId));
    }

    @PatchMapping("/{todoId}")
    public ApiResponse<Void> updateTodo(@LoginMember Long memberId,
                                        @PathVariable Long todoId,
                                        @RequestBody TodoUpdateCommand command) {
        todoService.updateTodo(memberId, todoId, command);
        return ApiResponse.success();
    }

    @PatchMapping("/{todoId}/status")
    public ApiResponse<Void> toggleComplete(@LoginMember Long memberId,
                                            @PathVariable Long todoId,
                                            @RequestBody TodoStatusUpdateCommand command) {
        todoService.updateStatus(memberId, todoId, command);
        return ApiResponse.success();
    }

    @DeleteMapping("/{todoId}")
    public ApiResponse<Void> deleteTodo(@LoginMember Long memberId, @PathVariable Long todoId) {
        todoService.deleteTodo(memberId, todoId);
        return ApiResponse.success();
    }
}

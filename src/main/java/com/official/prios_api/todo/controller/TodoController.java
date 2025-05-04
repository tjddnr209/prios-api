package com.official.prios_api.todo.controller;

import com.official.prios_api.global.response.ApiResponse;
import com.official.prios_api.orderinfo.service.OrderInfoService;
import com.official.prios_api.todo.controller.docs.TodoApiDocs;
import com.official.prios_api.todo.domain.Todo;
import com.official.prios_api.todo.dto.TodoResponse;
import com.official.prios_api.todo.repository.TodoRepository;
import com.official.prios_api.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController implements TodoApiDocs {

    private final TodoService todoService;
    private final TodoRepository todoRepository;
    private final OrderInfoService orderInfoService;

    @GetMapping("/today")
    public ApiResponse<List<TodoResponse>> getToday() {
        List<Todo> todos = todoService.getToday();
        Map<Long, Todo> todoMap = todos.stream().collect(Collectors.toMap(Todo::getId, t -> t));

        List<Long> orderedIds = orderInfoService.getTodayOrder().stream()
                .map(info -> info.getTodoId())
                .filter(todoMap::containsKey)
                .toList();

        List<TodoResponse> ordered = orderedIds.stream()
                .map(id -> TodoResponse.from(todoMap.get(id)))
                .toList();

        return ApiResponse.ok(ordered);
    }

    @PatchMapping("/{todoId}/complete")
    public ApiResponse<Void> complete(@PathVariable Long todoId) {
        todoService.markAsCompleted(todoId);
        return ApiResponse.ok();
    }

    @PatchMapping("/{todoId}")
    public ApiResponse<Void> update(@PathVariable Long todoId, @RequestParam String content) {
        todoService.update(todoId, content);
        return ApiResponse.ok();
    }

    @DeleteMapping("/{todoId}")
    public ApiResponse<Void> delete(@PathVariable Long todoId) {
        todoService.delete(todoId);
        return ApiResponse.ok();
    }
}


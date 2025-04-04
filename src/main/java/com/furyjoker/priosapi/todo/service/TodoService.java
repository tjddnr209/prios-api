package com.furyjoker.priosapi.todo.service;

import com.furyjoker.priosapi.todo.command.TodoCreateCommand;
import com.furyjoker.priosapi.todo.command.TodoUpdateCommand;
import com.furyjoker.priosapi.todo.command.TodoStatusUpdateCommand;
import com.furyjoker.priosapi.todo.dto.TodoResponse;

import java.util.List;

public interface TodoService {
    void createTodo(Long memberId, TodoCreateCommand command);
    void updateTodo(Long memberId, Long todoId, TodoUpdateCommand command);
    void updateStatus(Long memberId, Long todoId, TodoStatusUpdateCommand command);
    List<TodoResponse> getTodos(Long memberId);
    void deleteTodo(Long memberId, Long todoId);
}

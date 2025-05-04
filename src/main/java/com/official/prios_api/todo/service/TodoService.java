package com.official.prios_api.todo.service;

import com.official.prios_api.todo.domain.Todo;
import com.official.prios_api.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public void save(String content) {
        Todo todo = Todo.builder()
                .content(content)
                .isCompleted(false)
                .build();
        todoRepository.save(todo);
    }

    public List<Todo> getToday() {
        LocalDate today = LocalDate.now();
        return todoRepository.findAllByCreatedAtBetween(
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );
    }

    public void markAsCompleted(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));
        todo.complete();
    }

    public void update(Long todoId, String content) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));
        todo.updateContent(content);
    }

    public void delete(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}

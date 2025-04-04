package com.furyjoker.priosapi.todo.service;

import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.TagErrorCode;
import com.furyjoker.priosapi.global.exception.type.TodoErrorCode;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import com.furyjoker.priosapi.tag.domain.Tag;
import com.furyjoker.priosapi.tag.infrastructure.TagRepository;
import com.furyjoker.priosapi.todo.command.TodoCreateCommand;
import com.furyjoker.priosapi.todo.command.TodoUpdateCommand;
import com.furyjoker.priosapi.todo.command.TodoStatusUpdateCommand;
import com.furyjoker.priosapi.todo.domain.Todo;
import com.furyjoker.priosapi.todo.dto.TodoResponse;
import com.furyjoker.priosapi.todo.infrastructure.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final TagRepository tagRepository;

    @Override
    public void createTodo(Long memberId, TodoCreateCommand command) {
        Member member = getMember(memberId);
        Tag tag = tagRepository.findById(command.tagId())
                .orElseThrow(() -> new PriosException(TagErrorCode.TAG_NOT_FOUND));

        Todo todo = Todo.builder()
                .title(command.title())
                .description(command.description())
                .isCompleted(false)
                .member(member)
                .tag(tag)
                .build();

        todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Long memberId, Long todoId, TodoUpdateCommand command) {
        Todo todo = getTodo(memberId, todoId);
        Tag tag = getTag(command.tagId());
        todo.update(command.title(), command.description(), tag);
    }

    @Override
    public void updateStatus(Long memberId, Long todoId, TodoStatusUpdateCommand command) {
        Todo todo = getTodo(memberId, todoId);
        todo.changeStatus(command.isCompleted());
    }

    @Override
    public List<TodoResponse> getTodos(Long memberId) {
        Member member = getMember(memberId);
        return todoRepository.findByMember(member).stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTodo(Long memberId, Long todoId) {
        Todo todo = getTodo(memberId, todoId);
        todoRepository.delete(todo);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new PriosException(TodoErrorCode.MEMBER_NOT_FOUND));
    }

    private Tag getTag(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new PriosException(TodoErrorCode.TAG_NOT_FOUND));
    }

    private Todo getTodo(Long memberId, Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new PriosException(TodoErrorCode.TODO_NOT_FOUND));
        if (!todo.getMember().getId().equals(memberId)) {
            throw new PriosException(TodoErrorCode.TODO_FORBIDDEN);
        }
        return todo;
    }
}

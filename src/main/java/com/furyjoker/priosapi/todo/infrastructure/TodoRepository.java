package com.furyjoker.priosapi.todo.infrastructure;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByMember(Member member);
    List<Todo> findByMemberId(Long memberId);
}

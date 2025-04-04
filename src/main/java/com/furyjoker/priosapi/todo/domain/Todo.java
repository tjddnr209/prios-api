package com.furyjoker.priosapi.todo.domain;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public void update(String title, String description, Tag tag) {
        this.title = title;
        this.description = description;
        this.tag = tag;
    }

    public void changeStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}

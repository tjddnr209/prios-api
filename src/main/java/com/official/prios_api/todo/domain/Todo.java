package com.official.prios_api.todo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean isCompleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void complete() {
        this.isCompleted = true;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
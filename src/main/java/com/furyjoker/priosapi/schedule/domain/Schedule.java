package com.furyjoker.priosapi.schedule.domain;

import com.furyjoker.priosapi.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String groupId; // 반복 일정 그룹 ID (null 가능)

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void update(String title, String description, LocalDateTime startAt, LocalDateTime endAt) {
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}

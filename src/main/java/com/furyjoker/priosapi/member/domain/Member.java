package com.furyjoker.priosapi.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oauthId;

    private String name;

    private String email;

    private String picture;

    private boolean isClaudeLinked;

    private boolean isGoogleCalendarLinked;

    public void updateName(String name) {
        this.name = name;
    }

    public void linkClaude() {
        this.isClaudeLinked = true;
    }

    public void linkGoogleCalendar() {
        this.isGoogleCalendarLinked = true;
    }
}

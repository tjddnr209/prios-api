package com.furyjoker.priosapi.auth.domain;

import com.furyjoker.priosapi.member.domain.Member;

public record AuthMember(
        String oauthId,
        String name,
        String email,
        String picture
) {
    public Member toEntity() {
        return Member.builder()
                .oauthId(oauthId)
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }
}

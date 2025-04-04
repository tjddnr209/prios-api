package com.furyjoker.priosapi.member.dto;

import com.furyjoker.priosapi.member.domain.Member;

public record MemberInfoDto(
        String name,
        boolean isClaudeLinked,
        boolean isGoogleCalendarLinked
) {
    public static MemberInfoDto of(Member member) {
        return new MemberInfoDto(
                member.getName(),
                member.isClaudeLinked(),
                member.isGoogleCalendarLinked()
        );
    }
}

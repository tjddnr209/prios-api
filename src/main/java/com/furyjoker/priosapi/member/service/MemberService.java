package com.furyjoker.priosapi.member.service;

import com.furyjoker.priosapi.member.dto.MemberInfoDto;

public interface MemberService {
    MemberInfoDto getMyInfo(Long memberId);
    void updateName(Long memberId, String name);
}

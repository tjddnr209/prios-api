package com.furyjoker.priosapi.member.service;

import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.MemberErrorCode;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.dto.MemberInfoDto;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberInfoDto getMyInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new PriosException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberInfoDto.of(member);
    }

    @Override
    public void updateName(Long memberId, String name) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new PriosException(MemberErrorCode.MEMBER_NOT_FOUND));
        member.updateName(name);
    }
}

package com.furyjoker.priosapi.auth.service;

import com.furyjoker.priosapi.auth.domain.AuthMember;
import com.furyjoker.priosapi.auth.dto.TokenDto;
import com.furyjoker.priosapi.auth.infrastructure.GoogleOAuthClient;
import com.furyjoker.priosapi.global.jwt.JwtProvider;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import com.furyjoker.priosapi.tag.service.TagService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final GoogleOAuthClient googleOAuthClient;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final TagService tagService;

    @Override
    public TokenDto login(String code) {
        AuthMember authMember = googleOAuthClient.getUserInfo(code);

        Member member = memberRepository.findByOauthId(authMember.oauthId())
                .orElseGet(() -> {
                    Member newMember = memberRepository.save(authMember.toEntity());
                    tagService.createDefaultTagsFor(newMember); // 기본 태그 생성
                    return newMember;
                });

        return jwtProvider.generateTokenDto(member.getId());
    }

    @Override
    public TokenDto reissue(HttpServletRequest request) {
        return jwtProvider.reissue(request);
    }
}

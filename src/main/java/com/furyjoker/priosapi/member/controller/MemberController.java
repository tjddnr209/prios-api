package com.furyjoker.priosapi.member.controller;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.global.resolver.LoginMember;
import com.furyjoker.priosapi.member.controller.docs.MemberApiDocs;
import com.furyjoker.priosapi.member.dto.MemberInfoDto;
import com.furyjoker.priosapi.member.dto.MemberUpdateRequest;
import com.furyjoker.priosapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController implements MemberApiDocs {

    private final MemberService memberService;

    @GetMapping("/me")
    public ApiResponse<MemberInfoDto> getMyInfo(@LoginMember Long memberId) {
        return ApiResponse.success(memberService.getMyInfo(memberId));
    }

    @PatchMapping("/name")
    public ApiResponse<Void> updateName(
            @LoginMember Long memberId,
            @RequestBody MemberUpdateRequest request
    ) {
        memberService.updateName(memberId, request.name());
        return ApiResponse.success();
    }
}

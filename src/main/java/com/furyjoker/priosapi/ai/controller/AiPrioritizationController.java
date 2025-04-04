package com.furyjoker.priosapi.ai.controller;

import com.furyjoker.priosapi.ai.usecase.AiPrioritizationUseCase;
import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.global.resolver.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiPrioritizationController {

    private final AiPrioritizationUseCase aiPrioritizationUseCase;

    @PostMapping("/prioritize")
    public ApiResponse<Void> prioritize(@LoginMember Long memberId) {
        aiPrioritizationUseCase.prioritize(memberId);
        return ApiResponse.success();
    }
}

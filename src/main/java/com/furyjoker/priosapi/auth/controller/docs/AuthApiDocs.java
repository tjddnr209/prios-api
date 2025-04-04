package com.furyjoker.priosapi.auth.controller.docs;

import com.furyjoker.priosapi.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthApiDocs {

    @Operation(
            summary = "구글 로그인",
            description = "Authorization code를 통해 로그인하고 토큰을 반환합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    ApiResponse<?> login(
            @Parameter(description = "Authorization Code", required = true)
            String code
    );

    @Operation(
            summary = "토큰 재발급",
            description = "리프레시 토큰으로 새로운 액세스 토큰을 발급합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "재발급 성공"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "토큰 만료 또는 인증 실패")
            }
    )
    ApiResponse<?> reissue(HttpServletRequest request);
}

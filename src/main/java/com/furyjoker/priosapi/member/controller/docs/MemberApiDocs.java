package com.furyjoker.priosapi.member.controller.docs;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.member.dto.MemberInfoDto;
import com.furyjoker.priosapi.member.dto.MemberUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface MemberApiDocs {

    @Operation(
            summary = "내 정보 조회",
            description = "현재 로그인된 사용자의 정보를 반환합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
            }
    )
    ApiResponse<MemberInfoDto> getMyInfo(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId
    );

    @Operation(
            summary = "이름 변경",
            description = "사용자의 이름을 수정합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "이름 수정 성공")
            }
    )
    ApiResponse<Void> updateName(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @RequestBody(
                    description = "변경할 이름 정보",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MemberUpdateRequest.class))
            )
            MemberUpdateRequest request
    );
}

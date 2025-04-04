package com.furyjoker.priosapi.tag.controller.docs;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.tag.dto.CustomTagCreateRequest;
import com.furyjoker.priosapi.tag.dto.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

public interface TagApiDocs {

    @Operation(summary = "사용자 태그 생성", description = "사용자가 직접 입력한 태그를 생성합니다.")
    ApiResponse<Void> createCustomTag(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @RequestBody(
                    description = "사용자 태그 생성 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CustomTagCreateRequest.class))
            )
            CustomTagCreateRequest request
    );

    @Operation(summary = "모든 태그 조회", description = "기본 태그 + 사용자 태그 전체를 조회합니다.")
    ApiResponse<List<TagResponse>> getAllTags(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId
    );

    @Operation(summary = "태그 삭제", description = "사용자가 생성한 태그를 삭제합니다.")
    ApiResponse<Void> deleteTag(
            @Parameter(description = "로그인된 사용자 ID", hidden = true)
            Long memberId,

            @Parameter(description = "삭제할 태그 ID", example = "3")
            Long tagId
    );
}

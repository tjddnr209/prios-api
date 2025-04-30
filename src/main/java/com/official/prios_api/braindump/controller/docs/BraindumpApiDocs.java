package com.official.prios_api.braindump.controller.docs;

import com.official.prios_api.braindump.dto.BraindumpRequest;
import com.official.prios_api.braindump.dto.BraindumpResponse;
import com.official.prios_api.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Braindump", description = "브레인덤프 관련 API")
public interface BraindumpApiDocs {

    @Operation(
            summary = "브레인덤프 등록",
            description = "사용자가 오늘의 브레인덤프를 작성합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "성공",
                            content = @Content(
                                    schema = @Schema(implementation = BraindumpRequest.class)
                            )
                    )
            }
    )
    ApiResponse<Void> save(@RequestBody BraindumpRequest request);

    @Operation(
            summary = "오늘의 브레인덤프 조회",
            description = "오늘 생성된 브레인덤프 목록을 조회합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    schema = @Schema(implementation = BraindumpResponse.class)
                            )
                    )
            }
    )
    ApiResponse<List<BraindumpResponse>> getToday();
}

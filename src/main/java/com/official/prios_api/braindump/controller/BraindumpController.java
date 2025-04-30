package com.official.prios_api.braindump.controller;

import com.official.prios_api.braindump.controller.docs.BraindumpApiDocs;
import com.official.prios_api.braindump.dto.BraindumpRequest;
import com.official.prios_api.braindump.dto.BraindumpResponse;
import com.official.prios_api.braindump.service.BraindumpService;
import com.official.prios_api.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/braindump")
@RequiredArgsConstructor
public class BraindumpController implements BraindumpApiDocs {

    private final BraindumpService braindumpService;

    @PostMapping
    public ApiResponse<Void> save(@RequestBody @Valid BraindumpRequest request) {
        braindumpService.save(request);
        return ApiResponse.ok();
    }

    @GetMapping("/today")
    public ApiResponse<List<BraindumpResponse>> getToday() {
        var list = braindumpService.getToday().stream()
                .map(BraindumpResponse::from)
                .collect(Collectors.toList());
        return ApiResponse.ok(list);
    }
}

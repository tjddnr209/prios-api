package com.furyjoker.priosapi.tag.controller;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.global.resolver.LoginMember;
import com.furyjoker.priosapi.tag.controller.docs.TagApiDocs;
import com.furyjoker.priosapi.tag.dto.CustomTagCreateRequest;
import com.furyjoker.priosapi.tag.dto.TagResponse;
import com.furyjoker.priosapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController implements TagApiDocs {

    private final TagService tagService;

    @PostMapping("/custom")
    public ApiResponse<Void> createCustomTag(@LoginMember Long memberId,
                                             @RequestBody CustomTagCreateRequest request) {
        tagService.createCustomTag(memberId, request.name());
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<TagResponse>> getAllTags(@LoginMember Long memberId) {
        return ApiResponse.success(tagService.getAllTags(memberId));
    }

    @DeleteMapping("/{tagId}")
    public ApiResponse<Void> deleteTag(@LoginMember Long memberId, @PathVariable Long tagId) {
        tagService.deleteTag(memberId, tagId);
        return ApiResponse.success();
    }
}

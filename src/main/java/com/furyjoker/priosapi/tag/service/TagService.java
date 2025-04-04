package com.furyjoker.priosapi.tag.service;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.tag.dto.TagResponse;

import java.util.List;

public interface TagService {
    void createDefaultTagsFor(Member member);
    void createCustomTag(Long memberId, String name);
    List<TagResponse> getAllTags(Long memberId);
    void deleteTag(Long memberId, Long tagId);
}

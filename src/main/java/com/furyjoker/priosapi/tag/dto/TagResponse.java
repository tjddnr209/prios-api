package com.furyjoker.priosapi.tag.dto;

import com.furyjoker.priosapi.tag.domain.Tag;

public record TagResponse(Long id, String name, boolean isDefault) {
    public static TagResponse from(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName(), tag.isDefault());
    }
}

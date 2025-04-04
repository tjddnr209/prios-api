package com.furyjoker.priosapi.tag.service;

import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.TagErrorCode;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import com.furyjoker.priosapi.tag.domain.Tag;
import com.furyjoker.priosapi.tag.dto.TagResponse;
import com.furyjoker.priosapi.tag.infrastructure.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final MemberRepository memberRepository;

    private static final List<String> DEFAULT_TAGS = List.of(
            "중요하고 급한 일", "중요하지만 급하지 않은 일",
            "급하지만 중요하지 않은 일", "중요하지도 급하지도 않은 일",
            "루틴", "기타"
    );

    @Override
    public void createDefaultTagsFor(Member member) {
        List<String> defaultTagNames = List.of("운동", "공부", "업무", "취미", "자기계발", "기타");

        List<Tag> defaultTags = defaultTagNames.stream()
                .map(name -> Tag.builder()
                        .name(name)
                        .isDefault(true)
                        .member(member)
                        .build())
                .toList();

        tagRepository.saveAll(defaultTags);
    }

    @Override
    public void createCustomTag(Long memberId, String name) {
        Member member = findMember(memberId);
        Tag tag = Tag.builder()
                .name(name)
                .isDefault(false)
                .member(member)
                .build();
        tagRepository.save(tag);
    }

    @Override
    public List<TagResponse> getAllTags(Long memberId) {
        Member member = findMember(memberId);
        return tagRepository.findByMember(member).stream()
                .map(TagResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTag(Long memberId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new PriosException(TagErrorCode.TAG_NOT_FOUND));
        if (!tag.getMember().getId().equals(memberId)) {
            throw new PriosException(TagErrorCode.TAG_FORBIDDEN);
        }
        if (tag.isDefault()) {
            throw new PriosException(TagErrorCode.CANNOT_DELETE_DEFAULT_TAG);
        }
        tagRepository.delete(tag);
    }

    private Member findMember(Long id) {
        System.out.println("▶ memberId로 조회: " + id);
        return memberRepository.findById(id)
                .orElseThrow(() -> new PriosException(TagErrorCode.MEMBER_NOT_FOUND));
    }

}

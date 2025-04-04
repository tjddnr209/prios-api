package com.furyjoker.priosapi.tag.infrastructure;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByMember(Member member);
}

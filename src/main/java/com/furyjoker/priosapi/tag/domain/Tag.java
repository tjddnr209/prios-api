package com.furyjoker.priosapi.tag.domain;

import com.furyjoker.priosapi.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}

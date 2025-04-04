package com.furyjoker.priosapi.orderinfo.domain;

import com.furyjoker.priosapi.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long targetId; // todoId 또는 scheduleId

    @Enumerated(EnumType.STRING)
    private OrderType type; // TODO, SCHEDULE 등

    private int orderIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void updateOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}

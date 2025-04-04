package com.furyjoker.priosapi.orderinfo.infrastructure;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.orderinfo.domain.OrderInfo;
import com.furyjoker.priosapi.orderinfo.domain.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    List<OrderInfo> findByMemberAndTypeOrderByOrderIndex(Member member, OrderType type);
    Optional<OrderInfo> findByTargetIdAndType(Long targetId, OrderType type);
    void deleteByTargetIdAndType(Long targetId, OrderType type);
}

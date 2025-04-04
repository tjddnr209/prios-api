package com.furyjoker.priosapi.orderinfo.service;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.orderinfo.domain.OrderType;
import com.furyjoker.priosapi.orderinfo.dto.OrderUpdateRequest;

import java.util.List;

public interface OrderInfoService {
    void initOrder(Long memberId, Long targetId, OrderType type);
    void updateOrder(Long memberId, OrderUpdateRequest request, OrderType type);
    void removeOrder(Long targetId, OrderType type);
    void createOrderInfoList(Member member, List<?> entities, OrderType type);
    void updateOrder(Long memberId, List<Long> sortedIds);
}

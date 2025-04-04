package com.furyjoker.priosapi.orderinfo.service;

import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.MemberErrorCode;
import com.furyjoker.priosapi.global.exception.type.OrderInfoErrorCode;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import com.furyjoker.priosapi.orderinfo.domain.OrderInfo;
import com.furyjoker.priosapi.orderinfo.domain.OrderType;
import com.furyjoker.priosapi.orderinfo.dto.OrderUpdateRequest;
import com.furyjoker.priosapi.orderinfo.infrastructure.OrderInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {

    private final OrderInfoRepository orderInfoRepository;
    private final MemberRepository memberRepository;

    @Override
    public void initOrder(Long memberId, Long targetId, OrderType type) {
        Member member = findMember(memberId);
        int nextIndex = orderInfoRepository.findByMemberAndTypeOrderByOrderIndex(member, type).size();

        OrderInfo orderInfo = OrderInfo.builder()
                .member(member)
                .targetId(targetId)
                .orderIndex(nextIndex)
                .type(type)
                .build();

        orderInfoRepository.save(orderInfo);
    }

    @Override
    public void updateOrder(Long memberId, OrderUpdateRequest request, OrderType type) {
        List<Long> orderedIds = request.orderedTargetIds();

        for (int i = 0; i < orderedIds.size(); i++) {
            Long targetId = orderedIds.get(i);
            OrderInfo orderInfo = orderInfoRepository.findByTargetIdAndType(targetId, type)
                    .orElseThrow(); // 생략 가능

            orderInfo.updateOrderIndex(i);
        }
    }

    @Override
    public void removeOrder(Long targetId, OrderType type) {
        orderInfoRepository.deleteByTargetIdAndType(targetId, type);
    }

    @Override
    public void createOrderInfoList(Member member, List<?> entities, OrderType type) {
        List<OrderInfo> orderInfos = IntStream.range(0, entities.size())
                .mapToObj(i -> {
                    Long targetId;

                    if (type == OrderType.TODO) {
                        targetId = ((com.furyjoker.priosapi.todo.domain.Todo) entities.get(i)).getId();
                    } else if (type == OrderType.SCHEDULE) {
                        targetId = ((com.furyjoker.priosapi.schedule.domain.Schedule) entities.get(i)).getId();
                    } else {
                        throw new IllegalArgumentException("지원하지 않는 OrderType: " + type);
                    }

                    return OrderInfo.builder()
                            .member(member)
                            .targetId(targetId)
                            .type(type)
                            .orderIndex(i)
                            .build();
                })
                .toList();

        orderInfoRepository.saveAll(orderInfos);
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new PriosException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    public void updateOrder(Long memberId, List<Long> orderedTargetIds) {
        for (int i = 0; i < orderedTargetIds.size(); i++) {
            Long targetId = orderedTargetIds.get(i);
            OrderInfo orderInfo = orderInfoRepository.findByTargetIdAndType(targetId, OrderType.TODO)  // 또는 SCHEDULE
                    .orElseThrow(() -> new PriosException(OrderInfoErrorCode.ORDER_UPDATE_FAILED));

            orderInfo.updateOrderIndex(i);
        }
    }
}

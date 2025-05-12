package com.official.prios_api.orderinfo.service;

import com.official.prios_api.orderinfo.domain.OrderInfo;
import com.official.prios_api.orderinfo.repository.OrderInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderInfoService {

    private final OrderInfoRepository orderInfoRepository;

    @Transactional
    public void updateOrder(List<Long> todoIdList) {
        // 기존 오늘의 OrderInfo 삭제
        LocalDate today = LocalDate.now();
        orderInfoRepository.deleteAllByCreatedAtBetween(
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );

        // 새로운 OrderInfo 저장 (todoId 순서에 따라 orderIndex 지정)
        List<OrderInfo> orderInfoList = todoIdList.stream()
                .map(todoId -> OrderInfo.builder()
                        .todoId(todoId)
                        .orderIndex(todoIdList.indexOf(todoId))
                        .build())
                .collect(Collectors.toList());
        orderInfoRepository.saveAll(orderInfoList);
    }

    public List<OrderInfo> getTodayOrder() {
        LocalDate today = LocalDate.now();
        return orderInfoRepository.findAllByCreatedAtBetweenOrderByOrderIndex(
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );
    }
}

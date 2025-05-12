package com.official.prios_api.orderinfo.repository;

import com.official.prios_api.orderinfo.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

    List<OrderInfo> findAllByCreatedAtBetweenOrderByOrderIndex(
            LocalDateTime start, LocalDateTime end
    );

    void deleteAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}

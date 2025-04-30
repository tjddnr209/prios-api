package com.official.prios_api.braindump.repository;

import com.official.prios_api.braindump.domain.Braindump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BraindumpRepository extends JpaRepository<Braindump, Long> {
    List<Braindump> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}

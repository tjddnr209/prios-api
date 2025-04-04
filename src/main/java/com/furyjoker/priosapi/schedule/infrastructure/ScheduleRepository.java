package com.furyjoker.priosapi.schedule.infrastructure;

import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMember(Member member);
    List<Schedule> findByGroupId(String groupId);
    List<Schedule> findByMemberId(Long memberId);
}

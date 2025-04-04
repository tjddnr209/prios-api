package com.furyjoker.priosapi.schedule.service;

import com.furyjoker.priosapi.global.exception.PriosException;
import com.furyjoker.priosapi.global.exception.type.ScheduleErrorCode;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import com.furyjoker.priosapi.orderinfo.domain.OrderType;
import com.furyjoker.priosapi.orderinfo.service.OrderInfoService;
import com.furyjoker.priosapi.schedule.command.ScheduleCreateCommand;
import com.furyjoker.priosapi.schedule.command.ScheduleUpdateCommand;
import com.furyjoker.priosapi.schedule.domain.Schedule;
import com.furyjoker.priosapi.schedule.domain.ScheduleRepeatType;
import com.furyjoker.priosapi.schedule.dto.ScheduleResponse;
import com.furyjoker.priosapi.schedule.infrastructure.ScheduleRepository;
import com.furyjoker.priosapi.schedule.util.ScheduleGroupIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final OrderInfoService orderInfoService;

    @Override
    public void createSchedule(Long memberId, ScheduleCreateCommand command) {
        Member member = findMember(memberId);

        if (command.repeatType() == ScheduleRepeatType.NONE) {
            Schedule schedule = command.toEntity(member, null);
            scheduleRepository.save(schedule);
            orderInfoService.initOrder(member.getId(), schedule.getId(), OrderType.SCHEDULE);
            return;
        }

        String groupId = ScheduleGroupIdGenerator.generate();

        List<Schedule> repeated = generateRepeatedSchedules(command, member, groupId);
        scheduleRepository.saveAll(repeated);

        for (Schedule schedule : repeated) {
            orderInfoService.initOrder(member.getId(), schedule.getId(), OrderType.SCHEDULE);
        }
    }

    @Override
    public List<ScheduleResponse> getSchedules(Long memberId) {
        Member member = findMember(memberId);
        return scheduleRepository.findByMember(member).stream()
                .map(ScheduleResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void updateSchedule(Long memberId, Long scheduleId, ScheduleUpdateCommand command) {
        Schedule schedule = getSchedule(scheduleId, memberId);
        schedule.update(command.title(), command.description(), command.startAt(), command.endAt());
    }

    @Override
    public void deleteSchedule(Long memberId, Long scheduleId) {
        Schedule schedule = getSchedule(scheduleId, memberId);
        scheduleRepository.delete(schedule);
        orderInfoService.removeOrder(scheduleId, OrderType.SCHEDULE);
    }

    @Override
    public void deleteScheduleGroup(Long memberId, String groupId) {
        List<Schedule> group = scheduleRepository.findByGroupId(groupId);
        for (Schedule schedule : group) {
            if (!schedule.getMember().getId().equals(memberId)) {
                throw new PriosException(ScheduleErrorCode.FORBIDDEN);
            }
        }
        scheduleRepository.deleteAll(group);
        group.forEach(s -> orderInfoService.removeOrder(s.getId(), OrderType.SCHEDULE));
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new PriosException(ScheduleErrorCode.MEMBER_NOT_FOUND));
    }

    private Schedule getSchedule(Long id, Long memberId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new PriosException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
        if (!schedule.getMember().getId().equals(memberId)) {
            throw new PriosException(ScheduleErrorCode.FORBIDDEN);
        }
        return schedule;
    }

    private List<Schedule> generateRepeatedSchedules(ScheduleCreateCommand command, Member member, String groupId) {
        int count = command.repeatCount();
        ScheduleRepeatType type = command.repeatType();
        LocalDateTime baseStart = command.startAt();
        LocalDateTime baseEnd = command.endAt();

        return IntStream.range(0, count)
                .mapToObj(i -> {
                    LocalDateTime start = type.plus(baseStart, i);
                    LocalDateTime end = type.plus(baseEnd, i);
                    return command.toEntity(member, groupId, start, end);
                })
                .collect(Collectors.toList());
    }
}

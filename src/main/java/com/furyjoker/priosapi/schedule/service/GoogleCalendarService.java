package com.furyjoker.priosapi.schedule.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furyjoker.priosapi.member.domain.Member;
import com.furyjoker.priosapi.member.infrastructure.MemberRepository;
import com.furyjoker.priosapi.schedule.client.GoogleCalendarClient;
import com.furyjoker.priosapi.schedule.domain.Schedule;
import com.furyjoker.priosapi.schedule.infrastructure.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleCalendarService {

    private final GoogleCalendarClient googleCalendarClient;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void syncCalendar(Long memberId, String accessToken) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        String response = googleCalendarClient.getCalendarEvents(accessToken);

        List<Schedule> schedules = parseEvents(response, member);
        scheduleRepository.saveAll(schedules);
    }

    private List<Schedule> parseEvents(String json, Member member) {
        List<Schedule> schedules = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode items = root.path("items");

            for (JsonNode event : items) {
                String title = event.path("summary").asText("제목 없음");
                String description = event.path("description").asText("설명 없음");
                String startStr = event.path("start").path("dateTime").asText();
                String endStr = event.path("end").path("dateTime").asText();

                if (startStr.isEmpty() || endStr.isEmpty()) continue;

                LocalDateTime startAt = LocalDateTime.parse(startStr, DateTimeFormatter.ISO_DATE_TIME);
                LocalDateTime endAt = LocalDateTime.parse(endStr, DateTimeFormatter.ISO_DATE_TIME);

                Schedule schedule = Schedule.builder()
                        .title(title)
                        .description(description)
                        .startAt(startAt)
                        .endAt(endAt)
                        .member(member)
                        .build();

                schedules.add(schedule);
            }

        } catch (Exception e) {
            throw new RuntimeException("Google Calendar 응답 파싱 실패", e);
        }

        return schedules;
    }
}

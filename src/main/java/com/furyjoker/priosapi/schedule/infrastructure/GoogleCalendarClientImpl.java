package com.furyjoker.priosapi.schedule.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GoogleCalendarClientImpl implements GoogleCalendarClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String CALENDAR_EVENTS_URL = "https://www.googleapis.com/calendar/v3/calendars/primary/events";

    @Override
    public List<String> fetchEvents(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                CALENDAR_EVENTS_URL,
                HttpMethod.GET,
                request,
                Map.class
        );

        List<String> eventSummaries = new ArrayList<>();
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) response.getBody().get("items");
            for (Map<String, Object> item : items) {
                String summary = (String) item.get("summary");
                eventSummaries.add(summary);
            }
        } else {
            log.warn("Google Calendar API 연동 실패: {}", response.getStatusCode());
        }

        return eventSummaries;
    }
}

package com.furyjoker.priosapi.schedule.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GoogleCalendarClient {

    private final WebClient webClient;

    public String getCalendarEvents(String accessToken) {
        return webClient.get()
                .uri("https://www.googleapis.com/calendar/v3/calendars/primary/events")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

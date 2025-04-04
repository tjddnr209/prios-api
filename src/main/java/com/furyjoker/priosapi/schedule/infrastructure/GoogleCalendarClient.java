package com.furyjoker.priosapi.schedule.infrastructure;

import java.util.List;

public interface GoogleCalendarClient {
    List<String> fetchEvents(String accessToken);
}

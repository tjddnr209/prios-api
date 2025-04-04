package com.furyjoker.priosapi.schedule.util;

import java.util.UUID;

public class ScheduleGroupIdGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}

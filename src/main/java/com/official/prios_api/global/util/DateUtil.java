package com.official.prios_api.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    public static LocalDateTime startOfToday() {
        return LocalDate.now().atStartOfDay();
    }

    public static LocalDateTime endOfToday() {
        return LocalDate.now().atTime(23, 59, 59);  // 마지막 시간을 23:59:59로 설정
    }
}

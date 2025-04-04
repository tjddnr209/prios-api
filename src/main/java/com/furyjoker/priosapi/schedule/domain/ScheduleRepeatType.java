package com.furyjoker.priosapi.schedule.domain;

import java.time.LocalDateTime;

public enum ScheduleRepeatType {
    NONE {
        @Override
        public LocalDateTime plus(LocalDateTime time, int index) {
            return time;
        }
    },
    DAILY {
        @Override
        public LocalDateTime plus(LocalDateTime time, int index) {
            return time.plusDays(index);
        }
    },
    WEEKLY {
        @Override
        public LocalDateTime plus(LocalDateTime time, int index) {
            return time.plusWeeks(index);
        }
    },
    MONTHLY {
        @Override
        public LocalDateTime plus(LocalDateTime time, int index) {
            return time.plusMonths(index);
        }
    };

    public abstract LocalDateTime plus(LocalDateTime time, int index);
}

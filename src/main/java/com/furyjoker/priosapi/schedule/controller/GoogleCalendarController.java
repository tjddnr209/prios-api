package com.furyjoker.priosapi.schedule.controller;

import com.furyjoker.priosapi.global.response.ApiResponse;
import com.furyjoker.priosapi.global.resolver.LoginMember;
import com.furyjoker.priosapi.schedule.service.GoogleCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/google-calendar")
public class GoogleCalendarController {

    private final GoogleCalendarService googleCalendarService;

    @PostMapping("/sync")
    public ApiResponse<Void> syncGoogleCalendar(
            @LoginMember Long memberId,
            @RequestParam("accessToken") String accessToken
    ) {
        googleCalendarService.syncCalendar(memberId, accessToken);
        return ApiResponse.success();
    }
}

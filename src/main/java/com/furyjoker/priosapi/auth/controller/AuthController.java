package com.furyjoker.priosapi.auth.controller;

import com.furyjoker.priosapi.auth.controller.docs.AuthApiDocs;
import com.furyjoker.priosapi.auth.service.AuthService;
import com.furyjoker.priosapi.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthApiDocs {

    private final AuthService authService;

    @GetMapping("/login")
    public ApiResponse<?> login(@RequestParam("code") String code) {
        return ApiResponse.success(authService.login(code));
    }

    @PostMapping("/reissue")
    public ApiResponse<?> reissue(HttpServletRequest request) {
        return ApiResponse.success(authService.reissue(request));
    }
}

package com.furyjoker.priosapi.auth.service;

import com.furyjoker.priosapi.auth.dto.TokenDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    TokenDto login(String code);
    TokenDto reissue(HttpServletRequest request);
}

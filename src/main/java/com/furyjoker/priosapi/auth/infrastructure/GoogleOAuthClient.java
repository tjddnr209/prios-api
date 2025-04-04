package com.furyjoker.priosapi.auth.infrastructure;

import com.furyjoker.priosapi.auth.domain.AuthMember;

public interface GoogleOAuthClient {
    AuthMember getUserInfo(String code);
}

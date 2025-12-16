package com.chaewookim.accountbookformoms.domain.user.dto.response;

import com.chaewookim.accountbookformoms.domain.user.domain.User;

public record LoginResponse (

    String accessToken,
    String username
) {
    public static LoginResponse from(User user, String accessToken) {
        return new LoginResponse(accessToken, user.getUsername());
    }
}

package com.chaewookim.accountbookformoms.global.event;

import com.chaewookim.accountbookformoms.domain.user.domain.User;

public record UserSignedUpEvent(User user) {
}

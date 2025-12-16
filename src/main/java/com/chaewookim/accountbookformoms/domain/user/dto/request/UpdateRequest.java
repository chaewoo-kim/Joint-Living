package com.chaewookim.accountbookformoms.domain.user.dto.request;

import com.chaewookim.accountbookformoms.domain.user.domain.UserRole;

import java.time.LocalDate;

public record UpdateRequest(
        String username,
        String email,
        String password,
        LocalDate birthDate,
        UserRole role,
        String address
) {
}

package com.chaewookim.accountbookformoms.domain.asset.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        @NotNull
        BigDecimal balance,

        @NotBlank
        String username,

        @NotNull
        Long id
) {
}
